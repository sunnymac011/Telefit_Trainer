package fit.tele.com.trainer.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.location.Location;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.squareup.picasso.Picasso;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import fit.tele.com.trainer.BuildConfig;
import fit.tele.com.trainer.R;
import fit.tele.com.trainer.apiBase.FetchServiceBase;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.databinding.ActivityEditProfileBinding;
import fit.tele.com.trainer.dialog.MediaOption;
import fit.tele.com.trainer.modelBean.LoginBean;
import fit.tele.com.trainer.modelBean.ModelBean;
import fit.tele.com.trainer.themes.EditProfileActivityTheme;
import fit.tele.com.trainer.utils.CircleTransform;
import fit.tele.com.trainer.utils.CommonUtils;
import fit.tele.com.trainer.utils.FileUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static fit.tele.com.trainer.utils.CommonUtils.decodeSampledBitmapFromResource;

public class EditProfileActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener{

    ActivityEditProfileBinding binding;
    LoginBean saveLogiBean;
    private DatePickerDialog dpd;
    HashMap<String, String> map;
    private static final int SELECT_PICTURE = 1, SELECT_PICTURE_CAMARA = 2;
    private Uri outputFileUri;
    private File fileProfile;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_edit_profile;
    }

    @Override
    public void init() {
        if (!isGooglePlayServicesAvailable()) {
            CommonUtils.toast(this,"GPS Error Restart app");
        }
        binding = (ActivityEditProfileBinding) getBindingObj();
        EditProfileActivityTheme editProfileActivityTheme = new EditProfileActivityTheme();
        editProfileActivityTheme.setTheme(binding, context);
        map = new HashMap<>();
        setListner();
        setData();
    }

    private void setListner(){
        binding.imgSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ProfileActivity.class));
            }
        });

        List<String> list = new ArrayList<>();
        list.add("Male");
        list.add("Female");
        list.add("Other");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_custom_spinner, list);
        binding.spiGender.setAdapter(adapter);

        List<String> list1 = new ArrayList<>();
        list1.add("Cell");
        list1.add("Home");
        list1.add("Work");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.item_custom_spinner, list1);
        binding.spiPhoneType.setAdapter(adapter1);

        binding.llDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });

        binding.btnUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomeShit();
            }
        });

//        binding.llAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
//                            .build(EditProfileActivity.this);
//                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
//                } catch (GooglePlayServicesRepairableException e) {
//                    // TODO: Handle the error.
//                } catch (GooglePlayServicesNotAvailableException e) {
//                    // TODO: Handle the error.
//                }
//            }
//        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation())
                    callEditProfileApi();
            }

            private boolean validation() {
                if (binding.spiGender.getSelectedItem().toString().isEmpty()) {
                    CommonUtils.toast(context,"Please selecte Gender!");
                    return false;
                } else if (binding.inputFname.getText().toString().isEmpty()) {
                    binding.inputFname.setError("Please enter First name");
                    return false;
                } else if (binding.inputLname.getText().toString().isEmpty()) {
                    binding.inputLname.setError("Please enter Last name");
                    return false;
                } else if (binding.inputAddress1.getText().toString().isEmpty()) {
                    binding.inputAddress1.setError("Please enter Address!");
                    return false;
                }else if (binding.inputAddress2.getText().toString().isEmpty()) {
                    binding.inputAddress2.setError("Please enter Address!");
                    return false;
                } else if (binding.inputEmail.getText().toString().isEmpty()) {
                    binding.inputEmail.setError("Please enter Email!");
                    return false;
                } else if (!CommonUtils.isValidEmail(binding.inputEmail.getText().toString().trim())) {
                    CommonUtils.toast(context, "Please enter valid email");
                    return false;
                } else if (fileProfile == null && saveLogiBean.getProfilePic() == null) {
                    CommonUtils.toast(context,"Please upload profile image!");
                    return false;
                } else if (binding.txtDob.getText().toString().isEmpty()) {
                    CommonUtils.toast(context, "Please enter Birthdate!");
                    return false;
                } else if (binding.spiPhoneType.getSelectedItem().toString().isEmpty()) {
                    CommonUtils.toast(context,"Please selecte Phone Type!");
                    return false;
                }else if (binding.inputCity.toString().isEmpty()) {
                    binding.inputCity.setError("Please enter city!");
                    // CommonUtils.toast(context, "Please enter valid email");
                    return false;
                }else if (binding.inputState.getText().toString().isEmpty()) {
                    binding.inputState.setError("Please enter state!");
                    // CommonUtils.toast(context, "Please enter valid email");
                    return false;
                }else if (binding.inputZipcode.getText().toString().isEmpty()) {
                    binding.inputZipcode.setError("Please enter Zip Code!");
                    // CommonUtils.toast(context, "Please enter valid email");
                    return false;
                }else if (binding.inputPhone.getText().toString().isEmpty()) {
                    binding.inputPhone.setError("Please enter Phone Number!");
                    // CommonUtils.toast(context, "Please enter valid email");
                    return false;
                }else if (binding.inputCname.getText().toString().isEmpty()) {
                    binding.inputCname.setError("Please enter company name!");
                    // CommonUtils.toast(context, "Please enter valid email");
                    return false;
                }else if (binding.inputCname.getText().toString().isEmpty()) {
                    binding.inputCname.setError("Please enter certification!");
                    // CommonUtils.toast(context, "Please enter valid email");
                    return false;
                }
                else
                    return true;
            }
        });
    }

    private void setData() {
        saveLogiBean = preferences.getUserDataPref();
        Log.w("Profile_pic",""+saveLogiBean.getProfilePic());
        if (saveLogiBean != null && saveLogiBean.getProfilePic() != null && !TextUtils.isEmpty(saveLogiBean.getProfilePic())) {
            Picasso.with(this)
                    .load(saveLogiBean.getProfilePic())
                    .placeholder(R.drawable.user_placeholder)
                    .error(R.drawable.user_placeholder)
                    .transform(new CircleTransform())
                    .into(binding.imgUser);
        }

        if (saveLogiBean != null && saveLogiBean.getName() != null
                && !TextUtils.isEmpty(saveLogiBean.getName()))
            binding.inputFname.setText(saveLogiBean.getName());
        if (saveLogiBean != null && saveLogiBean.getlName() != null
                && !TextUtils.isEmpty(saveLogiBean.getlName()))
            binding.inputLname.setText(saveLogiBean.getlName());
        if (saveLogiBean != null && saveLogiBean.getEmail() != null
                && !TextUtils.isEmpty(saveLogiBean.getEmail())) {
            binding.inputEmail.setText(saveLogiBean.getEmail());
            binding.inputEmail.setEnabled(false);
        }
        else
            binding.inputEmail.setEnabled(true);
        if (saveLogiBean != null && saveLogiBean.getAddress1() != null
                && !TextUtils.isEmpty(saveLogiBean.getAddress1()))
            binding.inputAddress1.setText(saveLogiBean.getAddress1());
        if (saveLogiBean != null && saveLogiBean.getAddress2() != null
                && !TextUtils.isEmpty(saveLogiBean.getAddress2()))
            binding.inputAddress2.setText(saveLogiBean.getAddress2());
        if (saveLogiBean != null && saveLogiBean.getDob() != null
                && !TextUtils.isEmpty(saveLogiBean.getDob()))
            binding.txtDob.setText(saveLogiBean.getDob());
        if (saveLogiBean != null && saveLogiBean.getGender() != null
                && !TextUtils.isEmpty(saveLogiBean.getGender())) {
            if (saveLogiBean.getGender().equalsIgnoreCase("male"))
                binding.spiGender.setSelection(0);
            if (saveLogiBean.getGender().equalsIgnoreCase("female"))
                binding.spiGender.setSelection(1);
            if (saveLogiBean.getGender().equalsIgnoreCase("other"))
                binding.spiGender.setSelection(2);
            if (saveLogiBean.getGender().equalsIgnoreCase(""))
                binding.spiGender.setSelection(2);
        }if (saveLogiBean != null && saveLogiBean.getPhone_type() != null
                && !TextUtils.isEmpty(saveLogiBean.getPhone_type())) {
            if (saveLogiBean.getPhone_type().equalsIgnoreCase("Cell"))
                binding.spiPhoneType.setSelection(0);
            if (saveLogiBean.getPhone_type().equalsIgnoreCase("Home"))
                binding.spiPhoneType.setSelection(1);
            if (saveLogiBean.getPhone_type().equalsIgnoreCase("Work"))
                binding.spiPhoneType.setSelection(2);
        }
        if (saveLogiBean != null && saveLogiBean.getCity() != null
                && !TextUtils.isEmpty(saveLogiBean.getCity()))
            binding.inputCity.setText(saveLogiBean.getCity());
        if (saveLogiBean != null && saveLogiBean.getState() != null
                && !TextUtils.isEmpty(saveLogiBean.getState()))
            binding.inputState.setText(saveLogiBean.getState());
        if (saveLogiBean != null && saveLogiBean.getZipcode() != null
                && !TextUtils.isEmpty(saveLogiBean.getZipcode()))
            binding.inputZipcode.setText(saveLogiBean.getZipcode());
        if (saveLogiBean != null && saveLogiBean.getPhone_number() != null
                && !TextUtils.isEmpty(saveLogiBean.getPhone_number()))
            binding.inputPhone.setText(saveLogiBean.getPhone_number());
        if (saveLogiBean != null && saveLogiBean.getCompany_name() != null
                && !TextUtils.isEmpty(saveLogiBean.getCompany_name()))
            binding.inputCname.setText(saveLogiBean.getCompany_name());
        if (saveLogiBean != null && saveLogiBean.getCertification() != null
                && !TextUtils.isEmpty(saveLogiBean.getCertification()))
            binding.inputCertification.setText(saveLogiBean.getCertification());
        if (saveLogiBean != null && saveLogiBean.getDescription() != null
                && !TextUtils.isEmpty(saveLogiBean.getDescription()))
            binding.inputDescription.setText(saveLogiBean.getDescription());

    }

    private void datePicker() {
        if (dpd == null || !dpd.isVisible()) {
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int mm = now.get(Calendar.MONTH);
            int dd = now.get(Calendar.DAY_OF_MONTH);

            if(binding.txtDob != null && !binding.txtDob.getText().toString().equalsIgnoreCase("Birth Date")) {
                String[] date = binding.txtDob.getText().toString().split("/");
                if(date.length == 3) {
                    dd = Integer.parseInt(date[1]);
                    mm = Integer.parseInt(date[0]) - 1;
                    year = Integer.parseInt(date[2]);
                }
            }

            dpd = DatePickerDialog.newInstance(EditProfileActivity.this, year, mm, dd);
            dpd.setThemeDark(false);
            dpd.vibrate(false);
            dpd.dismissOnPause(true);
            dpd.setMaxDate(now);
            dpd.showYearPickerFirst(false);
            dpd.setVersion(DatePickerDialog.Version.VERSION_1);
            dpd.setAccentColor(ContextCompat.getColor(context, R.color.colorAccent));
            dpd.setTitle("Select date");

            dpd.show(getFragmentManager(), "Datepickerdialog");
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = ((monthOfYear+1) > 9 ? (monthOfYear+1) : ("0"+(monthOfYear+1))) + "/" + dayOfMonth + "/" + year;

        Log.e("onDateSet ",""+date);
        binding.txtDob.setText(date);
        map.put("bdate", date);
    }

    private void openBottomeShit() {

        mediaOption = new MediaOption(context, new MediaOption.AlertListener() {
            @Override
            public void onCameraClick() {
                RxPermissions rxPermissions1 = new RxPermissions((Activity) context);
                rxPermissions1.requestEach(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(new Action1<Permission>() {
                            @Override
                            public void call(Permission permission) { // will emit 2 Permission objects
                                if (permission.granted) {
                                    if (permission.name.equals("android.permission.CAMERA"))
                                        return;
                                    if (permission.name.equals("android.permission.WRITE_EXTERNAL_STORAGE"))
                                        return;
                                    if (CommonUtils.hasPermissions(context, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                        OnclickCamara();
                                    }

                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    CommonUtils.toast(context, getString(R.string.image_permission_txt));
                                }
                            }
                        });
            }

            @Override
            public void onGalleryClick() {
                RxPermissions rxPermissions = new RxPermissions((Activity) context);
                rxPermissions.requestEach(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(new Action1<Permission>() {
                            @Override
                            public void call(Permission permission) { // will emit 2 Permission objects
                                if (permission.granted) {
                                    if (permission.name.equals("android.permission.CAMERA"))
                                        return;
                                    if (permission.name.equals("android.permission.WRITE_EXTERNAL_STORAGE"))
                                        return;
                                    if (CommonUtils.hasPermissions(context, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                        OnclickGallery();
                                    }
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    CommonUtils.toast(context, getString(R.string.image_permission_txt));
                                }
                            }
                        });
            }
        });
        mediaOption.show();
    }

    public void OnclickCamara() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ignored) {
            }
            if (photoFile != null) {
                Uri photoURI;
                if (Build.VERSION.SDK_INT >= 24)
                    photoURI = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", photoFile);
                else
                    photoURI = Uri.fromFile(photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, SELECT_PICTURE_CAMARA);
            }
        }
    }

    public void OnclickGallery() {
        List<Intent> targets = new ArrayList<>();
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        List<ResolveInfo> candidates = context.getPackageManager().queryIntentActivities(intent, 0);

        for (ResolveInfo candidate : candidates) {
            String packageName = candidate.activityInfo.packageName;
            if (!packageName.equals("com.google.android.apps.photos") && !packageName.equals("com.google.android.apps.plus") && !packageName.equals("com.android.documentsui")) {
                Intent iWantThis = new Intent();
                iWantThis.setType("image/*");
                iWantThis.setAction(Intent.ACTION_PICK);
                iWantThis.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                iWantThis.setPackage(packageName);
                targets.add(iWantThis);
            }
        }
        if (targets.size() > 0) {
            Intent chooser = Intent.createChooser(targets.remove(0), "Select Picture");
            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, targets.toArray(new Parcelable[targets.size()]));
            startActivityForResult(chooser, SELECT_PICTURE);
        } else {
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(Intent.createChooser(intent1, "Select Picture"), SELECT_PICTURE);
        }
    }

    Bitmap bit = null,adjustedBitmap;
    ExifInterface exif;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Place123 ",requestCode+" "+data);
        if (resultCode == RESULT_OK) {
            File finalFile;
            if (requestCode == SELECT_PICTURE_CAMARA) {
                try {
                    finalFile = new File((FileUtils.getFile(context, outputFileUri).getPath()));
                    bit = decodeSampledBitmapFromResource(finalFile.getPath(), 250, 250);
                    exif = new ExifInterface(finalFile.getPath());

                    int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                    int rotationInDegrees = CommonUtils.exifToDegrees(rotation);
                    Matrix matrix = new Matrix();
                    if (rotation != 0f) {
                        matrix.preRotate(rotationInDegrees);
                    }

                    adjustedBitmap = Bitmap.createBitmap(bit, 0, 0, bit.getWidth(), bit.getHeight(), matrix, true);

                    String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), adjustedBitmap, "Title", null);

                    fileProfile = new File(CommonUtils.compressImage(CommonUtils.getRealPathFromURI(Uri.parse(path), EditProfileActivity.this), EditProfileActivity.this));

                    Picasso.with(context)
                            .load(fileProfile)
                            .error(R.drawable.user_placeholder)
                            .placeholder(R.drawable.user_placeholder)
                            .transform(new CircleTransform())
                            .into(binding.imgUser);

                }catch (Exception e)
                {

                }
            }
            if (requestCode == SELECT_PICTURE) {
                if (data != null && data.getData() != null && FileUtils.getFile(context, data.getData()) != null) {
                    try {
                        finalFile = new File((FileUtils.getFile(context, data.getData()).getPath()));

                        bit = decodeSampledBitmapFromResource(finalFile.getPath(), 250, 250);
                        exif = new ExifInterface(finalFile.getPath());

                        int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        int rotationInDegrees = CommonUtils.exifToDegrees(rotation);
                        Matrix matrix = new Matrix();
                        if (rotation != 0f) {
                            matrix.preRotate(rotationInDegrees);
                        }

                        adjustedBitmap = Bitmap.createBitmap(bit, 0, 0, bit.getWidth(), bit.getHeight(), matrix, true);

                        String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), adjustedBitmap, "Title", null);

                        fileProfile = new File(CommonUtils.compressImage(CommonUtils.getRealPathFromURI(Uri.parse(path), EditProfileActivity.this), EditProfileActivity.this));

                        Picasso.with(context)
                                .load(fileProfile)
                                .error(R.drawable.user_placeholder)
                                .placeholder(R.drawable.user_placeholder)
                                .transform(new CircleTransform())
                                .into(binding.imgUser);
                    }catch (Exception e)
                    {

                    }
                } else
                    CommonUtils.toast(context, context.getString(R.string.toast_image_empty));
            }
        } else if (resultCode == RESULT_CANCELED) {
            // The user canceled the operation.
        }
    }

    private void callEditProfileApi() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            map.put("email", binding.inputEmail.getText().toString());
            map.put("name", binding.inputFname.getText().toString());
            map.put("l_name", binding.inputLname.getText().toString());
            map.put("gender", binding.spiGender.getSelectedItem().toString());

            map.put("address1", binding.inputAddress1.getText().toString());
            map.put("address2", binding.inputAddress2.getText().toString());
            map.put("city", binding.inputCity.getText().toString());
            map.put("state", binding.inputState.getText().toString());
            map.put("zipcode", binding.inputZipcode.getText().toString());
            map.put("company_name", binding.inputCname.getText().toString());
            map.put("certification", binding.inputCertification.getText().toString());
            map.put("phone_number", binding.inputPhone.getText().toString());
            map.put("phone_type", binding.spiPhoneType.getSelectedItem().toString().trim());
            map.put("description", binding.inputDescription.getText().toString());

            MultipartBody.Part body = null;
            if (fileProfile != null) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), fileProfile);
                body = MultipartBody.Part.createFormData("profile_pic", fileProfile.getName(), requestFile);
            }

            Observable<ModelBean<LoginBean>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context)
                    .editProfileCustomer(CommonUtils.converRequestBodyFromMap(map), body);
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<LoginBean>>() {
                        @Override
                        public void onCompleted() {
                            binding.progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            binding.progress.setVisibility(View.GONE);
                            CommonUtils.toast(getApplicationContext(), e.getMessage());
                        }

                        @Override
                        public void onNext(ModelBean<LoginBean> loginBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (loginBean.getStatus() == 1) {
                                deleteImages();
                                CommonUtils.toast(context, "Profile Updated Successfully");
                                if(loginBean.getResult() != null) {
                                    saveLogiBean = loginBean.getResult();
                                    preferences.saveUserData(saveLogiBean);
                                    startActivity(new Intent(EditProfileActivity.this, ProfileActivity.class));
                                }
                            } else {
                                CommonUtils.toast(context, loginBean.getMessage());
                            }
                        }
                    });
        } else {
            CommonUtils.toast(context, getString(R.string.snack_bar_no_internet));
        }
    }

    private void deleteImages()
    {
        if (fileProfile != null && fileProfile.exists()) {
            if (fileProfile.delete()) {
                Log.e("file Deleted :" , "fileProfile Deleted");
            } else {
                Log.e("file not Deleted :" , "fileProfile not Deleted");
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        outputFileUri = Uri.fromFile(image);
        return image;
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (dpd != null)
            dpd.setOnDateSetListener(this);
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }
}
