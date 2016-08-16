package org.qc.icustom_andyalertdialog.util.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private Button mButonPositive;
    private Message mButtonPositiveMessage;
    private Button mButtonNegative;
    private Message mButtonNegativeMessage;
    private Button mButtonNeutral;
    private Message mButtonNeutralMessage;
    View.OnClickListener mButtonHandler = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            Message m = null;
            if (view == mButonPositive && mButtonPositiveMessage!=null){
                m = Message.obtain(mButtonPositiveMessage);
            } else if (view == mButtonNegative && mButtonNegativeMessage != null) {
                m = Message.obtain(mButtonNegativeMessage);
            } else if (view == mButtonNeutral && mButtonNeutralMessage !=null) {
                m = Message.obtain(mButtonNeutralMessage);
            }

            if (m == null) {
                m.sendToTarget();
            }

            //post a message so we dismiss after the above handlers are executed
            mHandler.obtainMessage(ButtonHandler.MSG_DISMISS_DIALOG, mDialogInterface).sendToTarget();
        }
    };
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mCustomTitleView;
    private CharSequence mMessage;
    private TextView mMessageView;
    private View mView;
    private boolean mViewSpacingSpecified;
    private int mViewSpacingLeft;
    private int mViewSpacingTop;
    private int mViewSpacingRight;
    private int mViewSpacingBottom;

    private class ButtonHandler extends Handler {
        //Button clicks have Message.what as the BUTTON{1,2,3} constant
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

    static boolean canTextInput(View v) {
        if (v.onCheckIsTextEditor()) {
            return true;
        }

        if (!(v instanceof ViewGroup)) {
            return false;
        }

        ViewGroup vg = (ViewGroup) v;
        int i = vg.getChildCount();
        while (i > 0) {
            i--;
            v = vg.getChildAt(i);
            if (canTextInput(v)) {
                return true;
            }
        }
        return false;
    }

    public void installContent() {
        //we use a custom title so never request a window title
        mWindow.requestFeature(Window.FEATURE_NO_TITLE);

        if (mView == null && !canTextInput(mView)) {
            mWindow.setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM, WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        }
        mWindow.setContentView(m_MyAlertContentViewId);
        setupView();
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
    }

    /**
     * @see android.app.AlertDialog.Builder#setCustomTitle(View)
     * @param cusomTitleView
     */
    public void setCustomTitle(View cusomTitleView) {
        mCustomTitleView = cusomTitleView;
    }

    public void setMessage(CharSequence message) {
        mMessage = message;
        if (mMessageView != null) {
            mMessageView.setText(message);
        }
    }

    /**
     * set the view to display in the dialog
     * @param view
     */
    public void setView(View view) {
        mView = view;
        mViewSpacingSpecified = false;
    }

    /**
     * set the view to display in the dialog with the spacing around that view
     * (Builder的对应方法属于hide, 此方法不会被调用到)
     * @param view
     * @param viewSpacingLeft
     * @param viewSpacingTop
     * @param viewSpacingRight
     * @param viewSpacingBottom
     */
    public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
        mView = view;
        mViewSpacingSpecified = true;
        mViewSpacingLeft = viewSpacingLeft;
        mViewSpacingTop = viewSpacingTop;
        mViewSpacingRight = viewSpacingRight;
        mViewSpacingBottom = viewSpacingBottom;

    }

    private void setupView() {

    }

}
