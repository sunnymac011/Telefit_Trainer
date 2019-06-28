package fit.tele.com.telefit.themes;

import android.content.Context;

import fit.tele.com.telefit.R;
import fit.tele.com.telefit.databinding.ActivityAboutTelefitBinding;
import fit.tele.com.telefit.utils.Preferences;

public class AboutTeleFitActivityTheme {

    Preferences preferences;

    public void setTheme(ActivityAboutTelefitBinding binding, Context context) {
        preferences = new Preferences(context);
        if (preferences.getTheme().toString().equalsIgnoreCase("Default")) {
            binding.rlMainHeader.setBackgroundColor(context.getResources().getColor(R.color.main_color));
            binding.txtVersion.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtPrivacy.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtTerms.setTextColor(context.getResources().getColor(R.color.main_color));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Light Blue")) {
            binding.rlMainHeader.setBackgroundColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtVersion.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtPrivacy.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtTerms.setTextColor(context.getResources().getColor(R.color.light_blue_text));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Yellow")) {
            binding.rlMainHeader.setBackgroundColor(context.getResources().getColor(R.color.yellow));
            binding.txtVersion.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtPrivacy.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtTerms.setTextColor(context.getResources().getColor(R.color.yellow));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Purple")) {
            binding.rlMainHeader.setBackgroundColor(context.getResources().getColor(R.color.purple));
            binding.txtVersion.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtPrivacy.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtTerms.setTextColor(context.getResources().getColor(R.color.purple));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Red")) {
            binding.rlMainHeader.setBackgroundColor(context.getResources().getColor(R.color.red));
            binding.txtVersion.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtPrivacy.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtTerms.setTextColor(context.getResources().getColor(R.color.red));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Blue")) {
            binding.rlMainHeader.setBackgroundColor(context.getResources().getColor(R.color.blue));
            binding.txtVersion.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtPrivacy.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtTerms.setTextColor(context.getResources().getColor(R.color.blue));
        }
    }
}
