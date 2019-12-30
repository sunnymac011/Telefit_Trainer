package fit.tele.com.trainer.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

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
import fit.tele.com.trainer.databinding.ActivitySignUpthreeBinding;
import fit.tele.com.trainer.dialog.MediaOption;
import fit.tele.com.trainer.modelBean.LoginBean;
import fit.tele.com.trainer.modelBean.ModelBean;
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

public class SignUpthreeActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {

   ActivitySignUpthreeBinding binding;
    String strfName="",strlName="",strEmail="",strPassword="";
    private static final int SELECT_PICTURE = 1, SELECT_PICTURE_CAMARA = 2;
    private Uri outputFileUri;
    private File fileProfile;
    private DatePickerDialog dpd;
    HashMap<String, String> map = new HashMap<>();;

    @Override
    public int getLayoutResId()
    {
        return R.layout.activity_sign_upthree;
    }

    @Override
    public void init() {
        binding = (ActivitySignUpthreeBinding)getBindingObj();
        Intent intent = getIntent();
        strfName = intent.getStringExtra("fname");
        strlName = intent.getStringExtra("lname");
        strEmail = intent.getStringExtra("email");
        strPassword = intent.getStringExtra("password");
//        if (!strfName.toString().isEmpty())
//            binding.txtTitle.setText("Hey, "+strfName);
        setListner();
    }

    private void setListner() {

        binding.imgSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomeShit();
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
            public void onClick(View view) {
                datePicker();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validation()){
                    callSignUpApi();
                }
            }
        });
    }

    private boolean validation() {
        if (binding.spiGender.getSelectedItem().toString().isEmpty()) {
            CommonUtils.toast(context,"Please selecte Gender!");
            return false;
        }else if (binding.spiPhoneType.getSelectedItem().toString().isEmpty()) {
            CommonUtils.toast(context,"Please selecte Phone Type!");
            return false;
        }else if (binding.txtDob.getText().toString().isEmpty()) {
            binding.txtDob.setError("Please enter Date of Birth!");
            return false;
        } else if (binding.inputAddress1.toString().isEmpty()) {
            binding.inputAddress1.setError("Please enter Address1!");
           // CommonUtils.toast(context, "Please enter valid email");
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

                    fileProfile = new File(CommonUtils.compressImage(CommonUtils.getRealPathFromURI(Uri.parse(path), SignUpthreeActivity.this), SignUpthreeActivity.this));

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

                        fileProfile = new File(CommonUtils.compressImage(CommonUtils.getRealPathFromURI(Uri.parse(path), SignUpthreeActivity.this), SignUpthreeActivity.this));

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

            dpd = DatePickerDialog.newInstance(SignUpthreeActivity.this, year, mm, dd);
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

    private void callSignUpApi() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);

            map.put("name", strfName);
            map.put("l_name", strlName);
            map.put("email", strEmail);
            map.put("password", strPassword.trim());
            map.put("gender", binding.spiGender.getSelectedItem().toString().trim());
            map.put("device_type", "android");
            map.put("login_by", "manual");

            if (preferences.getPushToken() != null) {
                map.put("device_token", preferences.getPushToken());
            } else {
                map.put("device_token", "123456789");
            }

            map.put("address1", binding.inputAddress1.getText().toString());
            map.put("address2", binding.inputAddress2.getText().toString());
            map.put("city", binding.inputCity.getText().toString());
            map.put("state", binding.inputState.getText().toString());
            map.put("zipcode", binding.inputZipcode.getText().toString());
            map.put("company_name", binding.inputCname.getText().toString());
            map.put("certification", binding.inputCertification.getText().toString());
            map.put("phone_number", binding.inputPhone.getText().toString());
            map.put("phone_type", binding.spiPhoneType.getSelectedItem().toString().trim());

            MultipartBody.Part body = null;
            if (fileProfile != null) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), fileProfile);
                body = MultipartBody.Part.createFormData("profile_pic", fileProfile.getName(), requestFile);
            }

            Observable<ModelBean<LoginBean>> signupusers = FetchServiceBase.getFetcherService(context).signUpApi(CommonUtils.converRequestBodyFromMap(map), body);
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<LoginBean>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            binding.progress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNext(ModelBean<LoginBean> loginBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (loginBean.getStatus() == 1) {
                                CommonUtils.toast(context,"Registered Successfully, Please verify your Email!");
                                startActivity(new Intent(context, LoginActivity.class));
                            } else
                                CommonUtils.toast(context,loginBean.getMessage());

                        }
                    });
        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = ((monthOfYear+1) > 9 ? (monthOfYear+1) : ("0"+(monthOfYear+1))) + "/" + dayOfMonth + "/" + year;

        Log.e("onDateSet ",""+date);
        binding.txtDob.setText(date);
        map.put("bdate", date);
    }
}
