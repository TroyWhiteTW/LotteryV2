package com.lotteryv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class QSActivity extends AppCompatActivity {
    private Button btn_List, btn_QG, btn_QS, btn_Member, btn_History;
    //    private Button btn_erDing, btn_sanDing, btn_siDing, btn_erXian, btn_sanXian, btn_siXian;
    private Button btn_qsr, btn_reset;
    private CheckBox cb_1, cb_2, cb_3, cb_4, cb_5, cb_6, cb_7, cb_8, cb_9, cb_10,
            cb_11, cb_12, cb_13, cb_14, cb_15, cb_16, cb_17, cb_18, cb_19, cb_20,
            cb_21, cb_22, cb_23, cb_24, cb_25, cb_26, cb_27, cb_28, cb_29, cb_30,
            cb_31, cb_32, cb_33, cb_34, cb_35, cb_36, cb_37, cb_38;
    private EditText et_1, et_2, et_3, et_4, et_5, et_6, et_7, et_8, et_9, et_10,
            et_11, et_12, et_13, et_14, et_15, et_16, et_17, et_18, et_19, et_20,
            et_21, et_22, et_23, et_24, et_25, et_26, et_27, et_28, et_29, et_30,
            et_31, et_32, et_33;
    private LinearLayout ll_1, ll_2, ll_3, ll_4, ll_5, ll_6, ll_7, ll_8, ll_9, ll_10,
            ll_11;
    private pDialog pDialog;
    private RadioGroup rg_1, rg_2, rg_3, rg_4, rg_5, rg_6, rg_7, rg_8, rg_9, rg_10,
            rg_11, rg_12, rg_13, rg_14, rg_15, rg_16, rg_17, rg_18, rg_19, rg_20,
            rg_21, rg_22;
    private String cookie, app_net, webside;
    private ScrollView sv_qs;
    private TextView tv_gameStyle, tv_gameOpenFalse, tv_gameOpenTrue;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qs);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        webside = it.getStringExtra("webside");

        initial();
        connectThread(0);
    }

    public void initial() {
        handler = new UIHandler();
        pDialog = new pDialog(this);

        app_net = "http://" + getResources().getString(R.string.app_net) + "/mobile/wap_ajax.php?action=";
        btn_List = (Button) findViewById(R.id.btn_qsList);
        btn_QG = (Button) findViewById(R.id.btn_qsQG);
        btn_QS = (Button) findViewById(R.id.btn_qsQS);
        btn_Member = (Button) findViewById(R.id.btn_qsMember);
        btn_History = (Button) findViewById(R.id.btn_qsHistory);
        btn_qsr = (Button) findViewById(R.id.btn_qsr);
        btn_reset = (Button) findViewById(R.id.btn_reset);
//        btn_erDing = (Button) findViewById(R.id.btn_erDing);
//        btn_sanDing = (Button) findViewById(R.id.btn_sanDing);
//        btn_siDing = (Button) findViewById(R.id.btn_siDing);
//        btn_erXian = (Button) findViewById(R.id.btn_erXian);
//        btn_sanXian = (Button) findViewById(R.id.btn_sanXian);
//        btn_siXian = (Button) findViewById(R.id.btn_siXian);
        cb_1 = (CheckBox) findViewById(R.id.cb_1);
        cb_2 = (CheckBox) findViewById(R.id.cb_2);
        cb_3 = (CheckBox) findViewById(R.id.cb_3);
        cb_4 = (CheckBox) findViewById(R.id.cb_4);
        cb_5 = (CheckBox) findViewById(R.id.cb_5);
        cb_6 = (CheckBox) findViewById(R.id.cb_6);
        cb_7 = (CheckBox) findViewById(R.id.cb_7);
        cb_8 = (CheckBox) findViewById(R.id.cb_8);
        cb_9 = (CheckBox) findViewById(R.id.cb_9);
        cb_10 = (CheckBox) findViewById(R.id.cb_10);
        cb_11 = (CheckBox) findViewById(R.id.cb_11);
        cb_12 = (CheckBox) findViewById(R.id.cb_12);
        cb_13 = (CheckBox) findViewById(R.id.cb_13);
        cb_14 = (CheckBox) findViewById(R.id.cb_14);
        cb_15 = (CheckBox) findViewById(R.id.cb_15);
        cb_16 = (CheckBox) findViewById(R.id.cb_16);
        cb_17 = (CheckBox) findViewById(R.id.cb_17);
        cb_18 = (CheckBox) findViewById(R.id.cb_18);
        cb_19 = (CheckBox) findViewById(R.id.cb_19);
        cb_20 = (CheckBox) findViewById(R.id.cb_20);
        cb_21 = (CheckBox) findViewById(R.id.cb_21);
        cb_22 = (CheckBox) findViewById(R.id.cb_22);
        cb_23 = (CheckBox) findViewById(R.id.cb_23);
        cb_24 = (CheckBox) findViewById(R.id.cb_24);
        cb_25 = (CheckBox) findViewById(R.id.cb_25);
        cb_26 = (CheckBox) findViewById(R.id.cb_26);
        cb_27 = (CheckBox) findViewById(R.id.cb_27);
        cb_28 = (CheckBox) findViewById(R.id.cb_28);
        cb_29 = (CheckBox) findViewById(R.id.cb_29);
        cb_30 = (CheckBox) findViewById(R.id.cb_30);
        cb_31 = (CheckBox) findViewById(R.id.cb_31);
        cb_32 = (CheckBox) findViewById(R.id.cb_32);
        cb_33 = (CheckBox) findViewById(R.id.cb_33);
        cb_34 = (CheckBox) findViewById(R.id.cb_34);
        cb_35 = (CheckBox) findViewById(R.id.cb_35);
        cb_36 = (CheckBox) findViewById(R.id.cb_36);
        cb_37 = (CheckBox) findViewById(R.id.cb_37);
        cb_38 = (CheckBox) findViewById(R.id.cb_38);
        et_1 = (EditText) findViewById(R.id.et_1);
        et_2 = (EditText) findViewById(R.id.et_2);
        et_3 = (EditText) findViewById(R.id.et_3);
        et_4 = (EditText) findViewById(R.id.et_4);
        et_5 = (EditText) findViewById(R.id.et_5);
        et_6 = (EditText) findViewById(R.id.et_6);
        et_7 = (EditText) findViewById(R.id.et_7);
        et_8 = (EditText) findViewById(R.id.et_8);
        et_9 = (EditText) findViewById(R.id.et_9);
        et_10 = (EditText) findViewById(R.id.et_10);
        et_11 = (EditText) findViewById(R.id.et_11);
        et_12 = (EditText) findViewById(R.id.et_12);
        et_13 = (EditText) findViewById(R.id.et_13);
        et_14 = (EditText) findViewById(R.id.et_14);
        et_15 = (EditText) findViewById(R.id.et_15);
        et_16 = (EditText) findViewById(R.id.et_16);
        et_17 = (EditText) findViewById(R.id.et_17);
        et_18 = (EditText) findViewById(R.id.et_18);
        et_19 = (EditText) findViewById(R.id.et_19);
        et_20 = (EditText) findViewById(R.id.et_20);
        et_21 = (EditText) findViewById(R.id.et_21);
        et_22 = (EditText) findViewById(R.id.et_22);
        et_23 = (EditText) findViewById(R.id.et_23);
        et_24 = (EditText) findViewById(R.id.et_24);
        et_25 = (EditText) findViewById(R.id.et_25);
        et_26 = (EditText) findViewById(R.id.et_26);
        et_27 = (EditText) findViewById(R.id.et_27);
        et_28 = (EditText) findViewById(R.id.et_28);
        et_29 = (EditText) findViewById(R.id.et_29);
        et_30 = (EditText) findViewById(R.id.et_30);
        et_31 = (EditText) findViewById(R.id.et_31);
        et_32 = (EditText) findViewById(R.id.et_32);
        et_33 = (EditText) findViewById(R.id.et_33);
        ll_1 = (LinearLayout) findViewById(R.id.ll_1);
        ll_2 = (LinearLayout) findViewById(R.id.ll_2);
        ll_3 = (LinearLayout) findViewById(R.id.ll_3);
        ll_4 = (LinearLayout) findViewById(R.id.ll_4);
        ll_5 = (LinearLayout) findViewById(R.id.ll_5);
        ll_6 = (LinearLayout) findViewById(R.id.ll_6);
        ll_7 = (LinearLayout) findViewById(R.id.ll_7);
        ll_8 = (LinearLayout) findViewById(R.id.ll_8);
        ll_9 = (LinearLayout) findViewById(R.id.ll_9);
        ll_10 = (LinearLayout) findViewById(R.id.ll_10);
        ll_11 = (LinearLayout) findViewById(R.id.ll_11);
        rg_1 = (RadioGroup) findViewById(R.id.rg_1);
        rg_2 = (RadioGroup) findViewById(R.id.rg_2);
        rg_3 = (RadioGroup) findViewById(R.id.rg_3);
        rg_4 = (RadioGroup) findViewById(R.id.rg_4);
        rg_5 = (RadioGroup) findViewById(R.id.rg_5);
        rg_6 = (RadioGroup) findViewById(R.id.rg_6);
        rg_7 = (RadioGroup) findViewById(R.id.rg_7);
        rg_8 = (RadioGroup) findViewById(R.id.rg_8);
        rg_9 = (RadioGroup) findViewById(R.id.rg_9);
        rg_10 = (RadioGroup) findViewById(R.id.rg_10);
        rg_11 = (RadioGroup) findViewById(R.id.rg_11);
        rg_12 = (RadioGroup) findViewById(R.id.rg_12);
        rg_13 = (RadioGroup) findViewById(R.id.rg_13);
        rg_14 = (RadioGroup) findViewById(R.id.rg_14);
        rg_15 = (RadioGroup) findViewById(R.id.rg_15);
        rg_16 = (RadioGroup) findViewById(R.id.rg_16);
        rg_17 = (RadioGroup) findViewById(R.id.rg_17);
        rg_18 = (RadioGroup) findViewById(R.id.rg_18);
        rg_19 = (RadioGroup) findViewById(R.id.rg_19);
        rg_20 = (RadioGroup) findViewById(R.id.rg_20);
        rg_21 = (RadioGroup) findViewById(R.id.rg_21);
        rg_22 = (RadioGroup) findViewById(R.id.rg_22);
        sv_qs = (ScrollView) findViewById(R.id.sv_qs);
        tv_gameStyle = (TextView) findViewById(R.id.tv_gameStyle);
        tv_gameOpenFalse = (TextView) findViewById(R.id.tv_gameOpenFalse);
        tv_gameOpenTrue = (TextView) findViewById(R.id.tv_gameOpenTrue);

        ll_1.setVisibility(View.GONE);
        ll_2.setVisibility(View.GONE);
        ll_3.setVisibility(View.GONE);
        ll_4.setVisibility(View.GONE);
        ll_5.setVisibility(View.GONE);
        ll_6.setVisibility(View.GONE);
        ll_7.setVisibility(View.GONE);
        ll_8.setVisibility(View.GONE);
        ll_9.setVisibility(View.GONE);
        ll_10.setVisibility(View.GONE);
        ll_11.setVisibility(View.GONE);
        rg_1.setVisibility(View.GONE);
        rg_2.setVisibility(View.GONE);
        rg_3.setVisibility(View.GONE);
        rg_4.setVisibility(View.GONE);
        rg_5.setVisibility(View.GONE);
        rg_6.setVisibility(View.GONE);
        rg_7.setVisibility(View.GONE);
        rg_8.setVisibility(View.GONE);
        rg_9.setVisibility(View.GONE);
        rg_10.setVisibility(View.GONE);
        rg_11.setVisibility(View.GONE);
        rg_12.setVisibility(View.GONE);
        rg_13.setVisibility(View.GONE);
        rg_14.setVisibility(View.GONE);
        rg_15.setVisibility(View.GONE);
        rg_16.setVisibility(View.GONE);
        rg_17.setVisibility(View.GONE);
        rg_18.setVisibility(View.GONE);
        rg_19.setVisibility(View.GONE);
        rg_20.setVisibility(View.GONE);
        rg_21.setVisibility(View.GONE);
        rg_22.setVisibility(View.GONE);

        btnClick();
    }

    public void btnClick() {
        btn_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QSActivity.this, ListActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_QG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QSActivity.this, QGActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_QS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(QSActivity.this, QSActivity.class);
//                it.putExtra("cookie", cookie);
//                startActivity(it);
//                finish();
            }
        });
        btn_Member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QSActivity.this, MemberActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QSActivity.this, HistoryActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_qsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QSSet set = new QSSet(1);
                set.quShuangChong();
                Log(set.getSet());
                Log("size = " + set.getSetSize());
                Intent it = new Intent(QSActivity.this, QSRActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("size", String.valueOf(set.getSetSize()));
                it.putExtra("set", set.getSet());
                startActivity(it);
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    public void btn_erDing(View view) {
        tv_gameStyle.setText("---二字定---");
        reset();
        ll_1.setVisibility(View.VISIBLE);
        ll_9.setVisibility(View.GONE);
        ll_10.setVisibility(View.VISIBLE);
        ll_11.setVisibility(View.VISIBLE);
        rg_1.setVisibility(View.VISIBLE);
        rg_2.setVisibility(View.GONE);
        rg_3.setVisibility(View.VISIBLE);
        rg_4.setVisibility(View.VISIBLE);
        rg_5.setVisibility(View.GONE);
        rg_6.setVisibility(View.VISIBLE);
        rg_7.setVisibility(View.GONE);
        rg_8.setVisibility(View.GONE);
        rg_9.setVisibility(View.VISIBLE);
        rg_10.setVisibility(View.GONE);
        rg_11.setVisibility(View.GONE);
        rg_12.setVisibility(View.GONE);
        rg_13.setVisibility(View.VISIBLE);
        rg_14.setVisibility(View.GONE);
        rg_15.setVisibility(View.GONE);
        rg_16.setVisibility(View.VISIBLE);
        rg_17.setVisibility(View.GONE);
        rg_18.setVisibility(View.GONE);
        rg_19.setVisibility(View.VISIBLE);
        rg_20.setVisibility(View.GONE);
        rg_21.setVisibility(View.GONE);
        rg_22.setVisibility(View.VISIBLE);
    }

    public void btn_sanDing(View view) {
        tv_gameStyle.setText("---三字定---");
        reset();
        ll_1.setVisibility(View.VISIBLE);
        ll_9.setVisibility(View.GONE);
        ll_10.setVisibility(View.VISIBLE);
        ll_11.setVisibility(View.VISIBLE);
        rg_1.setVisibility(View.VISIBLE);
        rg_2.setVisibility(View.GONE);
        rg_3.setVisibility(View.VISIBLE);
        rg_4.setVisibility(View.GONE);
        rg_5.setVisibility(View.VISIBLE);
        rg_6.setVisibility(View.GONE);
        rg_7.setVisibility(View.VISIBLE);
        rg_8.setVisibility(View.GONE);
        rg_9.setVisibility(View.VISIBLE);
        rg_10.setVisibility(View.GONE);
        rg_11.setVisibility(View.VISIBLE);
        rg_12.setVisibility(View.GONE);
        rg_13.setVisibility(View.VISIBLE);
        rg_14.setVisibility(View.VISIBLE);
        rg_15.setVisibility(View.GONE);
        rg_16.setVisibility(View.VISIBLE);
        rg_17.setVisibility(View.GONE);
        rg_18.setVisibility(View.GONE);
        rg_19.setVisibility(View.VISIBLE);
        rg_20.setVisibility(View.GONE);
        rg_21.setVisibility(View.GONE);
        rg_22.setVisibility(View.VISIBLE);
    }

    public void btn_siDing(View view) {
        tv_gameStyle.setText("---四字定---");
        reset();
        ll_1.setVisibility(View.VISIBLE);
        ll_9.setVisibility(View.VISIBLE);
        ll_10.setVisibility(View.VISIBLE);
        ll_11.setVisibility(View.VISIBLE);
        rg_1.setVisibility(View.VISIBLE);
        rg_2.setVisibility(View.GONE);
        rg_3.setVisibility(View.VISIBLE);
        rg_4.setVisibility(View.GONE);
        rg_5.setVisibility(View.VISIBLE);
        rg_6.setVisibility(View.GONE);
        rg_7.setVisibility(View.GONE);
        rg_8.setVisibility(View.VISIBLE);
        rg_9.setVisibility(View.VISIBLE);
        rg_10.setVisibility(View.VISIBLE);
        rg_11.setVisibility(View.VISIBLE);
        rg_12.setVisibility(View.VISIBLE);
        rg_13.setVisibility(View.VISIBLE);
        rg_14.setVisibility(View.VISIBLE);
        rg_15.setVisibility(View.VISIBLE);
        rg_16.setVisibility(View.VISIBLE);
        rg_17.setVisibility(View.GONE);
        rg_18.setVisibility(View.GONE);
        rg_19.setVisibility(View.VISIBLE);
        rg_20.setVisibility(View.GONE);
        rg_21.setVisibility(View.GONE);
        rg_22.setVisibility(View.VISIBLE);
    }

    public void btn_erXian(View view) {
        tv_gameStyle.setText("---二字現---");
        reset();
        ll_1.setVisibility(View.VISIBLE);
        ll_9.setVisibility(View.GONE);
        ll_10.setVisibility(View.GONE);
        ll_11.setVisibility(View.GONE);
        rg_1.setVisibility(View.GONE);
        rg_2.setVisibility(View.VISIBLE);
        rg_3.setVisibility(View.GONE);
        rg_4.setVisibility(View.VISIBLE);
        rg_5.setVisibility(View.GONE);
        rg_6.setVisibility(View.VISIBLE);
        rg_7.setVisibility(View.GONE);
        rg_8.setVisibility(View.GONE);
        rg_9.setVisibility(View.VISIBLE);
        rg_10.setVisibility(View.GONE);
        rg_11.setVisibility(View.GONE);
        rg_12.setVisibility(View.GONE);
        rg_13.setVisibility(View.VISIBLE);
        rg_14.setVisibility(View.GONE);
        rg_15.setVisibility(View.GONE);
        rg_16.setVisibility(View.VISIBLE);
        rg_17.setVisibility(View.VISIBLE);
        rg_18.setVisibility(View.GONE);
        rg_19.setVisibility(View.GONE);
        rg_20.setVisibility(View.VISIBLE);
        rg_21.setVisibility(View.GONE);
        rg_22.setVisibility(View.GONE);
    }

    public void btn_sanXian(View view) {
        tv_gameStyle.setText("---三字現---");
        reset();
        ll_1.setVisibility(View.VISIBLE);
        ll_9.setVisibility(View.GONE);
        ll_10.setVisibility(View.GONE);
        ll_11.setVisibility(View.GONE);
        rg_1.setVisibility(View.GONE);
        rg_2.setVisibility(View.VISIBLE);
        rg_3.setVisibility(View.GONE);
        rg_4.setVisibility(View.GONE);
        rg_5.setVisibility(View.VISIBLE);
        rg_6.setVisibility(View.GONE);
        rg_7.setVisibility(View.VISIBLE);
        rg_8.setVisibility(View.GONE);
        rg_9.setVisibility(View.VISIBLE);
        rg_10.setVisibility(View.GONE);
        rg_11.setVisibility(View.VISIBLE);
        rg_12.setVisibility(View.GONE);
        rg_13.setVisibility(View.VISIBLE);
        rg_14.setVisibility(View.VISIBLE);
        rg_15.setVisibility(View.GONE);
        rg_16.setVisibility(View.VISIBLE);
        rg_17.setVisibility(View.GONE);
        rg_18.setVisibility(View.VISIBLE);
        rg_19.setVisibility(View.GONE);
        rg_20.setVisibility(View.GONE);
        rg_21.setVisibility(View.VISIBLE);
        rg_22.setVisibility(View.GONE);
    }

    public void btn_siXian(View view) {
        tv_gameStyle.setText("---四字現---");
        reset();
        ll_1.setVisibility(View.VISIBLE);
        ll_9.setVisibility(View.GONE);
        ll_10.setVisibility(View.GONE);
        ll_11.setVisibility(View.GONE);
        rg_1.setVisibility(View.GONE);
        rg_2.setVisibility(View.VISIBLE);
        rg_3.setVisibility(View.GONE);
        rg_4.setVisibility(View.GONE);
        rg_5.setVisibility(View.VISIBLE);
        rg_6.setVisibility(View.GONE);
        rg_7.setVisibility(View.GONE);
        rg_8.setVisibility(View.VISIBLE);
        rg_9.setVisibility(View.VISIBLE);
        rg_10.setVisibility(View.GONE);
        rg_11.setVisibility(View.VISIBLE);
        rg_12.setVisibility(View.VISIBLE);
        rg_13.setVisibility(View.VISIBLE);
        rg_14.setVisibility(View.VISIBLE);
        rg_15.setVisibility(View.VISIBLE);
        rg_16.setVisibility(View.VISIBLE);
        rg_17.setVisibility(View.GONE);
        rg_18.setVisibility(View.GONE);
        rg_19.setVisibility(View.VISIBLE);
        rg_20.setVisibility(View.GONE);
        rg_21.setVisibility(View.GONE);
        rg_22.setVisibility(View.VISIBLE);
    }

    public void reset() {
        cb_1.setChecked(false);
        cb_2.setChecked(false);
        cb_3.setChecked(false);
        cb_4.setChecked(false);
        cb_5.setChecked(false);
        cb_6.setChecked(false);
        cb_7.setChecked(false);
        cb_8.setChecked(false);
        cb_9.setChecked(false);
        cb_10.setChecked(false);
        cb_11.setChecked(false);
        cb_12.setChecked(false);
        cb_13.setChecked(false);
        cb_14.setChecked(false);
        cb_15.setChecked(false);
        cb_16.setChecked(false);
        cb_17.setChecked(false);
        cb_18.setChecked(false);
        cb_19.setChecked(false);
        cb_20.setChecked(false);
        cb_21.setChecked(false);
        cb_22.setChecked(false);
        cb_23.setChecked(false);
        cb_24.setChecked(false);
        cb_25.setChecked(false);
        cb_26.setChecked(false);
        cb_27.setChecked(false);
        cb_28.setChecked(false);
        cb_29.setChecked(false);
        cb_30.setChecked(false);
        cb_31.setChecked(false);
        cb_32.setChecked(false);
        cb_33.setChecked(false);
        cb_34.setChecked(false);
        cb_35.setChecked(false);
        cb_36.setChecked(false);
        cb_37.setChecked(false);
        cb_38.setChecked(false);
        et_1.setText("");
        et_2.setText("");
        et_3.setText("");
        et_4.setText("");
        et_5.setText("");
        et_6.setText("");
        et_7.setText("");
        et_8.setText("");
        et_9.setText("");
        et_10.setText("");
        et_11.setText("");
        et_12.setText("");
        et_13.setText("");
        et_14.setText("");
        et_15.setText("");
        et_16.setText("");
        et_17.setText("");
        et_18.setText("");
        et_19.setText("");
        et_20.setText("");
        et_21.setText("");
        et_22.setText("");
        et_23.setText("");
        et_24.setText("");
        et_25.setText("");
        et_26.setText("");
        et_27.setText("");
        et_28.setText("");
        et_29.setText("");
        et_30.setText("");
        et_31.setText("");
        et_32.setText("");
        et_33.setText("");
        ll_2.setVisibility(View.GONE);
        ll_3.setVisibility(View.GONE);
        ll_4.setVisibility(View.GONE);
        ll_5.setVisibility(View.GONE);
        ll_6.setVisibility(View.GONE);
        ll_7.setVisibility(View.GONE);
        ll_8.setVisibility(View.GONE);
        rg_1.clearCheck();
        rg_2.clearCheck();
        rg_3.clearCheck();
        rg_4.clearCheck();
        rg_5.clearCheck();
        rg_6.clearCheck();
        rg_7.clearCheck();
        rg_8.clearCheck();
        rg_9.clearCheck();
        rg_10.clearCheck();
        rg_11.clearCheck();
        rg_12.clearCheck();
        rg_13.clearCheck();
        rg_14.clearCheck();
        rg_15.clearCheck();
        rg_16.clearCheck();
        rg_17.clearCheck();
        rg_18.clearCheck();
        rg_19.clearCheck();
        rg_20.clearCheck();
        rg_21.clearCheck();
        rg_22.clearCheck();
    }

    public void connectThread(final int connectThreadType) {
        pDialog.show();
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                switch (connectThreadType) {
                    case 0:
                        getData();
                        break;
                    case 1:

                        break;
                    default:
                        break;
                }
                Looper.loop();
            }
        }.start();
    }

    public void getData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw(app_net + "app_head_data");
            mu.sendCookie(cookie);
            JSONObject jo = mu.getJSONObjectData();

            Message msg = new Message();
            Bundle b = new Bundle();
            Log("game_open = " + jo.getInt("game_open"));
            b.putInt("game_open", jo.getInt("game_open"));
            msg.setData(b);
            handler.sendMessage(msg);
        } catch (Exception e) {
            Toast("无法与伺服器取得连线");
            Log(e.toString());
        }
        handler.sendEmptyMessage(1);
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log("msg.what = " + msg.what);
            switch (msg.what) {
                case 0:
                    int gameOpenCode = msg.getData().getInt("game_open");
                    switch (gameOpenCode) {
                        case 0:
                            gameOpenToast(0);
                            tv_gameOpenFalse.setVisibility(View.VISIBLE);
                            sv_qs.setVisibility(View.GONE);
                            break;
                        case 1:
                            gameOpenToast(1);
                            tv_gameOpenTrue.setVisibility(View.VISIBLE);
                            sv_qs.setVisibility(View.VISIBLE);
                            break;
                        default:
                            break;
                    }
                    break;
                case 1:
                    if (pDialog.isShowing()) pDialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    }

    public void gameOpenToast(int i) {
        switch (i) {
            case 0:
                Toast("關盤中");
                break;
            case 1:
                Toast("開盤中");
                break;
            default:
                break;
        }
    }

    public void Toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void Log(String s) {
        Log.i("troy", s);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        確定按下退出鍵and防止重複按下退出鍵
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) exitDialog();
        return false;
    }

    public void exitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//創建訊息方塊
        builder.setMessage("确定要离开吗？");
        builder.setTitle("关闭这个应用程式");
        builder.setPositiveButton("离开", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();//dismiss為關閉dialog,Activity還會保留dialog的狀態
                QSActivity.this.finish();//關閉activity
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
