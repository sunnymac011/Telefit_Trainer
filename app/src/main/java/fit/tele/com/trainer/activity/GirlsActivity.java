package fit.tele.com.trainer.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.adapter.GirlAdapter;
import fit.tele.com.trainer.base.BaseActivity;
import fit.tele.com.trainer.modelBean.SelectedItemsBean;
import fit.tele.com.trainer.modelBean.SubCatId;
import fit.tele.com.trainer.modelBean.SubOptionsBean;
import fit.tele.com.trainer.utils.CommonUtils;


public class GirlsActivity extends BaseActivity implements View.OnClickListener {

    fit.tele.com.trainer.databinding.ActivityGirlsBinding binding;
    private GirlAdapter girlAdapter;
    private ArrayList<SubOptionsBean> subOptionsBeans=new ArrayList<>();
    private SelectedItemsBean selectedItemsBean = new SelectedItemsBean();
    private String strCatSubId="";

    @Override
    public int getLayoutResId() {
        return R.layout.activity_girls;
    }

    @Override
    public void init() {
        binding = (fit.tele.com.trainer.databinding.ActivityGirlsBinding) getBindingObj();

        binding.imgSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if(getIntent() != null && getIntent().hasExtra("subOptionsBeans"))
            subOptionsBeans = getIntent().getParcelableArrayListExtra("subOptionsBeans");
        if(getIntent() != null && getIntent().hasExtra("subCatId"))
            strCatSubId = getIntent().getStringExtra("subCatId");

        setListner();
    }

    private void setListner() {
        binding.llProfile.setOnClickListener(this);
        binding.llNutrition.setOnClickListener(this);
        binding.llGoals.setOnClickListener(this);
        binding.llCustomers.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.rvGirl.setLayoutManager(linearLayoutManager);

        if (girlAdapter == null)
        {
            girlAdapter = new GirlAdapter(context, binding.rvGirl, new GirlAdapter.ClickListener() {
                @Override
                public void onClick(String exe_id) {
                    ArrayList<String> arrayList;
                    SubCatId subCatId;
                    ArrayList<SubCatId> subCatIds = new ArrayList<>();
                    selectedItemsBean.setCatId("2");
                    arrayList = new ArrayList<>();
                    subCatId = new SubCatId();
                    arrayList.add(exe_id);
                    subCatId.setId(strCatSubId);
                    subCatId.setOptSub(arrayList);
                    subCatIds.add(subCatId);
                    selectedItemsBean.setSubCatId(subCatIds);
                    Log.e("selectedItemsBean",""+selectedItemsBean.toString());

                    if (selectedItemsBean != null)
                    {
                        Intent intent = new Intent(GirlsActivity.this,ExercisesActivity.class);
                        intent.putExtra("SelectedItems",selectedItemsBean);
                        intent.putExtra("from","crossfit");
                        intent.putExtra("fromSub","TheGirl");
                        startActivity(intent);
                    }
                    else
                        CommonUtils.toast(context,"Please select category!");
                }
            });
        }
        binding.rvGirl.setAdapter(girlAdapter);
        girlAdapter.clearAll();

        if (subOptionsBeans != null)
            girlAdapter.addAllList(subOptionsBeans);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_customers :
                intent = new Intent(context, CustomersActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_nutrition:
                intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                this.overridePendingTransition(0, 0);
                break;

            case R.id.ll_profile:
                intent = new Intent(context, CustomerProfileActivity.class);
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
