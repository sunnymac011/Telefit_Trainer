package fit.tele.com.trainer.interfaces;

import android.view.View;

public interface ClickListenerChatFirebase {

    void clickImageChat(View view, int position, String nameUser, String urlPhotoUser, String urlPhotoClick);

    void clickFileChat(View view, int position, String nameUser, String urlFileUser, String urlFileClick);

    void clickImageMapChat(View view, int position, String latitude, String longitude);
}
