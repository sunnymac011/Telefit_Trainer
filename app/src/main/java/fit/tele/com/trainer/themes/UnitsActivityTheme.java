package fit.tele.com.trainer.themes;

import android.content.Context;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.databinding.ActivityUnitsBinding;
import fit.tele.com.trainer.utils.Preferences;

public class UnitsActivityTheme {

    Preferences preferences;

    public void setTheme(ActivityUnitsBinding binding, Context context) {
        preferences = new Preferences(context);
        if (preferences.getTheme().toString().equalsIgnoreCase("Default")) {
            binding.rlHeader.setBackgroundColor(context.getResources().getColor(R.color.main_color));
            binding.txtWeight.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtMesurements.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtDistance.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtEnergy.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtLiquidVolume.setTextColor(context.getResources().getColor(R.color.main_color));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Light Blue")) {
            binding.rlHeader.setBackgroundColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtWeight.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtMesurements.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtDistance.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtEnergy.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtLiquidVolume.setTextColor(context.getResources().getColor(R.color.light_blue_text));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Yellow")) {
            binding.rlHeader.setBackgroundColor(context.getResources().getColor(R.color.yellow));
            binding.txtWeight.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtMesurements.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtDistance.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtEnergy.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtLiquidVolume.setTextColor(context.getResources().getColor(R.color.yellow));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Purple")) {
            binding.rlHeader.setBackgroundColor(context.getResources().getColor(R.color.purple));
            binding.txtWeight.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtMesurements.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtDistance.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtEnergy.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtLiquidVolume.setTextColor(context.getResources().getColor(R.color.purple));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Red")) {
            binding.rlHeader.setBackgroundColor(context.getResources().getColor(R.color.red));
            binding.txtWeight.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtMesurements.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtDistance.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtEnergy.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtLiquidVolume.setTextColor(context.getResources().getColor(R.color.red));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Blue")) {
            binding.rlHeader.setBackgroundColor(context.getResources().getColor(R.color.blue));
            binding.txtWeight.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtMesurements.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtDistance.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtEnergy.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtLiquidVolume.setTextColor(context.getResources().getColor(R.color.blue));
        }
    }
}
