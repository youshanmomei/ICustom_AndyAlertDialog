package org.qc.icustom_andyalertdialog.util.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.qc.icustom_andyalertdialog.R;

/**
 * Created by admin on 2016/7/22.
 */
public class AndyAlertDialog extends AlertDialog implements DialogInterface {
    private AndyAlertDialog mAlert;

    protected AndyAlertDialog(Context context) {
        this(context, R.style.Andy_Theme_Dialog_Alert);
    }

    protected AndyAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
//        mAlert = new AndyAlertController(context, this, getWindow());
    }

    protected AndyAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, R.style.Andy_Theme_Dialog_Alert);
        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
//        mAlert = new AndyAlertController(context, this, getWindow());
    }

    public Button getButton(int whichButton) {
        return mAlert.getButton(whichButton);
    }

    /**
     * gets the listView used in the dialog
     * @return The {@link ListView} from dialog
     */
    public ListView getListLiswView() {
        return mAlert.getListView();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        mAlert.setTitle(title);
    }

    /**
//     * @see Builder#setCustomTitle(view)
     * @param customTitleView
     */
    public void setCustomTitle(View customTitleView) {
        mAlert.setCustomTitle(customTitleView);
    }

    /**
     * Set the view to display in that dialog
     * @param view
     */
    public void setView(View view) {
        mAlert.setView(view);
    }

    /**
     * Set the view to display in that dialog, specifying the spacing to appear around that view
     * @param view The view to show in the content area of the dialog
     * @param viewSpacingLeft Extra space to appear to the left of {@code view}
     * @param viewSpacingTop Extra space to appear above {@code view}
     * @param viewSpacingRight Extra space to appear to the right of {@code view}
     * @param viewSpacingBottom Extra space to appear below{@code view}
     */
    public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
        mAlert.setView(view, viewSpacingLeft, viewSpacingTop, viewSpacingRight, viewSpacingBottom);
    }

    /**
     * set a message to be sent when a button is pressed.
     *
     * @param whichButton
     *          which button to set the message for, can be one of
     *          {@link DialogInterface#BUTTON_POSITIVE},
     *          {@link DialogInterface#BUTTON_NEGATIVE}, or
     *          {@link DialogInterface#BUTTON_NEUTRAL}
     * @param text the text display in positive button
     * @param msg The {@link Message} to be sent when clicked
     */
    public void setButton(int whichButton, CharSequence text, Message msg) {
        mAlert.setButton(whichButton, text, msg);
    }

    /**
     * set a listener to be invoked when the positive button of the dialog is pressed.
     * @param whichButton
     *          Which button to set the listener on, can be one of
     *          {@link DialogInterface#BUTTON_POSITIVE},
     *          {@link DialogInterface#BUTTON_NEGATIVE}, or
     *          {@link DialogInterface#BUTTON_NEUTRAL}
     * @param text the text to display in positive button
     * @param listener the {@link android.content.DialogInterface.OnClickListener} to use
     */
    public void setButton(int whichButton, CharSequence text, OnClickListener listener) {
        mAlert.setButton(whichButton, text, listener);
    }

    /**
     * @deprecated  Use {@link #setButton(int, CharSequence, Message)} with
     *               {@link DialogInterface#BUTTON_POSITIVE}
     * @param text
     * @param msg
     */
    @Deprecated
    public void setButton(CharSequence text, Message msg) {
        setButton(BUTTON_POSITIVE, text, msg);
    }

    /**
     * @deprecated Use {@link #setButton(int, CharSequence, Message)} with
     *              {@link DialogInterface#BUTTON_NEGATIVE}
     * @param text
     * @param msg
     */
    @Deprecated
    public void setButton2(CharSequence text, Message msg) {
        setButton(BUTTON_NEGATIVE, text, msg);
    }

    /**
     * @deprecated Use {@link #setButton(int, CharSequence, OnClickListener)} with
     *              {@link DialogInterface#BUTTON_NEUTRAL}
     * @param text
     * @param msg
     */
    @Deprecated
    public void setButton3(CharSequence text, Message msg) {
        setButton(BUTTON_NEUTRAL, text, msg);
    }

    /**
     * set a listener to be invoke when button 1 of the dialog is pressed
     *
     * @param text the text to display in button 1.
     * @param listener the {@link android.content.DialogInterface.OnClickListener} to use.
     * @deprecated  use
     *               {@link #setButton(int, CharSequence, OnClickListener)}
     *               with {@link DialogInterface#BUTTON_NEGATIVE}
     */
    @Deprecated
    public void setButton(CharSequence text, OnClickListener listener) {
        setButton(BUTTON_POSITIVE, text, listener);
    }

    /**
     * set a listener to be invoked when button 2 of the dialog is pressed
     *
     * @param text
     *          the text to display in button2
     * @param listener
     *          the {@link android.content.DialogInterface.OnClickListener} to use
     * @deprecated Use
     *          {@link #setButton(int, CharSequence, OnClickListener)} with {@link DialogInterface#BUTTON_NEGATIVE}
     */
    @Deprecated
    public void setButton2(CharSequence text, OnClickListener listener) {
        setButton(BUTTON_NEGATIVE, text, listener);
    }

    /**
     * set a listener to be invoked when button 3 of the dialog is pressed
     *
     * @param text
     *          the text to display in button 3
     * @param listener
     *          the {@link android.content.DialogInterface.OnClickListener} to use
     * @deprecated
     *          {@link #setButton(int, CharSequence, OnClickListener)} with {@link DialogInterface#BUTTON_NEUTRAL}
     */
    @Deprecated
    public void setButton3(CharSequence text, OnClickListener listener) {
        setButton(BUTTON_NEUTRAL, text, listener);
    }

    /**
     * set resId to 0 if you don't want to an icon.
     *
     * @param resId
     *          the resourceId of the drawable to use as the icon or 0 if you don't want to an icon
     */
    public void setIcon(int resId) {
        mAlert.setIcon(resId);
    }

    public void setIcon(Drawable icon) {
        mAlert.setIcon(icon);
    }

    public void setInverseBackgroundForced(boolean forceInverseBackground) {
        mAlert.setInverseBackgroundForced(forceInverseBackground);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        mAlert.installContent();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAlert.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (mAlert.onKeyUp(keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    public static class Builder extends AlertDialog.Builder {

//        private final AndyAlertController.AlertParams p;

        public Builder(Context context) {
            super(context);
//            p = new AndyAlertController.AlertParams(context);
        }

        public Builder setTitle(int titleId) {
//            p.mTitle = p.mContext.getText(titleId);
            return this;
        }

        public Builder setTitle(CharSequence title) {
//            p.mTitle = title;
            return this;
        }

        /**
         * custom title
         * priority is high than custom title
         * @param customTitleView
         * @return
         */
        public Builder setCustomTitle(View customTitleView) {
//            p.mCustomTitleView = customTitleView;
            return this;
        }

        public Builder setMessage(int messageId) {
//            p.mMessage = p.mContext.getText(messageId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
//            p.mMessage = message;
            return this;
        }

        public Builder setIcon(int iconId) {
//            p.mIconId = iconId;
            return this;
        }

        public Builder setIcon(Drawable icon) {
//            p.mIcon = icon;
            return this;
        }

        public Builder setPositiveButton(int textId, final OnClickListener listener) {
//            p.mPositiveButtonText = p.mContext.getText(textId);
//            p.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, final OnClickListener listener) {
//            p.mPositiveButtonText = text;
//            p.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(int textId, final OnClickListener listener) {
//            p.NegativeButtonText = p.mContext.getText(textId);
//            p.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNeturalButton(CharSequence textId, final OnClickListener listener) {
//            p.mNeturalButtonText = p.mContext.getText(textId);
//            p.mNeturalButtonListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
//            p.mCancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
//            p.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
//            p.mOnkeyListener = onKeyListener;
            return this;
        }

        public Builder setItems(int itemsId, final OnClickListener onClickListener) {
//            p.mItems = p.mContext.getResources().getTextArray(itemsId);
//            p.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setItems(CharSequence[] items, final OnClickListener onClickListener) {
//            p.mItems = items;
//            p.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setAdapter(final ListAdapter adapter, final OnClickListener listener) {
//            p.mAdapter = adapter;
//            p.mOnClickListener = listener;
            return this;
        }

        public Builder setCursor(final Cursor cursor, final OnClickListener listener, String labelColumn) {
//            p.mCursor = cursor;
//            p.mLableColumn = labelColumn;
//            p.mOnClickListener = listener;
            return this;
        }

        public Builder setMultiChoiceItems(int itemsId, boolean[] checkItems, final OnMultiChoiceClickListener listener) {
//            p.Items = p.mContext.getResource().getTextArray(itemsId);
//            p.mOnCheckboxClickListener = listener;
//            p.mCheckItems = checkItems;
//            p.mIsMultiChoice = true;
            return this;
        }
        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkItems, final OnMultiChoiceClickListener listener) {
//            p.Items = items;
//            p.mOnCheckboxClickListener = listener;
//            p.mCheckItems = checkItems;
//            p.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColum, String labelColumn, final OnMultiChoiceClickListener listener) {
//            p.mCursor = cursor;
//            p.mOnCheckboxClickListener = listener;
//            p.mIsCheckedColumn = isCheckedColum;
//            p.mLabelColumn = labelColumn;
//            p.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(int itemsId, int checkedItem, final OnClickListener listener) {
//            p.mItems = p.mContext.getResources().getTextArray(itemsId);
//            p.mOnClickListener = listener;
//            p.mCheckedItem = checkedItem;
//            p.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, final OnClickListener listener) {
//            p.mCursor = cursor;
//            p.mOnClickListener = listener;
//            p.mCheckItem = checkedItem;
//            p.mLabelColumn = labelColumn;
//            p.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] itemms, int checkedItem, final OnClickListener listener) {
//            p.mItems = itemms;
//            p.mOnClickListener = listener;
//            p.mCheckedItem = checkedItem;
//            p.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, final OnClickListener listener) {
//            p.mAdapter = adapter;
//            p.mOnClickListener = listener;
//            p.mCheckedItem = checkedItem;
//            p.mIsSingleChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(final AdapterView.OnItemSelectedListener listener) {
//            p.mOnItemSelectedListener = listener;
            return this;
        }

        public Builder setView(View view) {
//            p.mView = view;
//            p.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
//            p.mView = view;
//            p.mViewSpacingSpecified = true;
//            p.mViewSpacingLeft = viewSpacingLeft;
//            p.mViewSpacingTop = viewSpacingTop;
//            p.mViewSpacingRight = viewSpacingRight;
//            p.mViewSpacingBottom = viewSpacingBottom;
            return this;
        }

        //TODO...setInverseBackgroundForced
        public Builder setInverseBackgroundForced(boolean useInverseBackground){
//            p.mForceInverseBackground = useInverseBackground;
            return this;
        }

        public Builder setRecycleOnMeasureEnable(boolean enable) {
//            p.mRecycleOnMeasure = enable;
            return this;
        }

        @Override
        public AlertDialog create() {
//            AndyAlertDialog dialog = new AndyAlertDialog(p.mContext);
//            p.apply(dialog);
//
//            dialog.setCancelable(p.mCancelable);
//            if (p.mOnKeyListener != null) {
//                dialog.setOnKeyListener(p.mOnKeyListener);
//            }
//            return dialog;
            return null;
        }

        /**
         * this method can use parent class directly
         *
         * create AlertDialog process:
         * 1.create Builder instance B,set parameter to AndyController.AlertParams(ap)
         * 2.b use show(), and new an AlertDialog instance ad, and than ap use apply() make ac in AlertController about ad to ap
         * 3.set resource to ac in ap's apply()
         * 4.use ad's show() to make ac use installContent()
         *
         * @return
         */
        public AlertDialog show(){
            AlertDialog alertDialog = create();
            alertDialog.show();
            return alertDialog;
        }
    }

}
