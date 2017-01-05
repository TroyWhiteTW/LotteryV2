package com.lotteryv2;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by user on 2017/1/5.
 */

public class pDialog extends ProgressDialog {
    private ProgressDialog pDialog;

    pDialog(Context context) {
        super(context);

        pDialog = new ProgressDialog(context);
        pDialog.setTitle("资料传输中");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }
}
