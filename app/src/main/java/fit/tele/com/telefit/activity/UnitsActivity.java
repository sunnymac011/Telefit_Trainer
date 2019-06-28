package fit.tele.com.telefit.activity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import fit.tele.com.telefit.R;
import fit.tele.com.telefit.base.BaseActivity;
import fit.tele.com.telefit.databinding.ActivityUnitsBinding;
import fit.tele.com.telefit.themes.UnitsActivityTheme;

public class UnitsActivity extends BaseActivity implements View.OnClickListener {

    ActivityUnitsBinding binding;
    PopupMenu popup;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_units;
    }

    private void setListner() {
        binding.rlWeight.setOnClickListener(this);
        binding.rlMesurements.setOnClickListener(this);
        binding.rlDistance.setOnClickListener(this);
        binding.rlEnergy.setOnClickListener(this);
        binding.rlLiquidVolume.setOnClickListener(this);
        binding.imgSideBar.setOnClickListener(this);
    }

    private void setData() {
        binding.txtWeightCount.setText(preferences.getWeightMes());
        binding.txtMesurementsCount.setText(preferences.getMesurementsMes());
        binding.txtDistanceCount.setText(preferences.getDistanceMes());
        binding.txtEnergyCount.setText(preferences.getEnergyMes());
        binding.txtLiquidVolumeCount.setText(preferences.getLiquidMes());
    }

    @Override
    public void init() {
        binding = (ActivityUnitsBinding) getBindingObj();
        UnitsActivityTheme unitsActivityTheme = new UnitsActivityTheme();
        unitsActivityTheme.setTheme(binding, context);
        setListner();
        setData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_weight:
                popup = new PopupMenu(context, binding.txtWeightCount);
                popup.inflate(R.menu.menu_weight);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_pound:
                                preferences.setWeightMes(item.getTitle().toString());
                                binding.txtWeightCount.setText(item.getTitle().toString());
                                break;
                            case R.id.menu_kg:
                                preferences.setWeightMes(item.getTitle().toString());
                                binding.txtWeightCount.setText(item.getTitle().toString());
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
                break;

            case R.id.rl_mesurements:
                popup = new PopupMenu(context, binding.txtMesurementsCount);
                popup.inflate(R.menu.menu_mesurements);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_feet:
                                preferences.setWeightMes(item.getTitle().toString());
                                binding.txtMesurementsCount.setText(item.getTitle().toString());
                                break;
                            case R.id.menu_centimeters:
                                preferences.setWeightMes(item.getTitle().toString());
                                binding.txtMesurementsCount.setText(item.getTitle().toString());
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
                break;

            case R.id.rl_distance:
                popup = new PopupMenu(context, binding.txtDistanceCount);
                popup.inflate(R.menu.menu_distance);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_miles:
                                preferences.setWeightMes(item.getTitle().toString());
                                binding.txtDistanceCount.setText(item.getTitle().toString());
                                break;
                            case R.id.menu_km:
                                preferences.setWeightMes(item.getTitle().toString());
                                binding.txtDistanceCount.setText(item.getTitle().toString());
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
                break;

            case R.id.rl_energy:
                popup = new PopupMenu(context, binding.txtEnergyCount);
                popup.inflate(R.menu.menu_energy);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_calories:
                                preferences.setWeightMes(item.getTitle().toString());
                                binding.txtEnergyCount.setText(item.getTitle().toString());
                                break;
                            case R.id.menu_kg:
                                preferences.setWeightMes(item.getTitle().toString());
                                binding.txtEnergyCount.setText(item.getTitle().toString());
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
                break;

            case R.id.rl_liquid_volume:
                popup = new PopupMenu(context, binding.txtLiquidVolumeCount);
                popup.inflate(R.menu.menu_liquid);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_fluid:
                                preferences.setWeightMes(item.getTitle().toString());
                                binding.txtLiquidVolumeCount.setText(item.getTitle().toString());
                                break;
                            case R.id.menu_kg:
                                preferences.setWeightMes(item.getTitle().toString());
                                binding.txtLiquidVolumeCount.setText(item.getTitle().toString());
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
                break;
            case R.id.img_side_bar:
                onBackPressed();
                break;
        }
    }
}
