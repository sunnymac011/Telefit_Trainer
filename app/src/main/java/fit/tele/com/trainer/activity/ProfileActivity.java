package fit.tele.com.trainer.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.adapter.PackageAdapter;
import fit.tele.com.trainer.apiBase.FetchServiceBase;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.databinding.ActivityProfileBinding;
import fit.tele.com.trainer.modelBean.LoginBean;
import fit.tele.com.trainer.modelBean.ModelBean;
import fit.tele.com.trainer.modelBean.PackageBean;
import fit.tele.com.trainer.utils.CircleTransform;
import fit.tele.com.trainer.utils.CommonUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProfileActivity extends BaseActivity implements View.OnClickListener{

    private ActivityProfileBinding binding;
    private LoginBean saveLogiBean;
    private LinearLayout ll_add_package;
    private RelativeLayout rl_notifications, rl_logout, rl_about, rl_app_preferences, rl_help, rl_privacy, rl_country;
    private TextView txt_themes_count, txt_selected_country;
    private RecyclerView rv_packages;
    private PackageAdapter packageAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_profile;
    }

    @Override
    public void init() {

        binding = (ActivityProfileBinding) getBindingObj();
//        ProfileActivityTheme profileActivityTheme = new ProfileActivityTheme();
//        profileActivityTheme.setTheme(binding, ProfileActivity.this);
        setListner();
        setData();
    }

    private void setListner() {

        rl_logout = (RelativeLayout) findViewById(R.id.rl_logout);
        rl_notifications = (RelativeLayout) findViewById(R.id.rl_notifications);
        rl_about = (RelativeLayout) findViewById(R.id.rl_about);
        rl_app_preferences = (RelativeLayout) findViewById(R.id.rl_app_preferences);
        rl_help = (RelativeLayout) findViewById(R.id.rl_help);
        rl_privacy = (RelativeLayout) findViewById(R.id.rl_privacy);
        rl_country = (RelativeLayout) findViewById(R.id.rl_country);
        ll_add_package = (LinearLayout) findViewById(R.id.ll_add_package);
        txt_selected_country = (TextView) findViewById(R.id.txt_selected_country);
        txt_themes_count = (TextView) findViewById(R.id.txt_themes_count);
        txt_themes_count.setText(preferences.getTheme());

        binding.llCustomers.setOnClickListener(this);
        binding.llSocial.setOnClickListener(this);

        binding.txtEdit.setOnClickListener(this);
        rl_app_preferences.setOnClickListener(this);
        rl_about.setOnClickListener(this);
        rl_help.setOnClickListener(this);
        rl_privacy.setOnClickListener(this);
        rl_country.setOnClickListener(this);
        rl_notifications.setOnClickListener(this);
        ll_add_package.setOnClickListener(this);

        binding.llManageTab.setOnClickListener(this);
        binding.llPackagesTab.setOnClickListener(this);
        binding.llMoreTab.setOnClickListener(this);


        rl_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.cleanAlltoken();
                Intent intent = new Intent(context, WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setData(){
        saveLogiBean = preferences.getUserDataPref();
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
        {
            if (saveLogiBean != null && saveLogiBean.getlName() != null
                    && !TextUtils.isEmpty(saveLogiBean.getlName()))
                binding.txtName.setText(saveLogiBean.getName()+" "+saveLogiBean.getlName());
            else
                binding.txtName.setText(saveLogiBean.getName());
        }
        if (saveLogiBean != null && saveLogiBean.getHeight() != null
                && !TextUtils.isEmpty(saveLogiBean.getHeight()))
        {
            if (saveLogiBean != null && saveLogiBean.getWeight() != null
                    && !TextUtils.isEmpty(saveLogiBean.getWeight()))
                binding.txtHeightWeight.setText(saveLogiBean.getHeight()+"ft / "+saveLogiBean.getWeight()+" kg");
        }

        if (saveLogiBean != null && saveLogiBean.getDob() != null
                && !TextUtils.isEmpty(saveLogiBean.getDob()))
            binding.txtAge.setText(""+getAge(saveLogiBean.getDob()));

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.ll_customers :
                intent = new Intent(context, CustomersActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_social :
                intent = new Intent(context, SocialActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.txt_edit :
                intent = new Intent(context, EditProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.ll_manage_tab :
                binding.vf.setDisplayedChild(0);
                binding.txtManageTab.setTextColor(getResources().getColor(R.color.white));
                binding.viewManage.setVisibility(View.VISIBLE);
                binding.txtPackagesTab.setTextColor(getResources().getColor(R.color.light_gray));
                binding.viewPackages.setVisibility(View.GONE);
                binding.txtMoreTab.setTextColor(getResources().getColor(R.color.light_gray));
                binding.viewMore.setVisibility(View.GONE);
                break;

            case R.id.ll_packages_tab :
                binding.vf.setDisplayedChild(1);
                binding.txtManageTab.setTextColor(getResources().getColor(R.color.light_gray));
                binding.viewManage.setVisibility(View.GONE);
                binding.txtPackagesTab.setTextColor(getResources().getColor(R.color.white));
                binding.viewPackages.setVisibility(View.VISIBLE);
                binding.txtMoreTab.setTextColor(getResources().getColor(R.color.light_gray));
                binding.viewMore.setVisibility(View.GONE);
                getPackages();
                break;

            case R.id.ll_more_tab :
                binding.vf.setDisplayedChild(2);
                binding.txtManageTab.setTextColor(getResources().getColor(R.color.light_gray));
                binding.viewManage.setVisibility(View.GONE);
                binding.txtPackagesTab.setTextColor(getResources().getColor(R.color.light_gray));
                binding.viewPackages.setVisibility(View.GONE);
                binding.txtMoreTab.setTextColor(getResources().getColor(R.color.white));
                binding.viewMore.setVisibility(View.VISIBLE);
                break;

            case R.id.rl_about :
                intent = new Intent(context, AboutTeleFitActivity.class);
                startActivity(intent);
                break;

            case R.id.rl_app_preferences:
                intent = new Intent(context, AppPreferencesActivity.class);
                startActivity(intent);
                break;

            case R.id.rl_notifications :
                intent = new Intent(context, NotificationSettingsActiviry.class);
                startActivity(intent);
                break;
//
            case R.id.rl_country :
                intent = new Intent(context, DatabaseCountryActivity.class);
                startActivity(intent);
                break;

            case R.id.rl_privacy :
                intent = new Intent(context, PrivacyActivity.class);
                startActivity(intent);
                break;

            case R.id.rl_help:
                intent = new Intent(context, HelpActivity.class);
                startActivity(intent);
                break;

            case R.id.ll_add_package:
                intent = new Intent(context, AddPackageActivity.class);
                intent.putExtra("header","New Package");
                startActivity(intent);
                break;
        }
    }

    private void getPackages() {
        rv_packages = (RecyclerView) findViewById(R.id.rv_packages);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rv_packages.setLayoutManager(linearLayoutManager);

        if (packageAdapter == null) {
            packageAdapter = new PackageAdapter(context, new PackageAdapter.ClickListener() {
                @Override
                public void onClick(PackageBean packageBean) {
                    Intent intent = new Intent(context, AddPackageActivity.class);
                    intent.putExtra("header","Edit Package");
                    intent.putExtra("packageBean",packageBean);
                    startActivity(intent);
                }
            });
        }
        rv_packages.setAdapter(packageAdapter);

        callGetPackageApi();
    }

    private void callGetPackageApi() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);

            Observable<ModelBean<ArrayList<PackageBean>>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).getPackage();
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<ArrayList<PackageBean>>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("callGetPackageApi"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNext(ModelBean<ArrayList<PackageBean>> apiFoodBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (apiFoodBean.getResult() != null && apiFoodBean.getResult().size() > 0) {
                                packageAdapter.addAllList(apiFoodBean.getResult());
                            }
                            else
                                CommonUtils.toast(context, apiFoodBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        ProfileActivityTheme profileActivityTheme = new ProfileActivityTheme();
//        profileActivityTheme.setTheme(binding, ProfileActivity.this);
//        txt_themes_count.setText(preferences.getTheme());
        if (preferences.getCountyPref() != null && !TextUtils.isEmpty(preferences.getCountyPref()))
            txt_selected_country.setText(preferences.getCountyPref());
        else
            txt_selected_country.setText("");
    }

    private int getAge(String dobString){

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month+1, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        return age;
    }
}
