package fit.tele.com.trainer.activity;

import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.apiBase.FetchServiceBase;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.modelBean.ModelBean;
import fit.tele.com.trainer.modelBean.PaymentInfoBean;
import fit.tele.com.trainer.databinding.ActivityPaymentDescBinding;
import fit.tele.com.trainer.utils.CommonUtils;
import fit.tele.com.trainer.utils.MyTagHandler;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PaymentDescActivity extends BaseActivity {

    ActivityPaymentDescBinding binding;
    private PaymentInfoBean paymentInfoBean;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_payment_desc;
    }

    @Override
    public void init() {
        binding = (ActivityPaymentDescBinding) getBindingObj();
        binding.imgSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        callCheckPaymentApi();
    }

    private void setListner() {
        if (paymentInfoBean != null && paymentInfoBean.getDetails() != null
                && !TextUtils.isEmpty(paymentInfoBean.getDetails()))
            binding.txtPaymentDesc.setText(Html.fromHtml(""+Html.fromHtml(paymentInfoBean.getDetails(), null, new MyTagHandler()), null, new MyTagHandler()));
        if (paymentInfoBean != null && paymentInfoBean.getAmount() != null && paymentInfoBean.getAmount() != null
                && !TextUtils.isEmpty(paymentInfoBean.getAmount()))
        {
            binding.txtPrice.setText("Only $ "+paymentInfoBean.getAmount());
            binding.rlPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PaymentDescActivity.this,PaymentActivity.class);
                    intent.putExtra("packagePrice",paymentInfoBean.getAmount());
                    intent.putExtra("subscriptionId",paymentInfoBean.getId());
                    startActivity(intent);
                }
            });
        }
    }

    private void callCheckPaymentApi() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);

            Observable<ModelBean<PaymentInfoBean>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).getCheckPaymentAPI();
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<PaymentInfoBean>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("callCheckPaymentApi"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNext(ModelBean<PaymentInfoBean> apiPaymentInfo) {
                            binding.progress.setVisibility(View.GONE);
                            if (apiPaymentInfo.getStatus().toString().equalsIgnoreCase("1"))
                            {
                                paymentInfoBean = apiPaymentInfo.getResult();
                                setListner();
                            }
                            else
                                CommonUtils.toast(context, ""+apiPaymentInfo.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }
}
