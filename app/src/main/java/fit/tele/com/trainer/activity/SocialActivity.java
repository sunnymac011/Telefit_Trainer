package fit.tele.com.trainer.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.adapter.ActivityAdapter;
import fit.tele.com.trainer.apiBase.FetchServiceBase;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.databinding.ActivitySocialBinding;
import fit.tele.com.trainer.modelBean.CreatePostBean;
import fit.tele.com.trainer.modelBean.ModelBean;
import fit.tele.com.trainer.utils.CommonUtils;
import fit.tele.com.trainer.utils.Preferences;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SocialActivity extends BaseActivity implements View.OnClickListener {

    ActivitySocialBinding binding;
    Preferences preferences;
    LinearLayout ll_create_post;
    RecyclerView rv_activities;
    LinearLayoutManager linearLayoutManager;
    ActivityAdapter activityAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_social;
    }

    @Override
    public void init() {
        binding = (ActivitySocialBinding)getBindingObj();
        preferences = new Preferences(this);
        setListner();
        setActivity();
    }



    public void setListner() {
        binding.imgSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ll_create_post = findViewById(R.id.ll_create_post);
        rv_activities = (RecyclerView)findViewById(R.id.rv_activities);
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rv_activities.setLayoutManager(linearLayoutManager);

        ll_create_post.setOnClickListener(this);
        binding.txtSetting.setOnClickListener(this);

        binding.llCustomers.setOnClickListener(this);
        binding.llProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_create_post:
                Intent in1 = new Intent(SocialActivity.this,CreatePost.class);
                startActivityForResult(in1, 1001);
                //    startActivity(in1);
                break;

            case R.id.txt_setting:
                Intent in2 = new Intent(SocialActivity.this,SocialSetting.class);
                startActivity(in2);
                break;

            case R.id.ll_profile:
                intent = new Intent(context, ProfileActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_customers:
                intent = new Intent(context, SocialActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;
        }
    }

    public void setActivity(){
        if(activityAdapter==null){
            activityAdapter = new ActivityAdapter(context, preferences.getUserDataPref().getId(), rv_activities, new ActivityAdapter.ActivitiesListner() {
                @Override
                public void onClick(int id, CreatePostBean bean) {
                    Log.w("Go","social adaper");
                    Intent in = new Intent(context, PostDetailActivity.class);
                    in.putExtra("postDetail",bean);
                    startActivity(in);
                }

                @Override
                public void onDeletClick(String id, CreatePostBean bean) {
                    deletePost(id);
                }
            });
            rv_activities.setAdapter(activityAdapter);
        }
        binding.txtSetting.setVisibility(View.VISIBLE);
        binding.txtNew.setVisibility(View.GONE);

        getAllActivities();
    }

    private void getAllActivities() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            Map<String, String> map = new HashMap<>();
            Observable<ModelBean<ArrayList<CreatePostBean>>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).getAllActivities(map);
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<ArrayList<CreatePostBean>>>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("callRoutinePlanDetails"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }
                        @Override
                        public void onNext(ModelBean<ArrayList<CreatePostBean>> loginBean) {
                            binding.progress.setVisibility(View.GONE);
                            if(loginBean.getStatus()==1){
                                //   binding.progress.setVisibility(View.GONE);
                                if(loginBean.getResult()!=null) {
                                    activityAdapter.clearAll();
                                    activityAdapter.addAllList(loginBean.getResult());
                                }
                            }
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }

    private void deletePost(String post_id) {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            Map<String, String> map = new HashMap<>();
            map.put("post_id",post_id);

            Observable<ModelBean<ArrayList<CreatePostBean>>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).deletePost(map);
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<ArrayList<CreatePostBean>>>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("deletePost"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }
                        @Override
                        public void onNext(ModelBean<ArrayList<CreatePostBean>> loginBean) {
                            binding.progress.setVisibility(View.GONE);
                            CommonUtils.toast(context, loginBean.getMessage());
                            if(loginBean.getStatus()==1){
                                activityAdapter.clearAll();
                                getAllActivities();
                            }
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001 && resultCode == Activity.RESULT_OK){

                getAllActivities();


        }
    }
}
