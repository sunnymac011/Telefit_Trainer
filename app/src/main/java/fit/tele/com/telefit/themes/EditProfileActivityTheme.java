package fit.tele.com.telefit.themes;

import android.content.Context;

import fit.tele.com.telefit.R;
import fit.tele.com.telefit.databinding.ActivityEditProfileBinding;
import fit.tele.com.telefit.utils.Preferences;

public class EditProfileActivityTheme {

    Preferences preferences;

    public void setTheme(ActivityEditProfileBinding binding, Context context) {
        preferences = new Preferences(context);
        if (preferences.getTheme().toString().equalsIgnoreCase("Default")) {
            binding.rlMainHeader.setBackgroundColor(context.getResources().getColor(R.color.main_color));
            binding.inputFname.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.inputFname.setHintTextColor(context.getResources().getColor(R.color.main_color));
            binding.inputLname.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.inputLname.setHintTextColor(context.getResources().getColor(R.color.main_color));
            binding.inputEmail.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.inputEmail.setHintTextColor(context.getResources().getColor(R.color.main_color));
            binding.inputAddress.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.inputAddress.setHintTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtDobHeader.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtDob.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtGenderHeader.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtHeightHeader.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtHeightType.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.edtHeight.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtWeightHeader.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.txtWeightType.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.edtWeight.setTextColor(context.getResources().getColor(R.color.main_color));
            binding.btnSubmit.setBackgroundResource(R.drawable.rectangle_main_blue_bg);
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Light Blue")) {
            binding.rlMainHeader.setBackgroundColor(context.getResources().getColor(R.color.light_blue_text));
            binding.inputFname.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.inputFname.setHintTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.inputLname.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.inputLname.setHintTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.inputEmail.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.inputEmail.setHintTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.inputAddress.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.inputAddress.setHintTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtDobHeader.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtDob.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtGenderHeader.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtHeightHeader.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtHeightType.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.edtHeight.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtWeightHeader.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.txtWeightType.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.edtWeight.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            binding.btnSubmit.setBackgroundResource(R.drawable.rectangle_light_blue_bg);
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Yellow")) {
            binding.rlMainHeader.setBackgroundColor(context.getResources().getColor(R.color.yellow));
            binding.inputFname.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.inputFname.setHintTextColor(context.getResources().getColor(R.color.yellow));
            binding.inputLname.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.inputLname.setHintTextColor(context.getResources().getColor(R.color.yellow));
            binding.inputEmail.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.inputEmail.setHintTextColor(context.getResources().getColor(R.color.yellow));
            binding.inputAddress.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.inputAddress.setHintTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtDobHeader.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtDob.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtGenderHeader.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtHeightHeader.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtHeightType.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.edtHeight.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtWeightHeader.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.txtWeightType.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.edtWeight.setTextColor(context.getResources().getColor(R.color.yellow));
            binding.btnSubmit.setBackgroundResource(R.drawable.rectangle_yellow_bg);
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Purple")) {
            binding.rlMainHeader.setBackgroundColor(context.getResources().getColor(R.color.purple));
            binding.inputFname.setTextColor(context.getResources().getColor(R.color.purple));
            binding.inputFname.setHintTextColor(context.getResources().getColor(R.color.purple));
            binding.inputLname.setTextColor(context.getResources().getColor(R.color.purple));
            binding.inputLname.setHintTextColor(context.getResources().getColor(R.color.purple));
            binding.inputEmail.setTextColor(context.getResources().getColor(R.color.purple));
            binding.inputEmail.setHintTextColor(context.getResources().getColor(R.color.purple));
            binding.inputAddress.setTextColor(context.getResources().getColor(R.color.purple));
            binding.inputAddress.setHintTextColor(context.getResources().getColor(R.color.purple));
            binding.txtDobHeader.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtDob.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtGenderHeader.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtHeightHeader.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtHeightType.setTextColor(context.getResources().getColor(R.color.purple));
            binding.edtHeight.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtWeightHeader.setTextColor(context.getResources().getColor(R.color.purple));
            binding.txtWeightType.setTextColor(context.getResources().getColor(R.color.purple));
            binding.edtWeight.setTextColor(context.getResources().getColor(R.color.purple));
            binding.btnSubmit.setBackgroundResource(R.drawable.rectangle_purple_bg);
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Red")) {
            binding.rlMainHeader.setBackgroundColor(context.getResources().getColor(R.color.red));
            binding.inputFname.setTextColor(context.getResources().getColor(R.color.red));
            binding.inputFname.setHintTextColor(context.getResources().getColor(R.color.red));
            binding.inputLname.setTextColor(context.getResources().getColor(R.color.red));
            binding.inputLname.setHintTextColor(context.getResources().getColor(R.color.red));
            binding.inputEmail.setTextColor(context.getResources().getColor(R.color.red));
            binding.inputEmail.setHintTextColor(context.getResources().getColor(R.color.red));
            binding.inputAddress.setTextColor(context.getResources().getColor(R.color.red));
            binding.inputAddress.setHintTextColor(context.getResources().getColor(R.color.red));
            binding.txtDobHeader.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtDob.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtGenderHeader.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtHeightHeader.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtHeightType.setTextColor(context.getResources().getColor(R.color.red));
            binding.edtHeight.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtWeightHeader.setTextColor(context.getResources().getColor(R.color.red));
            binding.txtWeightType.setTextColor(context.getResources().getColor(R.color.red));
            binding.edtWeight.setTextColor(context.getResources().getColor(R.color.red));
            binding.btnSubmit.setBackgroundResource(R.drawable.rectangle_red_bg);
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Blue")) {
            binding.rlMainHeader.setBackgroundColor(context.getResources().getColor(R.color.blue));
            binding.inputFname.setTextColor(context.getResources().getColor(R.color.blue));
            binding.inputFname.setHintTextColor(context.getResources().getColor(R.color.blue));
            binding.inputLname.setTextColor(context.getResources().getColor(R.color.blue));
            binding.inputLname.setHintTextColor(context.getResources().getColor(R.color.blue));
            binding.inputEmail.setTextColor(context.getResources().getColor(R.color.blue));
            binding.inputEmail.setHintTextColor(context.getResources().getColor(R.color.blue));
            binding.inputAddress.setTextColor(context.getResources().getColor(R.color.blue));
            binding.inputAddress.setHintTextColor(context.getResources().getColor(R.color.blue));
            binding.txtDobHeader.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtDob.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtGenderHeader.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtHeightHeader.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtHeightType.setTextColor(context.getResources().getColor(R.color.blue));
            binding.edtHeight.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtWeightHeader.setTextColor(context.getResources().getColor(R.color.blue));
            binding.txtWeightType.setTextColor(context.getResources().getColor(R.color.blue));
            binding.edtWeight.setTextColor(context.getResources().getColor(R.color.blue));
            binding.btnSubmit.setBackgroundResource(R.drawable.rectangle_blue_bg);
        }
    }
}
