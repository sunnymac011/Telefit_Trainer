package fit.tele.com.trainer.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.apiBase.FetchServiceBase;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.databinding.ActivityCustomerProfileBinding;
import fit.tele.com.trainer.modelBean.CustomerProfileBean;
import fit.tele.com.trainer.modelBean.LoginBean;
import fit.tele.com.trainer.modelBean.ModelBean;
import fit.tele.com.trainer.utils.CircleTransform;
import fit.tele.com.trainer.utils.CommonUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CustomerProfileActivity extends BaseActivity implements View.OnClickListener{

    ActivityCustomerProfileBinding binding;
    LoginBean saveLogiBean;
    RelativeLayout rl_food, rl_meals, rl_recipes;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_customer_profile;
    }

    @Override
    public void init() {

        binding = (ActivityCustomerProfileBinding) getBindingObj();
        setListner();
    }

    private void setListner() {
        rl_food = (RelativeLayout) findViewById(R.id.rl_food);
        rl_meals = (RelativeLayout) findViewById(R.id.rl_meals);
        rl_recipes = (RelativeLayout) findViewById(R.id.rl_recipes);

        binding.llCustomers.setOnClickListener(this);
        binding.llNutrition.setOnClickListener(this);
        binding.llFitness.setOnClickListener(this);
        binding.llGoals.setOnClickListener(this);

        binding.llNutritionTab.setOnClickListener(this);
        binding.llFitnessTab.setOnClickListener(this);

        rl_food.setOnClickListener(this);
        rl_meals.setOnClickListener(this);
        rl_recipes.setOnClickListener(this);
        if(getIntent() != null && getIntent().hasExtra("cust_id"))
            getCustomerProfile(getIntent().getStringExtra("cust_id"));
    }

    private void setData(){
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
                binding.txtHeightWeight.setText(saveLogiBean.getHeight()+"ft / "+saveLogiBean.getWeight()+" "+saveLogiBean.getWeightType());
        }

        if (saveLogiBean != null && saveLogiBean.getDob() != null
                && !TextUtils.isEmpty(saveLogiBean.getDob()))
            binding.txtAge.setText(getAge(saveLogiBean.getDob())+" years old");
        if (saveLogiBean != null && saveLogiBean.getMaintainWeight() != null
                && !TextUtils.isEmpty(saveLogiBean.getMaintainWeight()))
            binding.txtExerciseTime.setText(saveLogiBean.getMaintainWeight());

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

            case R.id.ll_nutrition :
                intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_fitness :
                intent = new Intent(context, FitnessActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_goals:
                intent = new Intent(context, GoalsActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_nutrition_tab :
                binding.vf.setDisplayedChild(0);
                binding.txtNutritionTab.setTextColor(getResources().getColor(R.color.white));
                binding.viewNutrition.setVisibility(View.VISIBLE);
                binding.txtFitnessTab.setTextColor(getResources().getColor(R.color.light_gray));
                binding.viewFitness.setVisibility(View.GONE);
                break;

            case R.id.ll_fitness_tab :
                binding.vf.setDisplayedChild(1);
                binding.txtNutritionTab.setTextColor(getResources().getColor(R.color.light_gray));
                binding.viewNutrition.setVisibility(View.GONE);
                binding.txtFitnessTab.setTextColor(getResources().getColor(R.color.white));
                binding.viewFitness.setVisibility(View.VISIBLE);
                break;

            case R.id.rl_food :
                intent = new Intent(context, SearchFoodActivity.class);
                intent.putExtra("tab",2);
                startActivity(intent);
                break;

            case R.id.rl_meals :
                intent = new Intent(context, SearchFoodActivity.class);
                intent.putExtra("tab",3);
                startActivity(intent);
                break;

            case R.id.rl_recipes :
                intent = new Intent(context, SearchFoodActivity.class);
                intent.putExtra("tab",4);
                startActivity(intent);
                break;
        }
    }

    private void getCustomerProfile(String custId) {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            Map<String, String> map = new HashMap<>();
            map.put("cust_id", custId);
            Observable<ModelBean<LoginBean>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).getCustomerProfile(map);
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
                            Log.e("getCustomerProfile"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }
                        @Override
                        public void onNext(ModelBean<LoginBean> loginBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (loginBean.getStatus() == 1)
                            {
                                saveLogiBean = loginBean.getResult();
                                setData();
                            }
                            else
                                CommonUtils.toast(context,loginBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
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
