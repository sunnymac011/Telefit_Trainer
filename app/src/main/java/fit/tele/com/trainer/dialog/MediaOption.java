package fit.tele.com.trainer.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import fit.tele.com.trainer.R;

public class MediaOption extends Dialog implements View.OnClickListener {
    private Context context;
    private TextView txt_camera;
    private TextView txt_gallery;
    private AlertListener listener;

    public MediaOption(@NonNull Context context, AlertListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    public MediaOption(@NonNull Context context, @StyleRes int themeResId, AlertListener listener) {
        super(context, themeResId);
        this.context = context;
        this.listener = listener;
    }

    public MediaOption(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, AlertListener listener) {
        super(context, cancelable, cancelListener);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        View contentView = View.inflate(getContext(), R.layout.dialog_bottom_sheet, null);
        setContentView(contentView);

        final Window window = getWindow();
        if(window != null) {
            window.getAttributes().windowAnimations = R.style.DialogAnimation;
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            //window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        txt_camera = (TextView) contentView.findViewById(R.id.txt_camera);
        txt_gallery = (TextView) contentView.findViewById(R.id.txt_gallery);
        txt_camera.setOnClickListener(this);
        txt_gallery.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_camera:
                dismiss();
                if(listener != null)
                    listener.onCameraClick();
                break;
            case R.id.txt_gallery:
                dismiss();
                if(listener != null)
                    listener.onGalleryClick();
                break;
        }
    }

    public interface AlertListener {
        void onCameraClick();
        void onGalleryClick();
    }
}
