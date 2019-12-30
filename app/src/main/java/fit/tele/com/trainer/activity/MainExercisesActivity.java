package fit.tele.com.trainer.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.apiBase.FetchServiceBase;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.databinding.ActivityMainExercisesBinding;
import fit.tele.com.trainer.modelBean.CategoryBean;
import fit.tele.com.trainer.modelBean.ModelBean;
import fit.tele.com.trainer.utils.CommonUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainExercisesActivity extends BaseActivity implements View.OnClickListener {

    ActivityMainExercisesBinding binding;
    private ArrayList<CategoryBean> categoryBeans;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main_exercises;
    }

    private void setListner() {
        binding.imgSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.llProfile.setOnClickListener(this);
        binding.llNutrition.setOnClickListener(this);
        binding.llGoals.setOnClickListener(this);
        binding.llCustomers.setOnClickListener(this);

        binding.rlGym.setOnClickListener(this);
        binding.rlCrossfit.setOnClickListener(this);
        binding.rlHiit.setOnClickListener(this);
        binding.rlYoga.setOnClickListener(this);
        binding.txtTrainers.setOnClickListener(this);

        callCategoryApi();
    }

    private void setData() {
        for (int i=0; i<categoryBeans.size();i++) {
            if (categoryBeans.get(i).getCatName().equalsIgnoreCase("CrossFit")) {
                if (categoryBeans != null && categoryBeans.get(i).getCatImageUrl() != null && !TextUtils.isEmpty(categoryBeans.get(i).getCatImageUrl())) {
                    Picasso.with(this)
                            .load(categoryBeans.get(i).getCatImageUrl())
                            .resize(500, 500)
                            .onlyScaleDown()
                            .into(binding.imgCrossfit);
                }
            }
            if (categoryBeans.get(i).getCatName().equalsIgnoreCase("gym")) {
                if (categoryBeans != null && categoryBeans.get(i).getCatImageUrl() != null && !TextUtils.isEmpty(categoryBeans.get(i).getCatImageUrl())) {
                    Picasso.with(this)
                            .load(categoryBeans.get(i).getCatImageUrl())
                            .resize(500, 500)
                            .onlyScaleDown()
                            .into(binding.imgGym);
                }
            }
            if (categoryBeans.get(i).getCatName().equalsIgnoreCase("hiit")) {
                if (categoryBeans != null && categoryBeans.get(i).getCatImageUrl() != null && !TextUtils.isEmpty(categoryBeans.get(i).getCatImageUrl())) {
                    Picasso.with(this)
                            .load(categoryBeans.get(i).getCatImageUrl())
                            .resize(500, 500)
                            .onlyScaleDown()
                            .into(binding.imgHiit);
                }
            }
            if (categoryBeans.get(i).getCatName().equalsIgnoreCase("yoga")) {
                if (categoryBeans != null && categoryBeans.get(i).getCatImageUrl() != null && !TextUtils.isEmpty(categoryBeans.get(i).getCatImageUrl())) {
                    Picasso.with(this)
                            .load(categoryBeans.get(i).getCatImageUrl())
                            .resize(500, 500)
                            .onlyScaleDown()
                            .into(binding.imgYoga);
                }
            }
        }
    }

    @Override
    public void init() {
        binding = (ActivityMainExercisesBinding) getBindingObj();

        setListner();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_customers :
                intent = new Intent(context, CustomersActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_nutrition:
                intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_profile:
                intent = new Intent(context, CustomerProfileActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_goals:
                intent = new Intent(context, GoalsActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;
            case R.id.rl_gym:
                intent = new Intent(context, GymActivity.class);
                intent.putExtra("exerciseType","1");
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.rl_crossfit:
                intent = new Intent(context, CrossFitActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.rl_hiit:
                intent = new Intent(context, GymActivity.class);
                intent.putExtra("exerciseType","3");
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.rl_yoga:
                intent = new Intent(context, YogaActivity.class);
                intent.putExtra("exerciseType","4");
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;
        }
    }

    private void callCategoryApi() {
        if (CommonUtils.isInternetOn(context)) {

            Observable<ModelBean<ArrayList<CategoryBean>>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).getCategoryApi();
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<ArrayList<CategoryBean>>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("callCategoryApi"," "+e);
                        }

                        @Override
                        public void onNext(ModelBean<ArrayList<CategoryBean>> apiExercisesBean) {
                            if (apiExercisesBean.getStatus().toString().equalsIgnoreCase("1") )
                            {
                                categoryBeans = apiExercisesBean.getResult();
                                setData();
                            }
                            else
                                CommonUtils.toast(context, ""+apiExercisesBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }
}
