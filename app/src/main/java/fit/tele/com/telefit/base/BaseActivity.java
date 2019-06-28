package fit.tele.com.telefit.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import fit.tele.com.telefit.dialog.MediaOption;
import fit.tele.com.telefit.utils.Preferences;
import rx.Subscription;

public abstract class BaseActivity extends AppCompatActivity {
    private ViewDataBinding bindObject;
    public Subscription subscription;
    public Preferences preferences;
    public Context context;
    public MediaOption mediaOption;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindObject = DataBindingUtil.setContentView(this, getLayoutResId());
        preferences = new Preferences(this);
        context = this;
        init();
    }

    public abstract int getLayoutResId();

    public abstract void init();

    public ViewDataBinding getBindingObj() {
        return bindObject;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            Runtime.getRuntime().gc();
            System.gc();
        } catch(Exception ignored){
        }
    }
}

