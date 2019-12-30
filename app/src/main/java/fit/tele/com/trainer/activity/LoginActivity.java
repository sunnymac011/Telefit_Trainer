package fit.tele.com.trainer.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.apiBase.FetchServiceBase;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.databinding.ActivityLoginBinding;
import fit.tele.com.trainer.modelBean.LoginBean;
import fit.tele.com.trainer.modelBean.ModelBean;
import fit.tele.com.trainer.utils.CommonUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;

    private void setListner() {

        binding.imgSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ForgotActivity.class));
            }
        });

        binding.inputPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    binding.btnLogin.performClick();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                    return true;
                }
                return false;
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    callEmailLoginApi();
                }
            }

            private boolean validation() {
                if (binding.inputEmail.getText().toString().isEmpty()) {
                    binding.inputEmail.setError("Please enter Email!");
                    return false;
                } else if (binding.inputPassword.getText().toString().length() < 6) {
                    CommonUtils.toast(context, "Password must be at least 6 characters long!");
                    return false;
                } else if (!CommonUtils.isValidEmail(binding.inputEmail.getText().toString().trim())) {
                    CommonUtils.toast(context, "Please enter valid email");
                    return false;
                } else if (binding.inputPassword.getText().toString().isEmpty()) {
                    binding.inputPassword.setError("Please enter Password!");
                    return false;
                } else
                    return true;
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        binding = (ActivityLoginBinding) getBindingObj();
        setListner();
    }

    private void callEmailLoginApi() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            Map<String, String> map = new HashMap<>();
            map.put("email", binding.inputEmail.getText().toString().trim());
            map.put("password", binding.inputPassword.getText().toString().trim());
            map.put("device_type", "android");

            if (preferences.getPushToken() != null)
                map.put("device_token", preferences.getPushToken());
            else
                map.put("device_token", "123456789");

            Observable<ModelBean<LoginBean>> signupusers = FetchServiceBase.getFetcherService(context).manualLogin(map);
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<LoginBean>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("callEmailLoginApi "," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNext(ModelBean<LoginBean> loginBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (loginBean.getStatus() == 1) {
                                if(loginBean.getResult() != null) {
                                    preferences.saveUserData(loginBean.getResult());
                                    if(loginBean.getResult().getSessionToken() != null)
                                        preferences.setSessionToken(loginBean.getResult().getSessionToken());
                                }
                                if (preferences.getUserDataPref().getIsSubscribe() != null && preferences.getUserDataPref().getIsSubscribe().equalsIgnoreCase("0")){
                                    Intent intent = new Intent(context, PaymentDescActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finishAffinity();
                                }
                                else {
                                    Intent intent = new Intent(context, CustomersActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finishAffinity();
                                }
                            } else
                                CommonUtils.toast(context,loginBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }
}