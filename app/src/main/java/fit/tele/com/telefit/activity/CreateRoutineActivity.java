package fit.tele.com.telefit.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import fit.tele.com.telefit.R;
import fit.tele.com.telefit.adapter.ExercisesDragNDropAdapter;
import fit.tele.com.telefit.apiBase.FetchServiceBase;
import fit.tele.com.telefit.base.BaseActivity;
import fit.tele.com.telefit.databinding.ActivityCreateRoutineBinding;
import fit.tele.com.telefit.helper.OnStartDragListener;
import fit.tele.com.telefit.helper.SimpleItemTouchHelperCallback;
import fit.tele.com.telefit.modelBean.CreatePlanApiBean;
import fit.tele.com.telefit.modelBean.ExeDetl;
import fit.tele.com.telefit.modelBean.ExercisesListBean;
import fit.tele.com.telefit.modelBean.ModelBean;
import fit.tele.com.telefit.utils.CommonUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CreateRoutineActivity extends BaseActivity implements View.OnClickListener, OnStartDragListener {

    ActivityCreateRoutineBinding binding;
    private ExercisesDragNDropAdapter exercisesDragNDropAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private ArrayList<ExercisesListBean> exercisesListBeans = new ArrayList<>();
    private CreatePlanApiBean createPlanApiBean;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_create_routine;
    }

    @Override
    public void init() {
        binding = (ActivityCreateRoutineBinding) getBindingObj();
        createPlanApiBean = new CreatePlanApiBean();
        setListner();
    }

    private void setListner() {
        binding.imgSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.llProfile.setOnClickListener(this);
        binding.llNutrition.setOnClickListener(this);
        binding.llGoals.setOnClickListener(this);
        binding.llFitness.setOnClickListener(this);
        binding.llAddPlan.setOnClickListener(this);
        binding.txtAdd.setOnClickListener(this);

        ArrayAdapter<String> spinnerDaysArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.days));
        spinnerDaysArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spiDay.setAdapter(spinnerDaysArrayAdapter);

        ArrayAdapter<String> spinnerRoutineTypeArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.routine_type));
        spinnerRoutineTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spiType.setAdapter(spinnerRoutineTypeArrayAdapter);

        ArrayAdapter<String> spinnerDifficultyLevelArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.difficulty_level));
        spinnerDifficultyLevelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spiLevel.setAdapter(spinnerDifficultyLevelArrayAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.rvExercises.setLayoutManager(linearLayoutManager);
        binding.rvExercises.setHasFixedSize(true);
        if (exercisesDragNDropAdapter == null) {
            exercisesDragNDropAdapter = new ExercisesDragNDropAdapter(context, this);
        }
        binding.rvExercises.setAdapter(exercisesDragNDropAdapter);
        exercisesDragNDropAdapter.clearAll();
        if (preferences.getRoutineDataPref() != null) {
            Gson gson = new Gson();
            exercisesListBeans = gson.fromJson(preferences.getRoutineDataPref(), new TypeToken<ArrayList<ExercisesListBean>>(){}.getType());
            exercisesDragNDropAdapter.addAllList(exercisesListBeans);
        }
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(exercisesDragNDropAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(binding.rvExercises);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.ll_nutrition:
                intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_profile:
                intent = new Intent(context, ProfileActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_goals:
                intent = new Intent(context, GoalsActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_fitness:
                intent = new Intent(context, FitnessActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_add_plan:
                intent = new Intent(context, MainExercisesActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.txt_add:
                    if (TextUtils.isEmpty(binding.inputRoutineName.getText().toString()))
                        CommonUtils.toast(context, "Please enter Routine Name");
                    else {
                        createPlanApiBean.setRoutineName(binding.inputRoutineName.getText().toString().trim());
                        createPlanApiBean.setDayOfTheWeek(binding.spiDay.getSelectedItem().toString());
                        createPlanApiBean.setRoutineType(binding.spiType.getSelectedItem().toString());
                        createPlanApiBean.setDifficultyLevel(binding.spiLevel.getSelectedItem().toString());
                        createPlanApiBean.setDayFlag(""+(binding.spiDay.getSelectedItemPosition()+1));
                        ExeDetl exeDetl;
                        ArrayList<ExeDetl> exeDetls;
                        if (exercisesDragNDropAdapter != null) {
                            if (exercisesDragNDropAdapter.getAllData().size() > 0) {
                                exeDetls = new ArrayList<>();
                                for (int i=0;i<exercisesDragNDropAdapter.getAllData().size();i++)
                                {
                                    exeDetl = new ExeDetl();
                                    exeDetl.setExeid(exercisesDragNDropAdapter.getAllData().get(i).getId());
                                    exeDetl.setSets(exercisesDragNDropAdapter.getAllData().get(i).getSets());
                                    exeDetl.setReps(exercisesDragNDropAdapter.getAllData().get(i).getReps());
                                    exeDetl.setTimebetweenreps(exercisesDragNDropAdapter.getAllData().get(i).getTime_between_sets());
                                    exeDetls.add(exeDetl);
                                }

                                if (exeDetls.size() > 0)
                                {
                                    createPlanApiBean.setExeId(exeDetls);
                                    callCreateApiApi();
                                }
                                else
                                    CommonUtils.toast(context, "Please add exercise!");
                            }
                            else
                                CommonUtils.toast(context, "Please add exercise!");
                        }
                    }
                break;
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    private void callCreateApiApi() {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);

            Observable<ModelBean<ExercisesListBean>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).createRoutineApi(createPlanApiBean);
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<ExercisesListBean>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("callCreateApiApi"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNext(ModelBean<ExercisesListBean> exercisesBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (exercisesBean.getStatus() == 1) {
                                preferences.cleanRoutinedata();
                                Intent intent = new Intent(context, FitnessActivity.class);
                                startActivity(intent);
                                CreateRoutineActivity.this.overridePendingTransition(0, 0);
                            }
                            else
                                CommonUtils.toast(context, exercisesBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }
}
