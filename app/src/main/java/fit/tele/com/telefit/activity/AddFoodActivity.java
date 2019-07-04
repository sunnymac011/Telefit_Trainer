package fit.tele.com.telefit.activity;

import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.NumberPicker;

import fit.tele.com.telefit.R;
import fit.tele.com.telefit.base.BaseActivity;
import fit.tele.com.telefit.databinding.ActivityAddFoodBinding;
import fit.tele.com.telefit.modelBean.chompBeans.ChompProductBean;

public class AddFoodActivity extends BaseActivity implements View.OnClickListener {

    ActivityAddFoodBinding binding;
    private ChompProductBean chompProductBean;
    private int intQty = 1, intHalfQty = 0;
    private double doubleFiber = 0, doubleSugar = 0, doubleCarbs = 0, doubleCalories = 0, doubleFat = 0, doubleSatFat = 0, doubleChol = 0
            , doubleSodium = 0, doubleProtein = 0;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_add_food;
    }

    @Override
    public void init() {
        binding = (ActivityAddFoodBinding) getBindingObj();
        if(getIntent() != null && getIntent().hasExtra("SelectedItems"))
            chompProductBean = getIntent().getParcelableExtra("SelectedItems");

        if (chompProductBean != null)
            setValue();
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
        binding.llFitness.setOnClickListener(this);
        binding.llGoals.setOnClickListener(this);

        final String[] values= {"-","1/4","1/2", "3/4"};

        binding.npServeQty.setMinValue(0);
        binding.npServeQty.setMaxValue(values.length-1);
        binding.npServeQty.setDisplayedValues(values);
        binding.npServeQty.setWrapSelectorWheel(true);
        binding.npServeQty.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){

            }
        });

        binding.npQty.setMinValue(1);
        binding.npQty.setMaxValue(10);
        binding.npQty.setWrapSelectorWheel(true);
        binding.npQty.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                intQty = newVal;
                setData();
            }
        });
    }

    private void setValue() {
        if (chompProductBean.getDetails() != null
                && chompProductBean.getDetails().getNutritionLabel() != null
                && chompProductBean.getDetails().getNutritionLabel().getCalories() != null
                && !TextUtils.isEmpty(chompProductBean.getDetails().getNutritionLabel().getCalories().getPerServing()))
            doubleCalories = Double.parseDouble(chompProductBean.getDetails().getNutritionLabel().getCalories().getPerServing());
        if (chompProductBean.getDetails() != null
                && chompProductBean.getDetails().getNutritionLabel() != null
                && chompProductBean.getDetails().getNutritionLabel().getFat() != null
                && !TextUtils.isEmpty(chompProductBean.getDetails().getNutritionLabel().getFat().getPerServing()))
            doubleFat = Double.parseDouble(chompProductBean.getDetails().getNutritionLabel().getFat().getPerServing());
        if (chompProductBean.getDetails() != null
                && chompProductBean.getDetails().getNutritionLabel() != null
                && chompProductBean.getDetails().getNutritionLabel().getCholesterol() != null
                && !TextUtils.isEmpty(chompProductBean.getDetails().getNutritionLabel().getCholesterol().getPerServing()))
            doubleChol = Double.parseDouble(chompProductBean.getDetails().getNutritionLabel().getCholesterol().getPerServing());
        if (chompProductBean.getDetails() != null
                && chompProductBean.getDetails().getNutritionLabel() != null
                && chompProductBean.getDetails().getNutritionLabel().getSodium() != null
                && !TextUtils.isEmpty(chompProductBean.getDetails().getNutritionLabel().getSodium().getPerServing()))
            doubleSodium = Double.parseDouble(chompProductBean.getDetails().getNutritionLabel().getSodium().getPerServing());
        if (chompProductBean.getDetails() != null
                && chompProductBean.getDetails().getNutritionLabel() != null
                && chompProductBean.getDetails().getNutritionLabel().getFiber() != null
                && !TextUtils.isEmpty(chompProductBean.getDetails().getNutritionLabel().getFiber().getPerServing()))
            doubleFiber = Double.parseDouble(chompProductBean.getDetails().getNutritionLabel().getFiber().getPerServing());
        if (chompProductBean.getDetails() != null
                && chompProductBean.getDetails().getNutritionLabel() != null
                && chompProductBean.getDetails().getNutritionLabel().getSugars() != null
                && !TextUtils.isEmpty(chompProductBean.getDetails().getNutritionLabel().getSugars().getPerServing()))
            doubleSugar = Double.parseDouble(chompProductBean.getDetails().getNutritionLabel().getSugars().getPerServing());
        doubleCarbs = doubleFiber+doubleSugar;
        if (chompProductBean.getDetails() != null
                && chompProductBean.getDetails().getNutritionLabel() != null
                && chompProductBean.getDetails().getNutritionLabel().getProteins() != null
                && !TextUtils.isEmpty(chompProductBean.getDetails().getNutritionLabel().getProteins().getPerServing()))
            doubleProtein = Double.parseDouble(chompProductBean.getDetails().getNutritionLabel().getProteins().getPerServing());
        if (chompProductBean.getDetails() != null
                && chompProductBean.getDetails().getNutritionLabel() != null
                && chompProductBean.getDetails().getNutritionLabel().getSaturatedFat() != null
                && !TextUtils.isEmpty(chompProductBean.getDetails().getNutritionLabel().getSaturatedFat().getPerServing()))
            doubleSatFat = Double.parseDouble(chompProductBean.getDetails().getNutritionLabel().getSaturatedFat().getPerServing());

        setData();
    }

    private void setData() {
        if (chompProductBean.getName() != null && !TextUtils.isEmpty(chompProductBean.getName()))
            binding.txtFoodName.setText(Html.fromHtml(chompProductBean.getName()));

        binding.txtCal.setText(String.format("%.2f", (doubleCalories*intQty))+"");
        binding.txtTotalFat.setText(String.format("%.2f", (doubleFat*intQty))+"");
        binding.txtCholesterol.setText(String.format("%.2f", (doubleChol*intQty))+"");
        binding.txtSodium.setText(String.format("%.2f", (doubleSodium*intQty))+"");
        binding.txtFibre.setText(String.format("%.2f", (doubleFiber*intQty))+"");
        binding.txtSugars.setText(String.format("%.2f", (doubleSugar*intQty))+"");
        doubleCarbs = doubleFiber+doubleSugar;
        binding.txtTotalCarbs.setText(""+String.format("%.2f", (doubleCarbs*intQty)));
        binding.txtProtein.setText(String.format("%.2f", (doubleProtein*intQty))+"");
        binding.txtSatFat.setText(String.format("%.2f", (doubleSatFat*intQty))+"");
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
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

            case R.id.ll_fitness:
                intent = new Intent(context, FitnessActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_goals:
                intent = new Intent(context, GoalsActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;
        }
    }
}
