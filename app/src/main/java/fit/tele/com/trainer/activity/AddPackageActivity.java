package fit.tele.com.trainer.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.apiBase.FetchServiceBase;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.databinding.ActivityAddPackageBinding;
import fit.tele.com.trainer.modelBean.LoginBean;
import fit.tele.com.trainer.modelBean.ModelBean;
import fit.tele.com.trainer.modelBean.PackageBean;
import fit.tele.com.trainer.utils.CommonUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddPackageActivity extends BaseActivity implements View.OnClickListener {

    ActivityAddPackageBinding binding;
    private String strheader = "Package";
    private PackageBean packageBean;
    private List<String> list;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_add_package;
    }

    @Override
    public void init() {
        binding = (ActivityAddPackageBinding) getBindingObj();

        if(getIntent() != null && getIntent().hasExtra("header"))
            strheader = getIntent().getStringExtra("header");
        if(getIntent() != null && getIntent().hasExtra("packageBean"))
            packageBean = getIntent().getParcelableExtra("packageBean");

        binding.txtHeaderName.setText(strheader);

        binding.imgSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        list = new ArrayList<>();
        list.add("1 Month");
        list.add("3 Month");
        list.add("6 Month");
        list.add("9 Month");
        list.add("12 Month");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_custom_spinner, list);
        binding.spiDuration.setAdapter(adapter);
        binding.txtAdd.setOnClickListener(this);

        if (!strheader.equalsIgnoreCase("New Package"))
            setData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_add:
                if (validation())
                {
                    if (strheader.equalsIgnoreCase("New Package"))
                        callSetPackageApi();
                    else
                        callEditPackageApi();
                }
                break;
        }
    }

    private void setData()
    {
        if (packageBean != null) {
            if (packageBean.getPrice() != null && !TextUtils.isEmpty(packageBean.getPrice().toString()))
                binding.inputPrice.setText(packageBean.getPrice().trim());
            if (packageBean.getDetails() != null && !TextUtils.isEmpty(packageBean.getDetails().toString()))
                binding.inputDescrption.setText(packageBean.getDetails().trim());
            if (packageBean.getTitle() != null && !TextUtils.isEmpty(packageBean.getTitle().toString())) {
                for (int i=0;i<list.size();i++) {
                    if (list.get(i).equalsIgnoreCase(packageBean.getTitle()))
                        binding.spiDuration.setSelection(i);
                }
            }
        }
    }

    private boolean validation() {
        if (binding.inputPrice.getText().toString().isEmpty()) {
            CommonUtils.toast(context,"Please enter Price!");
            return false;
        } else if (binding.inputDescrption.getText().toString().isEmpty()) {
            CommonUtils.toast(context,"Please enter Description!");
            return false;
        } else
            return true;
    }

    private void callSetPackageApi() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            HashMap<String, String> map = new HashMap<>();
            map.put("title", binding.spiDuration.getSelectedItem().toString());
            map.put("price", binding.inputPrice.getText().toString());
            map.put("details", binding.inputDescrption.getText().toString());
            map.put("validity", binding.spiDuration.getSelectedItem().toString());

            Observable<ModelBean<PackageBean>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).setPackage(map);
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<PackageBean>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("callSetPackageeApi"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNext(ModelBean<PackageBean> apiFoodBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (apiFoodBean.getStatus() == 1) {
                                Intent intent = new Intent(context, ProfileActivity.class);
                                startActivity(intent);
                            }
                            else
                                CommonUtils.toast(context, apiFoodBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }

    private void callEditPackageApi() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            HashMap<String, String> map = new HashMap<>();
            map.put("pack_id", packageBean.getId());
            map.put("title", binding.spiDuration.getSelectedItem().toString());
            map.put("price", binding.inputPrice.getText().toString());
            map.put("details", binding.inputDescrption.getText().toString());
            map.put("validity", binding.spiDuration.getSelectedItem().toString());

            Observable<ModelBean<PackageBean>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).setEditPackage(map);
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<PackageBean>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("callEditPackageeApi"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNext(ModelBean<PackageBean> apiFoodBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (apiFoodBean.getStatus() == 1) {
                                Intent intent = new Intent(context, ProfileActivity.class);
                                startActivity(intent);
                            }
                            else
                                CommonUtils.toast(context, apiFoodBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }
}
