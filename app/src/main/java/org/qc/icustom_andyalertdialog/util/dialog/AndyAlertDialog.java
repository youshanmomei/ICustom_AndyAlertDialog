package org.qc.icustom_andyalertdialog.util.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

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

    //TODO..
}
