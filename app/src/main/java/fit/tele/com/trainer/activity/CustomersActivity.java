package fit.tele.com.trainer.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.adapter.CustomersAdapter;
import fit.tele.com.trainer.adapter.RequestAdapter;
import fit.tele.com.trainer.apiBase.FetchServiceBase;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.databinding.ActivityCustomersBinding;
import fit.tele.com.trainer.modelBean.CountryBean;
import fit.tele.com.trainer.modelBean.CustomerProfileBean;
import fit.tele.com.trainer.modelBean.LoginBean;
import fit.tele.com.trainer.modelBean.ModelBean;
import fit.tele.com.trainer.utils.CommonUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CustomersActivity extends BaseActivity implements View.OnClickListener {

    ActivityCustomersBinding binding;
    private RecyclerView rv_customer_list, rv_request_list;
    private LinearLayoutManager linearLayoutManager;
    private int strSelectedTab = 1;
    private CustomersAdapter customersAdapter;
    private RequestAdapter requestAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_customers;
    }

    @Override
    public void init() {
        binding = (ActivityCustomersBinding) getBindingObj();
        preferences.cleanCustomerIddata();
        setListner();
    }

    public void setListner(){
        rv_customer_list = (RecyclerView)findViewById(R.id.rv_customer_list);
        rv_request_list = (RecyclerView)findViewById(R.id.rv_request_list);
        binding.llProfile.setOnClickListener(this);
        binding.llSocial.setOnClickListener(this);
        binding.llCustomers.setOnClickListener(this);

        binding.llNotificationTab.setOnClickListener(this);
        binding.llRequestTab.setOnClickListener(this);
        binding.llMessageTab.setOnClickListener(this);
        setCustomers();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_profile:
                intent = new Intent(context, ProfileActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_social:
                intent = new Intent(context, SocialActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_notification_tab:
                setCustomers();
                break;

            case R.id.ll_message_tab:
                setMessage();
                break;

            case R.id.ll_request_tab:
                setRequest();
                break;
        }
    }

    private void setCustomers() {
        binding.vf.setDisplayedChild(0);
        strSelectedTab = 1;
        binding.txtNotificationTab.setTextColor(getResources().getColor(R.color.white));
        binding.viewNotification.setVisibility(View.VISIBLE);
        binding.txtMessageTab.setTextColor(getResources().getColor(R.color.light_gray));
        binding.viewMessage.setVisibility(View.GONE);
        binding.txtRequestTab.setTextColor(getResources().getColor(R.color.light_gray));
        binding.viewRequest.setVisibility(View.GONE);

        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rv_customer_list.setLayoutManager(linearLayoutManager);

        customersAdapter = new CustomersAdapter(context, new CustomersAdapter.ClickListener() {
            @Override
            public void onClickMsg(String cust_id) {

            }

            @Override
            public void onClickVideo(String cust_id) {

            }

            @Override
            public void onClick(String cust_id) {
                preferences.saveCustomerIdData(cust_id);
                Intent intent = new Intent(CustomersActivity.this,CustomerProfileActivity.class);
                startActivity(intent);
            }
        });
        rv_customer_list.setAdapter(customersAdapter);
        getCustomersList();
    }

    private void setRequest() {
        binding.vf.setDisplayedChild(1);
        strSelectedTab = 2;
        binding.txtNotificationTab.setTextColor(getResources().getColor(R.color.light_gray));
        binding.viewNotification.setVisibility(View.GONE);
        binding.txtMessageTab.setTextColor(getResources().getColor(R.color.light_gray));
        binding.viewMessage.setVisibility(View.GONE);
        binding.txtRequestTab.setTextColor(getResources().getColor(R.color.white));
        binding.viewRequest.setVisibility(View.VISIBLE);

        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rv_request_list.setLayoutManager(linearLayoutManager);

        if (requestAdapter == null) {
            requestAdapter = new RequestAdapter(context, new RequestAdapter.ClickListener() {
                @Override
                public void onClickAccept(String cust_id) {
                    setRequestAccept(cust_id,"1");
                }

                @Override
                public void onClickDeny(String cust_id) {
                    setRequestAccept(cust_id,"2");
                }
            });
            rv_request_list.setAdapter(requestAdapter);
        }
        getRequestList();
    }

    private void setMessage() {
        binding.vf.setDisplayedChild(2);
        strSelectedTab = 3;
        binding.txtNotificationTab.setTextColor(getResources().getColor(R.color.light_gray));
        binding.viewNotification.setVisibility(View.GONE);
        binding.txtMessageTab.setTextColor(getResources().getColor(R.color.white));
        binding.viewMessage.setVisibility(View.VISIBLE);
        binding.txtRequestTab.setTextColor(getResources().getColor(R.color.light_gray));
        binding.viewRequest.setVisibility(View.GONE);

        rv_customer_list = (RecyclerView)findViewById(R.id.rv_customer_list);
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rv_customer_list.setLayoutManager(linearLayoutManager);
    }

    private void getRequestList() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            Observable<ModelBean<ArrayList<CustomerProfileBean>>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).getRequestList();
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<ArrayList<CustomerProfileBean>>>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("getRequestList"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }
                        @Override
                        public void onNext(ModelBean<ArrayList<CustomerProfileBean>> loginBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (loginBean.getStatus() == 1)
                            {
                                requestAdapter.clearAll();
                                requestAdapter.addAllList(loginBean.getResult());
                            }
                            else
                                CommonUtils.toast(context,loginBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }

    private void setRequestAccept(String custId, String accept) {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            Map<String, String> map = new HashMap<>();
            map.put("cust_id", custId);
            map.put("is_accept", accept);
            Observable<ModelBean<ArrayList<CustomerProfileBean>>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).getRequestAccept(map);
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<ArrayList<CustomerProfileBean>>>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("setRequestAccept"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }
                        @Override
                        public void onNext(ModelBean<ArrayList<CustomerProfileBean>> loginBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (loginBean.getStatus() == 1)
                            {
                                requestAdapter.clearAll();
                                requestAdapter.addAllList(loginBean.getResult());
                            }
                            else
                                CommonUtils.toast(context,loginBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }

    private void getCustomersList() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            Observable<ModelBean<ArrayList<CustomerProfileBean>>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).getCustomersList();
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<ArrayList<CustomerProfileBean>>>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("getCustomersList"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }
                        @Override
                        public void onNext(ModelBean<ArrayList<CustomerProfileBean>> loginBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (loginBean.getStatus() == 1)
                                customersAdapter.addAllList(loginBean.getResult());
                            else
                                CommonUtils.toast(context,loginBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }
}
