package fit.tele.com.telefit.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.firebase.iid.FirebaseInstanceId;

import java.security.MessageDigest;

import fit.tele.com.telefit.R;
import fit.tele.com.telefit.base.BaseActivity;
import fit.tele.com.telefit.databinding.ActivitySplashBinding;
import fit.tele.com.telefit.utils.LocationService;

public class SplashActivityClass extends BaseActivity {

    ActivitySplashBinding binding;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
        facebookHashKey(this);
        binding = (ActivitySplashBinding) getBindingObj();
        startService(new Intent(getApplicationContext(), LocationService.class));
        startCountDownTimer();
    }

    public void facebookHashKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e(SplashActivityClass.class.getName(), "KeyHash: " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception ignored) {
        }
    }

    private void startCountDownTimer() {

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        binding.contrain.clearAnimation();
        binding.contrain.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();

        binding.imgLogo.clearAnimation();
        binding.imgLogo.startAnimation(anim);

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }

                    if (FirebaseInstanceId.getInstance().getToken() != null) {
                        preferences.setPushToken(FirebaseInstanceId.getInstance().getToken());
                    }
                    startNextActivity();
                    SplashActivityClass.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    SplashActivityClass.this.finish();
                }
            }
        };
        splashTread.start();
    }

    private void startNextActivity() {
        Intent intent1;
        if (preferences.getSessionToken() != null) {
            intent1 = new Intent(SplashActivityClass.this, MainActivity.class);

            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent1);
            finishAffinity();
        } else {
            Intent intent;
            intent = new Intent(SplashActivityClass.this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finishAffinity();
        }
    }
}
