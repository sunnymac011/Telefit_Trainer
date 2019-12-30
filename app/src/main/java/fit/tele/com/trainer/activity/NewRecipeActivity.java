package fit.tele.com.trainer.activity;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.databinding.ActivityNewRecipeBinding;

public class NewRecipeActivity extends BaseActivity {

    ActivityNewRecipeBinding binding;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_new_recipe;
    }

    @Override
    public void init() {
        binding = (ActivityNewRecipeBinding) getBindingObj();
    }
}
