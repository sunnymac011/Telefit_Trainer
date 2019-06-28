package fit.tele.com.telefit.firebasepush;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

import fit.tele.com.telefit.utils.Preferences;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        sendRegistrationToServer(s);

    }

    private void sendRegistrationToServer(String token) {
        Log.e("token "," "+token);
        Preferences preferences = new Preferences(this);
        preferences.setPushToken(token);
    }
}