package org.qc.icustom_andyalertdialog.util.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
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
    View.OnClickListener mButtonHandler = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Message m = null;
            if (view == mButonPositive && mButtonPositiveMessage != null) {
                m = Message.obtain(mButtonPositiveMessage);
            } else if (view == mButtonNegative && mButtonNegativeMessage != null) {
                m = Message.obtain(mButtonNegativeMessage);
            } else if (view == mButtonNeutral && mButtonNeutralMessage != null) {
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
    private CharSequence mButtonPositiveText;
    private CharSequence mButonNegativeText;
    private CharSequence mButtonNeutralText;
    private Drawable mIcon;
    private ImageView mIconView;
    private boolean mForceInverseBackground;
    private ListView mListView;
    private Button mButtonPositive;
    private ScrollView mScrollView;
    private int mIconId = 0;//0 -> hide icon, -1 -> show icon
    private ListAdapter mAdapter;
    private int mCheckedItem = -1;

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
                    ((DialogInterface) msg.obj).dismiss();
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
     * @param cusomTitleView
     * @see android.app.AlertDialog.Builder#setCustomTitle(View)
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
     *
     * @param view
     */
    public void setView(View view) {
        mView = view;
        mViewSpacingSpecified = false;
    }

    /**
     * set the view to display in the dialog with the spacing around that view
     * (Builder的对应方法属于hide, 此方法不会被调用到)
     *
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

    public void setButton(int whichButton, CharSequence text, DialogInterface.OnClickListener listener, Message msg) {
        if (msg == null && listener != null) {
            msg = mHandler.obtainMessage(whichButton, listener);
        }

        switch (whichButton) {
            case DialogInterface.BUTTON_POSITIVE:
                mButtonPositiveText = text;
                mButtonPositiveMessage = msg;
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                mButonNegativeText = text;
                mButtonNegativeMessage = msg;
                break;

            case DialogInterface.BUTTON_NEUTRAL:
                mButtonNeutralText = text;
                mButtonNeutralMessage = msg;
                break;

            default:
                throw new IllegalArgumentException("Button does not exit");
        }
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
        if ((mIconView != null) && (mIcon != null)) {
            mIconView.setImageDrawable(icon);
        }
    }

    public void setInverseBackgroundForced(boolean forceInverseBackground) {
        mForceInverseBackground = forceInverseBackground;
    }

    public ListView getListView() {
        return mListView;
    }

    public Button getButton(int whichButton) {
        switch (whichButton) {
            case DialogInterface.BUTTON_POSITIVE:
                return mButtonPositive;
            case DialogInterface.BUTTON_NEGATIVE:
                return mButtonNegative;
            case DialogInterface.BUTTON_NEUTRAL:
                return mButtonNeutral;
            default:
                return null;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mScrollView != null && mScrollView.executeKeyEvent(event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return mScrollView != null && mScrollView.executeKeyEvent(event);
    }

    private void setupView() {
        LinearLayout contentPanel = (LinearLayout) mWindow.findViewById(R.id.contentPanel);
        setUpContent(contentPanel);
        boolean hasButtons = setupButtons();

        LinearLayout topPanel = (LinearLayout) mWindow.findViewById(R.id.topPanel);
//        TypedArray a = mContext.obtainStyledAttributes(null, R.styleable.AndyAlertDialog, R.style.alertDialogStyle, 0);
        TypedArray a = mContext.obtainStyledAttributes(null, R.styleable.AndyAlertDialog, 0, 0);

        boolean hasTitle = setupTitle(topPanel);
        View buttonPanel = mWindow.findViewById(R.id.buttonPanel);
        if (!hasButtons) {
            buttonPanel.setVisibility(View.GONE);
            mWindow.findViewById(R.id.btnUpDivider).setVisibility(View.GONE);
        }

        FrameLayout customPanel = null;
        if (mView != null) {
            customPanel = (FrameLayout) mWindow.findViewById(R.id.customPanel);
            FrameLayout custom = (FrameLayout) mWindow.findViewById(R.id.custom);
            custom.addView(mView, new WindowManager.LayoutParams(MATCH_PAERNT, MATCH_PAERNT));

            if (mViewSpacingSpecified) {
                custom.setPadding(mViewSpacingLeft, mViewSpacingTop, mViewSpacingRight, mViewSpacingBottom);
            }

            if (mListView != null) {
                ((LinearLayout.LayoutParams) customPanel.getLayoutParams()).weight = 0;
            }
        } else {
            mWindow.findViewById(R.id.customPanel).setVisibility(View.GONE);
        }

        if (hasTitle) {
            View divider = mWindow.findViewById(R.id.titleDivider);
            divider.setVisibility(View.VISIBLE);
        }

        setBackground(topPanel, contentPanel, customPanel, hasButtons, a, hasTitle, buttonPanel);
        a.recycle();

    }

    private boolean setupTitle(LinearLayout topPanel) {
        boolean hasTitle = true;

        //if has custom title, hide default title
        if (mCustomTitleView != null) {
            //add the custom title view directly to the topPanel layout
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            topPanel.addView(mCustomTitleView, 1, lp);

            //hide the title temple
            View titleTemplate = mWindow.findViewById(R.id.title_template);
            titleTemplate.setVisibility(View.GONE);
        } else {
            final boolean hasTextTitle = !TextUtils.isEmpty(mTitle);
            mIconView = (ImageView) mWindow.findViewById(R.id.icon);
            if (hasTextTitle) {
                /* Display the title if a title is supplied, else hide it */
                mTitleView = (TextView) mWindow.findViewById(R.id.alertTitle);
                mTitleView.setText(mTitle);
            }

            if (mIconView != null) {
                if (mIconId > 0) {
                    mIconView.setImageResource(mIconId);
                } else if (mIcon != null) {
                    mIconView.setImageDrawable(mIcon);
                } else if (mIconId == 0) {
                    mIconView.setVisibility(View.GONE);
                }
            } else {
                //hide the title template
                View titleTemplate = mWindow.findViewById(R.id.title_template);
                titleTemplate.setVisibility(View.GONE);
                hasTitle = false;
            }
        }
        return hasTitle;
    }


    private boolean setupButtons() {
        return false;
    }

    private void setUpContent(LinearLayout contentPanel) {
    }

    private void setBackground(LinearLayout topPanel, LinearLayout contentPanel, FrameLayout customPanel, boolean hasButtons, TypedArray a, boolean hasTitle, View buttonPanel) {
        //system default background
        int fullDark = a.getResourceId(R.styleable.AndyAlertDialog_fullDark, R.drawable.andy_alertex_dlg_bg_full_bright);
        int topDark = a.getResourceId(R.styleable.AndyAlertDialog_topDark, R.drawable.andy_alertex_dlg_bg_top_dark);
        int centerDark = a.getResourceId(R.styleable.AndyAlertDialog_centerDark, R.drawable.andy_alertex_dlg_bg_center_dark);
        int bottomDark = a.getResourceId(R.styleable.AndyAlertDialog_bottomDark, R.drawable.andy_alertex_dlg_bg_bottom_dark);
        int fullBright = a.getResourceId(R.styleable.AndyAlertDialog_fullBright, R.drawable.andy_alertex_dlg_bg_full_bright);
        int topBright = a.getResourceId(R.styleable.AndyAlertDialog_topBright, R.drawable.andy_alertex_dlg_bg_top_bright);
        int centerBright = a.getResourceId(R.styleable.AndyAlertDialog_centerBright, R.drawable.andy_alertex_dlg_bg_center_dark);
        int bottomBright = a.getResourceId(R.styleable.AndyAlertDialog_bottomBright, R.drawable.andy_alertex_dlg_bg_bottom_dark);
        int bottomMedium = a.getResourceId(R.styleable.AndyAlertDialog_bottomMedium, R.drawable.andy_alertex_dlg_bg_bottom_dark);

        View[] views = new View[4];
        boolean[] light = new boolean[4];
        View lastView = null;
        boolean lastLight = false;

        int pos = 0;
        if (hasTitle) {
            views[pos] = topPanel;
            light[pos] = true;
            pos++;
        }

        views[pos] = (contentPanel.getVisibility() == View.GONE) ? null : contentPanel;
        light[pos] = true;
        pos++;
        if (customPanel != null) {
            views[pos] = customPanel;
            light[pos] = true;
            pos++;
        }
        if (hasButtons) {
            views[pos] = customPanel;
            light[pos] = true;
        }

        boolean setView = false;
        for (pos=0; pos<views.length;pos++) {
            View v = views[pos];
            if (v == null) {
                continue;
            }
            if (lastView != null) {
                if (!setView) {
                    lastView.setBackgroundResource(lastLight ? topBright : topDark);
                } else {
                    lastView.setBackgroundResource(lastLight?centerBright:centerDark);
                }
                setView = true;
            }
            lastView = v;
            lastLight = light[pos];
        }

        if (lastView != null) {
            if (setView) {
                lastView.setBackgroundResource(lastLight ? (hasButtons ? bottomMedium : bottomBright) : bottomDark);
            } else {
                lastView.setBackgroundResource(lastLight?fullBright:fullDark);
            }
        }

        if ((mListView != null) && (mAdapter != null)) {
            mListView.setAdapter(mAdapter);
            if (mCheckedItem > -1) {
                mListView.setItemChecked(mCheckedItem, true);
                mListView.setSelection(mCheckedItem);
            }
        }

    }

    public static class RecycleListView extends ListView {
        boolean mRecycleOnMeasure = true;

        public RecycleListView(Context context) {
            super(context);
        }

        public RecycleListView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public RecycleListView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        protected boolean recycleOnMeasure(){
            return mRecycleOnMeasure;
        }
    }

    //TODO...

}
