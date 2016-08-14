package org.qc.icustom_andyalertdialog.util.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.LinearLayout;

import org.qc.icustom_andyalertdialog.R;

import java.lang.ref.WeakReference;

/**
 * Created by admin on 2016/8/9.
 */
public class AndyAlertController {
    private static final int MATCH_PAERNT = LinearLayout.LayoutParams.FILL_PARENT;

    protected final Context mContext;


    public static int m_MyAlertContentViewId = R.layout.andy_alertex_dialog_layout;
    private final DialogInterface mDialogInterface;
    private final Window mWindow;
    private final ButtonHandler mHandler;


    private class ButtonHandler extends Handler {
        private static final int MSG_DISMISS_DIALOG = 1;
        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialog) {
            WeakReference<DialogInterface> mDialog = new WeakReference<DialogInterface>(dialog);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DialogInterface.BUTTON_POSITIVE:
                case DialogInterface.BUTTON_NEGATIVE:
                case DialogInterface.BUTTON_NEUTRAL:
                    ((DialogInterface.OnClickListener) msg.obj).onClick(mDialog.get(), msg.what);
                    break;
                case MSG_DISMISS_DIALOG:
                    ((DialogInterface)msg.obj).dismiss();
            }
        }
    }

    public AndyAlertController(Context mContext, DialogInterface di, Window window) {
        this.mContext = mContext;
        mDialogInterface = di;
        mWindow = window;
        mHandler = new ButtonHandler(di);

    }
}
