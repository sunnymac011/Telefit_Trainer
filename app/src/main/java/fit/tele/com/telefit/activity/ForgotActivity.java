package fit.tele.com.telefit.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import fit.tele.com.telefit.R;
import fit.tele.com.telefit.apiBase.FetchServiceBase;
import fit.tele.com.telefit.base.BaseActivity;
import fit.tele.com.telefit.databinding.ActivityForgotBinding;
import fit.tele.com.telefit.databinding.ActivityLoginBinding;
import fit.tele.com.telefit.modelBean.LoginBean;
import fit.tele.com.telefit.modelBean.ModelBean;
import fit.tele.com.telefit.utils.CommonUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ForgotActivity extends BaseActivity {

    private ActivityForgotBinding binding;

    private void setListner() {

        binding.imgSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    callForgotApi();
                }
            }

            private boolean validation() {
                if (binding.inputEmail.getText().toString().isEmpty()) {
                    binding.inputEmail.setError("Please enter Email!");
                    return false;
                } else if (!CommonUtils.isValidEmail(binding.inputEmail.getText().toString().trim())) {
                    CommonUtils.toast(context, "Please enter valid email");
                    return false;
                }  else
                    return true;
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_forgot;
    }

    @Override
    public void init() {
        binding = (ActivityForgotBinding) getBindingObj();
        setListner();
    }

    private void callForgotApi() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            Map<String, String> map = new HashMap<>();
            map.put("email", binding.inputEmail.getText().toString().trim());

            Observable<ModelBean<LoginBean>> signupusers = FetchServiceBase.getFetcherService(context).forgotAPI(map);
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
                                CommonUtils.toast(context, ""+loginBean.getMessage());
                                Intent intent = new Intent(ForgotActivity.this, ChangePasswordActivity.class);
                                intent.putExtra("email",binding.inputEmail.getText().toString());
                                startActivity(intent);
                            }
                            else
                                CommonUtils.toast(context, ""+loginBean.getMessage());

                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }
}