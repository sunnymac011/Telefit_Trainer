package fit.tele.com.telefit.utils;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.facebook.FacebookSdk;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

import fit.tele.com.telefit.BuildConfig;
import fit.tele.com.telefit.R;
import io.agora.AgoraAPIOnlySignal;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.fabric.sdk.android.Fabric;

public class MyApplication extends MultiDexApplication {
    public PhoneAuthProvider.ForceResendingToken token;
    public String verificationId;

    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    public FirebaseAuth mAuth;

    private final String TAG = MyApplication.class.getSimpleName();

    private static MyApplication mInstance;
    private AgoraAPIOnlySignal m_agoraAPI;
    private RtcEngine mRtcEngine;

    public static MyApplication the() {
        return mInstance;
    }

    public MyApplication() {
        mInstance = this;
    }

    private MyApplication.OnAgoraEngineInterface onAgoraEngineInterface;

    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onFirstRemoteVideoDecoded(final int uid, int width, int height, int elapsed) {
            if (onAgoraEngineInterface != null) {
                onAgoraEngineInterface.onFirstRemoteVideoDecoded(uid, width, height, elapsed);
            }
        }

        @Override
        public void onUserOffline(int uid, int reason) {
            Log.i(TAG, "onUserOffline uid: " + uid + " reason:" + reason);
            if (onAgoraEngineInterface != null) {
                onAgoraEngineInterface.onUserOffline(uid, reason);
            }
        }

        @Override
        public void onUserMuteVideo(final int uid, final boolean muted) {
            if (onAgoraEngineInterface != null) {
                onAgoraEngineInterface.onUserMuteVideo(uid, muted);
            }
        }

        @Override
        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
            super.onJoinChannelSuccess(channel, uid, elapsed);
            Log.i(TAG, "onJoinChannelSuccess channel:" + channel + " uid:" + uid);
            if (onAgoraEngineInterface != null) {
                onAgoraEngineInterface.onJoinChannelSuccess(channel, uid, elapsed);
            }
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        setupAgoraEngine();
        CrashlyticsCore core = new CrashlyticsCore.Builder().disabled(BuildConfig.CRASHLYTICS_ENABLED).build();
        //Fabric.with(this, new Crashlytics.Builder().core(core).build());
        Fabric.with(this, new Crashlytics());

        FacebookSdk.sdkInitialize(getApplicationContext());
        FirebaseApp.initializeApp(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
//        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "assets/font/rubikregular.ttf");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public PhoneAuthProvider.ForceResendingToken getToken() {
        return token;
    }

    public void setToken(PhoneAuthProvider.ForceResendingToken token) {
        this.token = token;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    public PhoneAuthProvider.OnVerificationStateChangedCallbacks getmCallbacks() {
        return mCallbacks;
    }

    public void setmCallbacks(PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks) {
        this.mCallbacks = mCallbacks;
    }

    public RtcEngine getmRtcEngine() {
        return mRtcEngine;
    }

    public AgoraAPIOnlySignal getmAgoraAPI() {
        return m_agoraAPI;
    }

    private void setupAgoraEngine() {
        String appID = getString(R.string.agora_app_id);

        try {
            m_agoraAPI = AgoraAPIOnlySignal.getInstance(this, appID);
            mRtcEngine = RtcEngine.create(getBaseContext(), appID, mRtcEventHandler);
            Log.i(TAG, "setupAgoraEngine mRtcEngine :" + mRtcEngine);
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
    }

    public void setOnAgoraEngineInterface(MyApplication.OnAgoraEngineInterface onAgoraEngineInterface) {
        this.onAgoraEngineInterface = onAgoraEngineInterface;
    }

    public interface OnAgoraEngineInterface {
        void onFirstRemoteVideoDecoded(final int uid, int width, int height, int elapsed);

        void onUserOffline(int uid, int reason);

        void onUserMuteVideo(final int uid, final boolean muted);

        void onJoinChannelSuccess(String channel, int uid, int elapsed);
    }
}
