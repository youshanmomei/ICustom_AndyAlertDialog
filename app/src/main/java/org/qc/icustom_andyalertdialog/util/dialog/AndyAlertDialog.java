package org.qc.icustom_andyalertdialog.util.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.view.View;
import android.widget.Button;
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
}
