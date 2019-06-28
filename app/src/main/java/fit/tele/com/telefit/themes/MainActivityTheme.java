package fit.tele.com.telefit.themes;

import android.app.Activity;
import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fit.tele.com.telefit.R;
import fit.tele.com.telefit.utils.Preferences;

public class MainActivityTheme {

    Preferences preferences;
    RelativeLayout relative_lay_out,relative_lay_out_nutrients;
    TextView txt_total_calories,txt_total_calories_detail,txt_net,txt_exercise,txt_food,txt_daily,
            txt_total_nurients_detail,txt_fat_gram_txt,txt_carbohydrates_gram_txt,txt_protein_gram_txt,txt_unknown_gram_txt;

    public void setTheme(Activity context) {
        preferences = new Preferences(context);

        relative_lay_out = (RelativeLayout) context.findViewById(R.id.relative_lay_out);
        txt_total_calories = (TextView) context.findViewById(R.id.txt_total_calories);
        txt_total_calories_detail = (TextView) context.findViewById(R.id.txt_total_calories_detail);
        txt_net = (TextView) context.findViewById(R.id.txt_net);
        txt_exercise = (TextView) context.findViewById(R.id.txt_exercise);
        txt_daily = (TextView) context.findViewById(R.id.txt_daily);

        relative_lay_out_nutrients = (RelativeLayout) context.findViewById(R.id.relative_lay_out_nutrients);
        txt_total_nurients_detail = (TextView) context.findViewById(R.id.txt_total_nurients_detail);
        txt_fat_gram_txt = (TextView) context.findViewById(R.id.txt_fat_gram_txt);
        txt_carbohydrates_gram_txt = (TextView) context.findViewById(R.id.txt_carbohydrates_gram_txt);
        txt_protein_gram_txt = (TextView) context.findViewById(R.id.txt_protein_gram_txt);
        txt_unknown_gram_txt = (TextView) context.findViewById(R.id.txt_unknown_gram_txt);

        if (preferences.getTheme().toString().equalsIgnoreCase("Default")) {
            relative_lay_out.setBackgroundColor(context.getResources().getColor(R.color.main_color));
            txt_total_calories.setTextColor(context.getResources().getColor(R.color.main_color));
            txt_total_calories_detail.setTextColor(context.getResources().getColor(R.color.main_color));
            txt_net.setTextColor(context.getResources().getColor(R.color.main_color));
            txt_exercise.setTextColor(context.getResources().getColor(R.color.main_color));
            txt_daily.setTextColor(context.getResources().getColor(R.color.main_color));

            relative_lay_out_nutrients.setBackgroundColor(context.getResources().getColor(R.color.main_color));
            txt_total_nurients_detail.setTextColor(context.getResources().getColor(R.color.main_color));
            txt_fat_gram_txt.setTextColor(context.getResources().getColor(R.color.main_color));
            txt_carbohydrates_gram_txt.setTextColor(context.getResources().getColor(R.color.main_color));
            txt_protein_gram_txt.setTextColor(context.getResources().getColor(R.color.main_color));
            txt_unknown_gram_txt.setTextColor(context.getResources().getColor(R.color.main_color));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Light Blue")) {
            relative_lay_out.setBackgroundColor(context.getResources().getColor(R.color.light_blue_text));
            txt_total_calories.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            txt_total_calories_detail.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            txt_net.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            txt_exercise.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            txt_daily.setTextColor(context.getResources().getColor(R.color.light_blue_text));

            relative_lay_out_nutrients.setBackgroundColor(context.getResources().getColor(R.color.light_blue_text));
            txt_total_nurients_detail.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            txt_fat_gram_txt.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            txt_carbohydrates_gram_txt.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            txt_protein_gram_txt.setTextColor(context.getResources().getColor(R.color.light_blue_text));
            txt_unknown_gram_txt.setTextColor(context.getResources().getColor(R.color.light_blue_text));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Yellow")) {
            relative_lay_out.setBackgroundColor(context.getResources().getColor(R.color.yellow));
            txt_total_calories.setTextColor(context.getResources().getColor(R.color.yellow));
            txt_total_calories_detail.setTextColor(context.getResources().getColor(R.color.yellow));
            txt_net.setTextColor(context.getResources().getColor(R.color.yellow));
            txt_exercise.setTextColor(context.getResources().getColor(R.color.yellow));
            txt_daily.setTextColor(context.getResources().getColor(R.color.yellow));

            relative_lay_out_nutrients.setBackgroundColor(context.getResources().getColor(R.color.yellow));
            txt_total_nurients_detail.setTextColor(context.getResources().getColor(R.color.yellow));
            txt_fat_gram_txt.setTextColor(context.getResources().getColor(R.color.yellow));
            txt_carbohydrates_gram_txt.setTextColor(context.getResources().getColor(R.color.yellow));
            txt_protein_gram_txt.setTextColor(context.getResources().getColor(R.color.yellow));
            txt_unknown_gram_txt.setTextColor(context.getResources().getColor(R.color.yellow));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Purple")) {
            relative_lay_out.setBackgroundColor(context.getResources().getColor(R.color.purple));
            txt_total_calories.setTextColor(context.getResources().getColor(R.color.purple));
            txt_total_calories_detail.setTextColor(context.getResources().getColor(R.color.purple));
            txt_net.setTextColor(context.getResources().getColor(R.color.purple));
            txt_exercise.setTextColor(context.getResources().getColor(R.color.purple));
            txt_daily.setTextColor(context.getResources().getColor(R.color.purple));

            relative_lay_out_nutrients.setBackgroundColor(context.getResources().getColor(R.color.purple));
            txt_total_nurients_detail.setTextColor(context.getResources().getColor(R.color.purple));
            txt_fat_gram_txt.setTextColor(context.getResources().getColor(R.color.purple));
            txt_carbohydrates_gram_txt.setTextColor(context.getResources().getColor(R.color.purple));
            txt_protein_gram_txt.setTextColor(context.getResources().getColor(R.color.purple));
            txt_unknown_gram_txt.setTextColor(context.getResources().getColor(R.color.purple));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Red")) {
            relative_lay_out.setBackgroundColor(context.getResources().getColor(R.color.red));
            txt_total_calories.setTextColor(context.getResources().getColor(R.color.red));
            txt_total_calories_detail.setTextColor(context.getResources().getColor(R.color.red));
            txt_net.setTextColor(context.getResources().getColor(R.color.red));
            txt_exercise.setTextColor(context.getResources().getColor(R.color.red));
            txt_daily.setTextColor(context.getResources().getColor(R.color.red));

            relative_lay_out_nutrients.setBackgroundColor(context.getResources().getColor(R.color.red));
            txt_total_nurients_detail.setTextColor(context.getResources().getColor(R.color.red));
            txt_fat_gram_txt.setTextColor(context.getResources().getColor(R.color.red));
            txt_carbohydrates_gram_txt.setTextColor(context.getResources().getColor(R.color.red));
            txt_protein_gram_txt.setTextColor(context.getResources().getColor(R.color.red));
            txt_unknown_gram_txt.setTextColor(context.getResources().getColor(R.color.red));
        }
        else if (preferences.getTheme().toString().equalsIgnoreCase("Blue")) {
            relative_lay_out.setBackgroundColor(context.getResources().getColor(R.color.blue));
            txt_total_calories.setTextColor(context.getResources().getColor(R.color.blue));
            txt_total_calories_detail.setTextColor(context.getResources().getColor(R.color.blue));
            txt_net.setTextColor(context.getResources().getColor(R.color.blue));
            txt_exercise.setTextColor(context.getResources().getColor(R.color.blue));
            txt_daily.setTextColor(context.getResources().getColor(R.color.blue));

            relative_lay_out_nutrients.setBackgroundColor(context.getResources().getColor(R.color.blue));
            txt_total_nurients_detail.setTextColor(context.getResources().getColor(R.color.blue));
            txt_fat_gram_txt.setTextColor(context.getResources().getColor(R.color.blue));
            txt_carbohydrates_gram_txt.setTextColor(context.getResources().getColor(R.color.blue));
            txt_protein_gram_txt.setTextColor(context.getResources().getColor(R.color.blue));
            txt_unknown_gram_txt.setTextColor(context.getResources().getColor(R.color.blue));
        }
    }
}
