package fit.tele.com.trainer.activity;

import android.view.View;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.databinding.ActivityThemesBinding;
import fit.tele.com.trainer.themes.ThemesActivityTheme;

public class ThemesActivity extends BaseActivity implements View.OnClickListener {

    public ActivityThemesBinding binding;
    ThemesActivityTheme themesActivityTheme;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_themes;
    }

    private void setListner() {

        binding.imgSideBar.setOnClickListener(this);

        binding.llDefault.setOnClickListener(this);
        binding.llLightBlue.setOnClickListener(this);
        binding.llYellow.setOnClickListener(this);
        binding.llPurple.setOnClickListener(this);
        binding.llRed.setOnClickListener(this);
        binding.llBlue.setOnClickListener(this);
    }

    @Override
    public void init() {
        binding = (ActivityThemesBinding) getBindingObj();
        themesActivityTheme = new ThemesActivityTheme();
        themesActivityTheme.setTheme(binding, context);
        setListner();
        setData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_side_bar:
                onBackPressed();
                break;
            case R.id.ll_default:
                preferences.setTheme("Default");
                binding.imgDefault.setVisibility(View.VISIBLE);
                binding.imgLightBlue.setVisibility(View.GONE);
                binding.imgYellow.setVisibility(View.GONE);
                binding.imgPurple.setVisibility(View.GONE);
                binding.imgRed.setVisibility(View.GONE);
                binding.imgBlue.setVisibility(View.GONE);
                themesActivityTheme.setTheme(binding, context);
                break;
            case R.id.ll_light_blue:
                preferences.setTheme("Light Blue");
                binding.imgDefault.setVisibility(View.GONE);
                binding.imgLightBlue.setVisibility(View.VISIBLE);
                binding.imgYellow.setVisibility(View.GONE);
                binding.imgPurple.setVisibility(View.GONE);
                binding.imgRed.setVisibility(View.GONE);
                binding.imgBlue.setVisibility(View.GONE);
                themesActivityTheme.setTheme(binding, context);
                break;
            case R.id.ll_yellow:
                preferences.setTheme("Yellow");
                binding.imgDefault.setVisibility(View.GONE);
                binding.imgLightBlue.setVisibility(View.GONE);
                binding.imgYellow.setVisibility(View.VISIBLE);
                binding.imgPurple.setVisibility(View.GONE);
                binding.imgRed.setVisibility(View.GONE);
                binding.imgBlue.setVisibility(View.GONE);
                themesActivityTheme.setTheme(binding, context);
                break;
            case R.id.ll_purple:
                preferences.setTheme("Purple");
                binding.imgDefault.setVisibility(View.GONE);
                binding.imgLightBlue.setVisibility(View.GONE);
                binding.imgYellow.setVisibility(View.GONE);
                binding.imgPurple.setVisibility(View.VISIBLE);
                binding.imgRed.setVisibility(View.GONE);
                binding.imgBlue.setVisibility(View.GONE);
                themesActivityTheme.setTheme(binding, context);
                break;
            case R.id.ll_red:
                preferences.setTheme("Red");
                binding.imgDefault.setVisibility(View.GONE);
                binding.imgLightBlue.setVisibility(View.GONE);
                binding.imgYellow.setVisibility(View.GONE);
                binding.imgPurple.setVisibility(View.GONE);
                binding.imgRed.setVisibility(View.VISIBLE);
                binding.imgBlue.setVisibility(View.GONE);
                themesActivityTheme.setTheme(binding, context);
                break;
            case R.id.ll_blue:
                preferences.setTheme("Blue");
                binding.imgDefault.setVisibility(View.GONE);
                binding.imgLightBlue.setVisibility(View.GONE);
                binding.imgYellow.setVisibility(View.GONE);
                binding.imgPurple.setVisibility(View.GONE);
                binding.imgRed.setVisibility(View.GONE);
                binding.imgBlue.setVisibility(View.VISIBLE);
                themesActivityTheme.setTheme(binding, context);
                break;
        }
    }

    private void setData() {
        if (preferences.getTheme().toString().equalsIgnoreCase("Default")) {
            binding.imgDefault.setVisibility(View.VISIBLE);
            binding.imgLightBlue.setVisibility(View.GONE);
            binding.imgYellow.setVisibility(View.GONE);
            binding.imgPurple.setVisibility(View.GONE);
            binding.imgRed.setVisibility(View.GONE);
            binding.imgBlue.setVisibility(View.GONE);
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Light Blue")) {
            binding.imgDefault.setVisibility(View.GONE);
            binding.imgLightBlue.setVisibility(View.VISIBLE);
            binding.imgYellow.setVisibility(View.GONE);
            binding.imgPurple.setVisibility(View.GONE);
            binding.imgRed.setVisibility(View.GONE);
            binding.imgBlue.setVisibility(View.GONE);
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Yellow")) {
            binding.imgDefault.setVisibility(View.GONE);
            binding.imgLightBlue.setVisibility(View.GONE);
            binding.imgYellow.setVisibility(View.VISIBLE);
            binding.imgPurple.setVisibility(View.GONE);
            binding.imgRed.setVisibility(View.GONE);
            binding.imgBlue.setVisibility(View.GONE);
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Purple")) {
            binding.imgDefault.setVisibility(View.GONE);
            binding.imgLightBlue.setVisibility(View.GONE);
            binding.imgYellow.setVisibility(View.GONE);
            binding.imgPurple.setVisibility(View.VISIBLE);
            binding.imgRed.setVisibility(View.GONE);
            binding.imgBlue.setVisibility(View.GONE);
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Red")) {
            binding.imgDefault.setVisibility(View.GONE);
            binding.imgLightBlue.setVisibility(View.GONE);
            binding.imgYellow.setVisibility(View.GONE);
            binding.imgPurple.setVisibility(View.GONE);
            binding.imgRed.setVisibility(View.VISIBLE);
            binding.imgBlue.setVisibility(View.GONE);
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Blue")) {
            binding.imgDefault.setVisibility(View.GONE);
            binding.imgLightBlue.setVisibility(View.GONE);
            binding.imgYellow.setVisibility(View.GONE);
            binding.imgPurple.setVisibility(View.GONE);
            binding.imgRed.setVisibility(View.GONE);
            binding.imgBlue.setVisibility(View.VISIBLE);
        }
    }
}
