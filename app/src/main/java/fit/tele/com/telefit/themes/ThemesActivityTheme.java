package fit.tele.com.telefit.themes;

import android.content.Context;

import fit.tele.com.telefit.R;
import fit.tele.com.telefit.databinding.ActivityThemesBinding;
import fit.tele.com.telefit.utils.Preferences;

public class ThemesActivityTheme {

    Preferences preferences;

    public void setTheme(ActivityThemesBinding binding, Context context) {
        preferences = new Preferences(context);
        if (preferences.getTheme().toString().equalsIgnoreCase("Default")) {
            binding.rlHeader.setBackgroundColor(context.getResources().getColor(R.color.main_color));
            binding.txtDefault.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtLightBlue.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtYellow.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtRed.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtPurple.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtBlue.setTextColor(context.getResources().getColor(R.color.main_color));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Light Blue")) {
            binding.rlHeader.setBackgroundColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtDefault.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtLightBlue.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtYellow.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtRed.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtPurple.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtBlue.setTextColor(context.getResources().getColor(R.color.light_blue_text));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Yellow")) {
            binding.rlHeader.setBackgroundColor(context.getResources().getColor(R.color.yellow));
            binding.txtDefault.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtLightBlue.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtYellow.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtRed.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtPurple.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtBlue.setTextColor(context.getResources().getColor(R.color.yellow));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Purple")) {
            binding.rlHeader.setBackgroundColor(context.getResources().getColor(R.color.purple));
            binding.txtDefault.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtLightBlue.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtYellow.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtRed.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtPurple.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtBlue.setTextColor(context.getResources().getColor(R.color.purple));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Red")) {
            binding.rlHeader.setBackgroundColor(context.getResources().getColor(R.color.red));
            binding.txtDefault.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtLightBlue.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtYellow.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtRed.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtPurple.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtBlue.setTextColor(context.getResources().getColor(R.color.red));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Blue")) {
            binding.rlHeader.setBackgroundColor(context.getResources().getColor(R.color.blue));
            binding.txtDefault.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtLightBlue.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtYellow.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtRed.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtPurple.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtBlue.setTextColor(context.getResources().getColor(R.color.blue));
        }
    }
}
