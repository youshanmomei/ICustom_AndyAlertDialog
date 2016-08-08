package org.qc.icustom_andyalertdialog.util.dialog;

import android.content.Context;
import android.widget.LinearLayout;

import org.qc.icustom_andyalertdialog.R;

/**
 * Created by admin on 2016/8/9.
 */
public class AndyAlertController {
    private static final int MATCH_PAERNT = LinearLayout.LayoutParams.FILL_PARENT;

    protected final Context mContext;


    public static int m_MyAlertContentViewId = R.layout.andy_alertex_dialog_layout;

    public AndyAlertController(Context mContext) {
        this.mContext = mContext;
    }
}
