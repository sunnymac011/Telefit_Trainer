package fit.tele.com.trainer.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.adapter.RoutinePlanAdapter;
import fit.tele.com.trainer.apiBase.FetchServiceBase;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.databinding.ActivityFitnessBinding;
import fit.tele.com.trainer.modelBean.CategoryBean;
import fit.tele.com.trainer.modelBean.ModelBean;
import fit.tele.com.trainer.modelBean.RoutinePlanBean;
import fit.tele.com.trainer.utils.CircleTransform;
import fit.tele.com.trainer.utils.CommonUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FitnessActivity extends BaseActivity implements View.OnClickListener {

    ActivityFitnessBinding binding;
    private RoutinePlanAdapter routinePlanAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_fitness;
    }

    @Override
    public void init() {
        binding = (ActivityFitnessBinding) getBindingObj();
    }

    private void setListner() {
        binding.llProfile.setOnClickListener(this);
        binding.llNutrition.setOnClickListener(this);
        binding.llGoals.setOnClickListener(this);
        binding.llCustomers.setOnClickListener(this);

        binding.llAddPlan.setOnClickListener(this);
        binding.txtExplore.setOnClickListener(this);

        binding.imgCrossfit.setOnClickListener(this);
        binding.imgGym.setOnClickListener(this);
        binding.imgHiit.setOnClickListener(this);
        binding.imgYoga.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.rvFuturePlans.setLayoutManager(linearLayoutManager);

        if (routinePlanAdapter == null) {
            routinePlanAdapter = new RoutinePlanAdapter(context, binding.rvFuturePlans, new RoutinePlanAdapter.ClickListener() {
                @Override
                public void onClick(String plan_id) {
                    Intent intent = new Intent(FitnessActivity.this,PlayVideoActivity.class);
                    intent.putExtra("plan_id",plan_id);
                    startActivity(intent);
                }

                @Override
                public void onLongClick(String plan_id) {
                    preferences.setIsUpdatePlan(plan_id);
                    Intent intent = new Intent(FitnessActivity.this,UpdateRoutineActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onDelete(String plan_id) {
                    deleteRoutine(plan_id);
                }
            });
        }
        binding.rvFuturePlans.setAdapter(routinePlanAdapter);
        routinePlanAdapter.clearAll();

        callRoutinePlanApi();
        callCategoryApi();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_customers:
                intent = new Intent(context, MainActivity.class);
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
            case R.id.ll_add_plan:
                preferences.cleanUpdatePlan();
                intent = new Intent(FitnessActivity.this,CreateRoutineActivity.class);
                startActivity(intent);
                break;

            case R.id.txt_explore:
                intent = new Intent(context, MainExercisesActivity.class);
                startActivity(intent);
                break;

            case R.id.img_gym:
                intent = new Intent(context, GymActivity.class);
                intent.putExtra("exerciseType","1");
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.img_crossfit:
                intent = new Intent(context, CrossFitActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.img_hiit:
                intent = new Intent(context, GymActivity.class);
                intent.putExtra("exerciseType","3");
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.img_yoga:
                intent = new Intent(context, YogaActivity.class);
                intent.putExtra("exerciseType","4");
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;
        }
    }

    private void callRoutinePlanApi() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            Map<String, String> map = new HashMap<>();
            map.put("cust_id", preferences.getCustomerIdPref());

            Observable<ModelBean<ArrayList<RoutinePlanBean>>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).getRoutinePlansApi(map);
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<ArrayList<RoutinePlanBean>>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("callRoutinePlanApi"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNext(ModelBean<ArrayList<RoutinePlanBean>> apiExercisesBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (apiExercisesBean.getStatus().toString().equalsIgnoreCase("1") )
                            {
                                if (apiExercisesBean.getResult() != null) {
                                    if (apiExercisesBean.getResult().size() > 0)
                                        routinePlanAdapter.addAllList(apiExercisesBean.getResult());
                                }
                            }
                            else
                                CommonUtils.toast(context, ""+apiExercisesBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }

    private void deleteRoutine(String plan_id) {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            HashMap<String, String> map = new HashMap<>();
            map.put("routine_p_id",plan_id);

            Observable<ModelBean<ArrayList<RoutinePlanBean>>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).deleteRoutine(map);
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<ArrayList<RoutinePlanBean>>>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Log.e("deleteRoutine"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }
                        @Override
                        public void onNext(ModelBean<ArrayList<RoutinePlanBean>> arrayListModelBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (arrayListModelBean.getStatus().toString().equalsIgnoreCase("1") )
                            {
                                routinePlanAdapter.clearAll();
                                if (arrayListModelBean.getResult() != null) {
                                    if (arrayListModelBean.getResult().size() > 0)
                                        routinePlanAdapter.addAllList(arrayListModelBean.getResult());
                                }
                            }
                            else
                                CommonUtils.toast(context, ""+arrayListModelBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
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
                                for (int i=0; i<apiExercisesBean.getResult().size();i++) {
                                    if (apiExercisesBean.getResult().get(i).getCatName().equalsIgnoreCase("CrossFit")) {
                                        if (apiExercisesBean.getResult() != null && apiExercisesBean.getResult().get(i).getCatImageUrl() != null && !TextUtils.isEmpty(apiExercisesBean.getResult().get(i).getCatImageUrl())) {
                                            Picasso.with(context)
                                                    .load(apiExercisesBean.getResult().get(i).getCatImageUrl())
                                                    .transform(new CircleTransform())
                                                    .resize(100, 100)
                                                    .onlyScaleDown()
                                                    .into(binding.imgCrossfit);
                                        }
                                    }
                                    if (apiExercisesBean.getResult().get(i).getCatName().equalsIgnoreCase("gym")) {
                                        if (apiExercisesBean.getResult() != null && apiExercisesBean.getResult().get(i).getCatImageUrl() != null && !TextUtils.isEmpty(apiExercisesBean.getResult().get(i).getCatImageUrl())) {
                                            Picasso.with(context)
                                                    .load(apiExercisesBean.getResult().get(i).getCatImageUrl())
                                                    .transform(new CircleTransform())
                                                    .resize(100, 100)
                                                    .onlyScaleDown()
                                                    .into(binding.imgGym);
                                        }
                                    }
                                    if (apiExercisesBean.getResult().get(i).getCatName().equalsIgnoreCase("hiit")) {
                                        if (apiExercisesBean.getResult() != null && apiExercisesBean.getResult().get(i).getCatImageUrl() != null && !TextUtils.isEmpty(apiExercisesBean.getResult().get(i).getCatImageUrl())) {
                                            Picasso.with(context)
                                                    .load(apiExercisesBean.getResult().get(i).getCatImageUrl())
                                                    .transform(new CircleTransform())
                                                    .resize(100, 100)
                                                    .onlyScaleDown()
                                                    .into(binding.imgHiit);
                                        }
                                    }
                                    if (apiExercisesBean.getResult().get(i).getCatName().equalsIgnoreCase("yoga")) {
                                        if (apiExercisesBean.getResult() != null && apiExercisesBean.getResult().get(i).getCatImageUrl() != null && !TextUtils.isEmpty(apiExercisesBean.getResult().get(i).getCatImageUrl())) {
                                            Picasso.with(context)
                                                    .load(apiExercisesBean.getResult().get(i).getCatImageUrl())
                                                    .transform(new CircleTransform())
                                                    .resize(100, 100)
                                                    .onlyScaleDown()
                                                    .into(binding.imgYoga);
                                        }
                                    }
                                }
                            }
                            else
                                CommonUtils.toast(context, ""+apiExercisesBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }

    @Override
    protected void onResume() {
        setListner();
        super.onResume();
    }
}
