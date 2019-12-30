package fit.tele.com.trainer.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.databinding.ActivitySignUpTwoBinding;
import fit.tele.com.trainer.utils.CommonUtils;

public class SignUpTwoActivity extends BaseActivity {

    ActivitySignUpTwoBinding binding;
    String strfName="",strlName="",strEmail="",strPassword="";

    private void setListner() {

        binding.imgSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        List<String> list = new ArrayList<>();
        list.add("Male");
        list.add("Female");
        list.add("Other");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_custom_spinner, list);
        binding.spiGender.setAdapter(adapter);

        binding.edtWeight.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    binding.btnCreate.performClick();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                    return true;
                }
                return false;
            }
        });

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                  //  callSignUpApi();
                }
            }

            private boolean validation() {
                if (binding.spiGender.getSelectedItem().toString().isEmpty()) {
                    CommonUtils.toast(context,"Please selecte Gender!");
                    return false;
                } else if (binding.edtHeight.getText().toString().isEmpty()) {
                    binding.edtHeight.setError("Please enter Height!");
                    return false;
                } else if (binding.edtWeight.getText().toString().isEmpty()) {
                    binding.edtWeight.setError("Please enter Weight!");
                    return false;
                } else
                    return true;
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_sign_up_two;
    }

    @Override
    public void init() {
        binding = (ActivitySignUpTwoBinding) getBindingObj();
        setListner();
        Intent intent = getIntent();
        strfName = intent.getStringExtra("fname");
        strlName = intent.getStringExtra("lname");
        strEmail = intent.getStringExtra("email");
        strPassword = intent.getStringExtra("password");
        if (!strfName.toString().isEmpty())
            binding.txtTitle.setText("Hey, "+strfName);
    }

//    private void callSignUpApi() {
//        if (CommonUtils.isInternetOn(context)) {
//            binding.progress.setVisibility(View.VISIBLE);
//            Map<String, String> map = new HashMap<>();
//            map.put("name", strfName);
//            map.put("l_name", strlName);
//            map.put("email", strEmail);
//            map.put("password", strPassword.trim());
//            map.put("gender", binding.spiGender.getSelectedItem().toString().trim());
//            map.put("height", binding.edtHeight.getText().toString().trim());
//            map.put("weight", binding.edtWeight.getText().toString().trim());
//            map.put("device_type", "android");
//            map.put("login_by", "manual");
//
//            if (preferences.getPushToken() != null) {
//                map.put("device_token", preferences.getPushToken());
//            } else {
//                map.put("device_token", "123456789");
//            }
//
//            Observable<ModelBean<LoginBean>> signupusers = FetchServiceBase.getFetcherService(context).signUpApi(map);
//            subscription = signupusers.subscribeOn(Schedulers.newThread())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Subscriber<ModelBean<LoginBean>>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            e.printStackTrace();
//                            binding.progress.setVisibility(View.GONE);
//                        }
//
//                        @Override
//                        public void onNext(ModelBean<LoginBean> loginBean) {
//                            binding.progress.setVisibility(View.GONE);
//                            if (loginBean.getStatus() == 1) {
//                                CommonUtils.toast(context,"Registered Successfully, Please verify your Email!");
//                                startActivity(new Intent(context, LoginActivity.class));
//                            } else
//                                CommonUtils.toast(context,loginBean.getMessage());
//
//                        }
//                    });
//        } else {
//            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
//        }
//    }
}
