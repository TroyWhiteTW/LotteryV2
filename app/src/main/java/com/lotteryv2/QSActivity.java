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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class QSActivity extends AppCompatActivity {
    private Button btn_List, btn_QG, btn_QS, btn_Member, btn_History;
    private Button btn_qsr, btn_reset;
    private CheckBox cb_1, cb_2, cb_3, cb_4, cb_5, cb_6, cb_7, cb_8, cb_9, cb_10,
            cb_11, cb_12, cb_13, cb_14, cb_15, cb_16, cb_17, cb_18, cb_19, cb_20,
            cb_21, cb_22, cb_23, cb_24, cb_25, cb_26, cb_27, cb_28, cb_29, cb_30,
            cb_31, cb_32, cb_33, cb_34, cb_35, cb_36, cb_37, cb_38;
    private EditText et_1, et_2, et_3, et_4, et_5, et_6, et_7, et_8, et_9, et_10,
            et_11, et_12, et_13, et_14, et_15, et_16, et_17, et_18, et_19, et_20,
            et_21, et_22, et_23, et_24, et_25, et_26, et_27, et_28, et_29, et_30,
            et_31, et_32, et_33, et_34, et_35, et_36, et_37, et_38, et_39;
    private int gameStyle = 0;//classID: 1=二定位; 2=三定位; 3=四定位; 4=二字現; 5=三字現; 6=四字現
    private LinearLayout ll_1, ll_2, ll_3, ll_4, ll_5, ll_6, ll_7, ll_8, ll_9, ll_10,
            ll_11;
    private QSSet set;
    private pDialog pDialog;
    private RadioButton rb_1, rb_2, rb_3, rb_4, rb_5, rb_6, rb_7, rb_8, rb_9, rb_10,
            rb_11, rb_12, rb_13, rb_14, rb_15, rb_16, rb_17, rb_18, rb_19, rb_20,
            rb_21, rb_22, rb_23, rb_24, rb_25, rb_26, rb_27, rb_28, rb_29, rb_30,
            rb_31, rb_32, rb_33, rb_34, rb_35, rb_36, rb_37, rb_38, rb_39, rb_40,
            rb_41, rb_42, rb_43, rb_44, rb_45, rb_46, rb_47, rb_48, rb_49, rb_50,
            rb_51;
    private RadioGroup rg_1, rg_2, rg_3, rg_4, rg_5, rg_6, rg_7, rg_8, rg_9, rg_10,
            rg_11, rg_12, rg_13, rg_14, rg_15, rg_16, rg_17, rg_18, rg_19, rg_20,
            rg_21, rg_22, rg_23, rg_24, rg_25;
    private String cookie, app_net, webside;
    private String selectlogs;
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
        et_34 = (EditText) findViewById(R.id.et_34);
        et_35 = (EditText) findViewById(R.id.et_35);
        et_36 = (EditText) findViewById(R.id.et_36);
        et_37 = (EditText) findViewById(R.id.et_37);
        et_38 = (EditText) findViewById(R.id.et_38);
        et_39 = (EditText) findViewById(R.id.et_39);
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
        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_3 = (RadioButton) findViewById(R.id.rb_3);
        rb_4 = (RadioButton) findViewById(R.id.rb_4);
        rb_5 = (RadioButton) findViewById(R.id.rb_5);
        rb_6 = (RadioButton) findViewById(R.id.rb_6);
        rb_7 = (RadioButton) findViewById(R.id.rb_7);
        rb_8 = (RadioButton) findViewById(R.id.rb_8);
        rb_9 = (RadioButton) findViewById(R.id.rb_9);
        rb_10 = (RadioButton) findViewById(R.id.rb_10);
        rb_11 = (RadioButton) findViewById(R.id.rb_11);
        rb_12 = (RadioButton) findViewById(R.id.rb_12);
        rb_13 = (RadioButton) findViewById(R.id.rb_13);
        rb_14 = (RadioButton) findViewById(R.id.rb_14);
        rb_15 = (RadioButton) findViewById(R.id.rb_15);
        rb_16 = (RadioButton) findViewById(R.id.rb_16);
        rb_17 = (RadioButton) findViewById(R.id.rb_17);
        rb_18 = (RadioButton) findViewById(R.id.rb_18);
        rb_19 = (RadioButton) findViewById(R.id.rb_19);
        rb_20 = (RadioButton) findViewById(R.id.rb_20);
        rb_21 = (RadioButton) findViewById(R.id.rb_21);
        rb_22 = (RadioButton) findViewById(R.id.rb_22);
        rb_23 = (RadioButton) findViewById(R.id.rb_23);
        rb_24 = (RadioButton) findViewById(R.id.rb_24);
        rb_25 = (RadioButton) findViewById(R.id.rb_25);
        rb_26 = (RadioButton) findViewById(R.id.rb_26);
        rb_27 = (RadioButton) findViewById(R.id.rb_27);
        rb_28 = (RadioButton) findViewById(R.id.rb_28);
        rb_29 = (RadioButton) findViewById(R.id.rb_29);
        rb_30 = (RadioButton) findViewById(R.id.rb_30);
        rb_31 = (RadioButton) findViewById(R.id.rb_31);
        rb_32 = (RadioButton) findViewById(R.id.rb_32);
        rb_33 = (RadioButton) findViewById(R.id.rb_33);
        rb_34 = (RadioButton) findViewById(R.id.rb_34);
        rb_35 = (RadioButton) findViewById(R.id.rb_35);
        rb_36 = (RadioButton) findViewById(R.id.rb_36);
        rb_37 = (RadioButton) findViewById(R.id.rb_37);
        rb_38 = (RadioButton) findViewById(R.id.rb_38);
        rb_39 = (RadioButton) findViewById(R.id.rb_39);
        rb_40 = (RadioButton) findViewById(R.id.rb_40);
        rb_41 = (RadioButton) findViewById(R.id.rb_41);
        rb_42 = (RadioButton) findViewById(R.id.rb_42);
        rb_43 = (RadioButton) findViewById(R.id.rb_43);
        rb_44 = (RadioButton) findViewById(R.id.rb_44);
        rb_45 = (RadioButton) findViewById(R.id.rb_45);
        rb_46 = (RadioButton) findViewById(R.id.rb_46);
        rb_47 = (RadioButton) findViewById(R.id.rb_47);
        rb_48 = (RadioButton) findViewById(R.id.rb_48);
        rb_49 = (RadioButton) findViewById(R.id.rb_49);
        rb_50 = (RadioButton) findViewById(R.id.rb_50);
        rb_51 = (RadioButton) findViewById(R.id.rb_51);
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
        rg_23 = (RadioGroup) findViewById(R.id.rg_23);
        rg_24 = (RadioGroup) findViewById(R.id.rg_24);
        rg_25 = (RadioGroup) findViewById(R.id.rg_25);
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
        rg_23.setVisibility(View.GONE);
        rg_24.setVisibility(View.GONE);
        rg_25.setVisibility(View.GONE);

        btnClick();
        rbClick();
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
                qsSet();
                selectlogs();
                Intent it = new Intent(QSActivity.this, QSRActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("gameStyle", String.valueOf(gameStyle));
                it.putExtra("selectlogs", selectlogs);
                it.putExtra("size", String.valueOf(set.getSetSize()));
                it.putStringArrayListExtra("set", set.getSet());
                startActivity(it);
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAll();
            }
        });
    }

    public void qsSet() {
        set = new QSSet(gameStyle);
        if (!et_26.getText().toString().equals("")) set.dingFuShi2(et_26.getText().toString());
        if (!et_28.getText().toString().equals("")) set.dingFuShi3(et_28.getText().toString());
        if (!et_30.getText().toString().equals("")) set.dingFuShi4(et_30.getText().toString());
        StringBuffer quanZhuanEt = new StringBuffer(et_22.getText().toString());
        switch (gameStyle) {
            case 1:
                if (quanZhuanEt.length() >= 2) set.quanZhuan2(et_22.getText().toString());
                break;
            case 2:
                if (quanZhuanEt.length() >= 3) set.quanZhuan3(et_22.getText().toString());
                break;
            case 3:
                if (quanZhuanEt.length() >= 4) set.quanZhuan4(et_22.getText().toString());
                break;
        }
        if (rb_1.isChecked()) {//定除
            set.dingWeiZhi_chu(0);
            if (!et_1.getText().toString().equals("")) {
                StringBuffer sb1 = new StringBuffer(et_1.getText().toString());
                HashMap<Integer, String> treeMap = new HashMap<>();
                for (int i = 0; i < sb1.length(); i++)
                    treeMap.put(i, sb1.substring(i, i + 1));
                ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                    @Override
                    public int compare(Map.Entry o1, Map.Entry o2) {
                        return ((String) o1.getValue()).compareTo((String) o2.getValue());
                    }
                };
                Collections.sort(entryList, sortByValue);
                LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                for (Map.Entry e : entryList)
                    linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                for (int i = 0; i < al1.size(); i++)
                    set.dingWeiZhi(al1.get(i).toString(), "", "", "");
                set.dingWeiZhi("X", "", "", "");
                set.dingWeiZhi();
            }
            if (!et_2.getText().toString().equals("")) {
                StringBuffer sb1 = new StringBuffer(et_2.getText().toString());
                HashMap<Integer, String> treeMap = new HashMap<>();
                for (int i = 0; i < sb1.length(); i++)
                    treeMap.put(i, sb1.substring(i, i + 1));
                ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                    @Override
                    public int compare(Map.Entry o1, Map.Entry o2) {
                        return ((String) o1.getValue()).compareTo((String) o2.getValue());
                    }
                };
                Collections.sort(entryList, sortByValue);
                LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                for (Map.Entry e : entryList)
                    linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                for (int i = 0; i < al1.size(); i++)
                    set.dingWeiZhi("", al1.get(i).toString(), "", "");
                set.dingWeiZhi("", "X", "", "");
                set.dingWeiZhi();
            }
            if (!et_3.getText().toString().equals("")) {
                StringBuffer sb1 = new StringBuffer(et_3.getText().toString());
                HashMap<Integer, String> treeMap = new HashMap<>();
                for (int i = 0; i < sb1.length(); i++)
                    treeMap.put(i, sb1.substring(i, i + 1));
                ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                    @Override
                    public int compare(Map.Entry o1, Map.Entry o2) {
                        return ((String) o1.getValue()).compareTo((String) o2.getValue());
                    }
                };
                Collections.sort(entryList, sortByValue);
                LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                for (Map.Entry e : entryList)
                    linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                for (int i = 0; i < al1.size(); i++)
                    set.dingWeiZhi("", "", al1.get(i).toString(), "");
                set.dingWeiZhi("", "", "X", "");
                set.dingWeiZhi();
            }
            if (!et_4.getText().toString().equals("")) {
                StringBuffer sb1 = new StringBuffer(et_4.getText().toString());
                HashMap<Integer, String> treeMap = new HashMap<>();
                for (int i = 0; i < sb1.length(); i++)
                    treeMap.put(i, sb1.substring(i, i + 1));
                ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                    @Override
                    public int compare(Map.Entry o1, Map.Entry o2) {
                        return ((String) o1.getValue()).compareTo((String) o2.getValue());
                    }
                };
                Collections.sort(entryList, sortByValue);
                LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                for (Map.Entry e : entryList)
                    linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                for (int i = 0; i < al1.size(); i++)
                    set.dingWeiZhi("", "", "", al1.get(i).toString());
                set.dingWeiZhi("", "", "", "X");
                set.dingWeiZhi();
            }
            set.dingWeiZhi_chu(1);
        }
        if (rb_2.isChecked()) {//定取
            if (!et_1.getText().toString().equals("")) {
                StringBuffer sb1 = new StringBuffer(et_1.getText().toString());
                HashMap<Integer, String> treeMap = new HashMap<>();
                for (int i = 0; i < sb1.length(); i++)
                    treeMap.put(i, sb1.substring(i, i + 1));
                ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                    @Override
                    public int compare(Map.Entry o1, Map.Entry o2) {
                        return ((String) o1.getValue()).compareTo((String) o2.getValue());
                    }
                };
                Collections.sort(entryList, sortByValue);
                LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                for (Map.Entry e : entryList)
                    linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                for (int i = 0; i < al1.size(); i++)
                    set.dingWeiZhi(al1.get(i).toString(), "", "", "");
                set.dingWeiZhi();
            }
            if (!et_2.getText().toString().equals("")) {
                StringBuffer sb1 = new StringBuffer(et_2.getText().toString());
                HashMap<Integer, String> treeMap = new HashMap<>();
                for (int i = 0; i < sb1.length(); i++)
                    treeMap.put(i, sb1.substring(i, i + 1));
                ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                    @Override
                    public int compare(Map.Entry o1, Map.Entry o2) {
                        return ((String) o1.getValue()).compareTo((String) o2.getValue());
                    }
                };
                Collections.sort(entryList, sortByValue);
                LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                for (Map.Entry e : entryList)
                    linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                for (int i = 0; i < al1.size(); i++)
                    set.dingWeiZhi("", al1.get(i).toString(), "", "");
                set.dingWeiZhi();
            }
            if (!et_3.getText().toString().equals("")) {
                StringBuffer sb1 = new StringBuffer(et_3.getText().toString());
                HashMap<Integer, String> treeMap = new HashMap<>();
                for (int i = 0; i < sb1.length(); i++)
                    treeMap.put(i, sb1.substring(i, i + 1));
                ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                    @Override
                    public int compare(Map.Entry o1, Map.Entry o2) {
                        return ((String) o1.getValue()).compareTo((String) o2.getValue());
                    }
                };
                Collections.sort(entryList, sortByValue);
                LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                for (Map.Entry e : entryList)
                    linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                for (int i = 0; i < al1.size(); i++)
                    set.dingWeiZhi("", "", al1.get(i).toString(), "");
                set.dingWeiZhi();
            }
            if (!et_4.getText().toString().equals("")) {
                StringBuffer sb1 = new StringBuffer(et_4.getText().toString());
                HashMap<Integer, String> treeMap = new HashMap<>();
                for (int i = 0; i < sb1.length(); i++)
                    treeMap.put(i, sb1.substring(i, i + 1));
                ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                    @Override
                    public int compare(Map.Entry o1, Map.Entry o2) {
                        return ((String) o1.getValue()).compareTo((String) o2.getValue());
                    }
                };
                Collections.sort(entryList, sortByValue);
                LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                for (Map.Entry e : entryList)
                    linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                for (int i = 0; i < al1.size(); i++)
                    set.dingWeiZhi("", "", "", al1.get(i).toString());
                set.dingWeiZhi();
            }
        }
        if (rb_3.isChecked()) {//配除
            set.peiShu_chu(0);
            switch (gameStyle) {
                case 1:
                    if (!et_5.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_5.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu(al1.get(i).toString(), "", "", "");
                        set.peiShu();
                    }
                    if (!et_6.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_6.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    break;
                case 2:
                    if (!et_7.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_7.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu(al1.get(i).toString(), "", "", "");
                        set.peiShu();
                    }
                    if (!et_8.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_8.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    if (!et_9.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_9.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    break;
                case 3:
                    if (!et_10.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_10.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu(al1.get(i).toString(), "", "", "");
                        set.peiShu();
                    }
                    if (!et_11.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_11.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    if (!et_12.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_12.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    if (!et_13.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_13.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    break;
            }
            set.peiShu_chu(1);
        }
        if (rb_4.isChecked()) {//配取
            switch (gameStyle) {
                case 1:
                    if (!et_5.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_5.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu(al1.get(i).toString(), "", "", "");
                        set.peiShu();
                    }
                    if (!et_6.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_6.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    break;
                case 2:
                    if (!et_7.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_7.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu(al1.get(i).toString(), "", "", "");
                        set.peiShu();
                    }
                    if (!et_8.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_8.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    if (!et_9.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_9.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    break;
                case 3:
                    if (!et_10.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_10.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu(al1.get(i).toString(), "", "", "");
                        set.peiShu();
                    }
                    if (!et_11.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_11.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    if (!et_12.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_12.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    if (!et_13.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_13.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    break;
            }
        }
        if (rb_5.isChecked()) {//配除
            set.peiShu_chu(0);
            switch (gameStyle) {
                case 4:
                    if (!et_5.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_5.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu(al1.get(i).toString(), "", "", "");
                        set.peiShu();
                    }
                    if (!et_6.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_6.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    break;
                case 5:
                    if (!et_7.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_7.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu(al1.get(i).toString(), "", "", "");
                        set.peiShu();
                    }
                    if (!et_8.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_8.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    if (!et_9.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_9.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    break;
                case 6:
                    if (!et_10.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_10.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu(al1.get(i).toString(), "", "", "");
                        set.peiShu();
                    }
                    if (!et_11.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_11.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    if (!et_12.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_12.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    if (!et_13.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_13.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    break;
            }
            set.peiShu_chu(1);
        }
        if (rb_6.isChecked()) {//配取
            switch (gameStyle) {
                case 4:
                    if (!et_5.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_5.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu(al1.get(i).toString(), "", "", "");
                        set.peiShu();
                    }
                    if (!et_6.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_6.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    break;
                case 5:
                    if (!et_7.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_7.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu(al1.get(i).toString(), "", "", "");
                        set.peiShu();
                    }
                    if (!et_8.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_8.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    if (!et_9.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_9.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    break;
                case 6:
                    if (!et_10.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_10.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu(al1.get(i).toString(), "", "", "");
                        set.peiShu();
                    }
                    if (!et_11.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_11.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    if (!et_12.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_12.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    if (!et_13.getText().toString().equals("")) {
                        StringBuffer sb1 = new StringBuffer(et_13.getText().toString());
                        HashMap<Integer, String> treeMap = new HashMap<>();
                        for (int i = 0; i < sb1.length(); i++)
                            treeMap.put(i, sb1.substring(i, i + 1));
                        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                            @Override
                            public int compare(Map.Entry o1, Map.Entry o2) {
                                return ((String) o1.getValue()).compareTo((String) o2.getValue());
                            }
                        };
                        Collections.sort(entryList, sortByValue);
                        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                        for (Map.Entry e : entryList)
                            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                        ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                        for (int i = 0; i < al1.size(); i++)
                            set.peiShu("", al1.get(i).toString(), "", "");
                        set.peiShu();
                    }
                    break;
            }
        }
        if (rb_7.isChecked()) {//合分除
            set.heFen_chu(0);
            int cb1 = 0, cb2 = 0, cb3 = 0, cb4 = 0, cb5 = 0, cb6 = 0, cb7 = 0, cb8 = 0, cb9 = 0, cb10 = 0,
                    cb11 = 0, cb12 = 0, cb13 = 0, cb14 = 0, cb15 = 0, cb16 = 0;
            if (cb_1.isChecked()) cb1 = 1;
            if (cb_2.isChecked()) cb2 = 1;
            if (cb_3.isChecked()) cb3 = 1;
            if (cb_4.isChecked()) cb4 = 1;
            if (cb_5.isChecked()) cb5 = 1;
            if (cb_6.isChecked()) cb6 = 1;
            if (cb_7.isChecked()) cb7 = 1;
            if (cb_8.isChecked()) cb8 = 1;
            if (cb_9.isChecked()) cb9 = 1;
            if (cb_10.isChecked()) cb10 = 1;
            if (cb_11.isChecked()) cb11 = 1;
            if (cb_12.isChecked()) cb12 = 1;
            if (cb_13.isChecked()) cb13 = 1;
            if (cb_14.isChecked()) cb14 = 1;
            if (cb_15.isChecked()) cb15 = 1;
            if (cb_16.isChecked()) cb16 = 1;
            if (cb1 == 1 || cb2 == 1 || cb3 == 1 || cb4 == 1)
                set.heFen1(et_14.getText().toString(), cb1, cb2, cb3, cb4);
            if (cb5 == 1 || cb6 == 1 || cb7 == 1 || cb8 == 1)
                set.heFen2(et_15.getText().toString(), cb5, cb6, cb7, cb8);
            if (cb9 == 1 || cb10 == 1 || cb11 == 1 || cb12 == 1)
                set.heFen3(et_16.getText().toString(), cb9, cb10, cb11, cb12);
            if (cb13 == 1 || cb14 == 1 || cb15 == 1 || cb16 == 1)
                set.heFen4(et_17.getText().toString(), cb13, cb14, cb15, cb16);
            set.heFen_chu(1);
        }
        if (rb_8.isChecked()) {//合分取
            int cb1 = 0, cb2 = 0, cb3 = 0, cb4 = 0, cb5 = 0, cb6 = 0, cb7 = 0, cb8 = 0, cb9 = 0, cb10 = 0,
                    cb11 = 0, cb12 = 0, cb13 = 0, cb14 = 0, cb15 = 0, cb16 = 0;
            if (cb_1.isChecked()) cb1 = 1;
            if (cb_2.isChecked()) cb2 = 1;
            if (cb_3.isChecked()) cb3 = 1;
            if (cb_4.isChecked()) cb4 = 1;
            if (cb_5.isChecked()) cb5 = 1;
            if (cb_6.isChecked()) cb6 = 1;
            if (cb_7.isChecked()) cb7 = 1;
            if (cb_8.isChecked()) cb8 = 1;
            if (cb_9.isChecked()) cb9 = 1;
            if (cb_10.isChecked()) cb10 = 1;
            if (cb_11.isChecked()) cb11 = 1;
            if (cb_12.isChecked()) cb12 = 1;
            if (cb_13.isChecked()) cb13 = 1;
            if (cb_14.isChecked()) cb14 = 1;
            if (cb_15.isChecked()) cb15 = 1;
            if (cb_16.isChecked()) cb16 = 1;
            if (cb1 == 1 || cb2 == 1 || cb3 == 1 || cb4 == 1)
                set.heFen1(et_14.getText().toString(), cb1, cb2, cb3, cb4);
            if (cb5 == 1 || cb6 == 1 || cb7 == 1 || cb8 == 1)
                set.heFen2(et_15.getText().toString(), cb5, cb6, cb7, cb8);
            if (cb9 == 1 || cb10 == 1 || cb11 == 1 || cb12 == 1)
                set.heFen3(et_16.getText().toString(), cb9, cb10, cb11, cb12);
            if (cb13 == 1 || cb14 == 1 || cb15 == 1 || cb16 == 1)
                set.heFen4(et_17.getText().toString(), cb13, cb14, cb15, cb16);
        }
        if (rb_9.isChecked()) {//不定位合分兩
            if (!et_18.getText().toString().equals("")) {
                StringBuffer sb1 = new StringBuffer(et_18.getText().toString());
                HashMap<Integer, String> treeMap = new HashMap<>();
                for (int i = 0; i < sb1.length(); i++)
                    treeMap.put(i, sb1.substring(i, i + 1));
                ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                    @Override
                    public int compare(Map.Entry o1, Map.Entry o2) {
                        return ((String) o1.getValue()).compareTo((String) o2.getValue());
                    }
                };
                Collections.sort(entryList, sortByValue);
                LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                for (Map.Entry e : entryList)
                    linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                for (int i = 0; i < al1.size(); i++)
                    set.buDingWeiHeFen2(al1.get(i).toString());
                set.buDingWeiHeFen(0);
            }
        }
        if (rb_10.isChecked()) {//不定位合分兩
            if (!et_19.getText().toString().equals("")) {
                StringBuffer sb1 = new StringBuffer(et_19.getText().toString());
                HashMap<Integer, String> treeMap = new HashMap<>();
                for (int i = 0; i < sb1.length(); i++)
                    treeMap.put(i, sb1.substring(i, i + 1));
                ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                    @Override
                    public int compare(Map.Entry o1, Map.Entry o2) {
                        return ((String) o1.getValue()).compareTo((String) o2.getValue());
                    }
                };
                Collections.sort(entryList, sortByValue);
                LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                for (Map.Entry e : entryList)
                    linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                for (int i = 0; i < al1.size(); i++)
                    set.buDingWeiHeFen2(al1.get(i).toString());
                set.buDingWeiHeFen(0);
            }
        }
        if (rb_11.isChecked()) {//不定位合分三
            if (!et_19.getText().toString().equals("")) {
                StringBuffer sb1 = new StringBuffer(et_19.getText().toString());
                HashMap<Integer, String> treeMap = new HashMap<>();
                for (int i = 0; i < sb1.length(); i++)
                    treeMap.put(i, sb1.substring(i, i + 1));
                ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
                Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
                    @Override
                    public int compare(Map.Entry o1, Map.Entry o2) {
                        return ((String) o1.getValue()).compareTo((String) o2.getValue());
                    }
                };
                Collections.sort(entryList, sortByValue);
                LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
                for (Map.Entry e : entryList)
                    linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
                ArrayList al1 = new ArrayList<>(linkedHashMap.values());
                for (int i = 0; i < al1.size(); i++)
                    set.buDingWeiHeFen3(al1.get(i).toString());
                set.buDingWeiHeFen(0);
            }
        }

        //值範圍
        int x = 0, y = 0;
        if (!et_20.getText().toString().equals("") && !et_21.getText().toString().equals("")) {
            x = Integer.parseInt(et_20.getText().toString());
            y = Integer.parseInt(et_21.getText().toString());
            for (int i = x; i <= y; i++) {
                set.buDingWeiHeFen4(String.valueOf(i));
            }
            set.buDingWeiHeFen(1);
        }
        if (!et_20.getText().toString().equals("") && et_21.getText().toString().equals(""))
            Toast("值范围输入错误");
        if (et_20.getText().toString().equals("") && !et_21.getText().toString().equals(""))
            Toast("值范围输入错误");

        //排除
        if (!et_24.getText().toString().equals("")) set.paiChu(et_24.getText().toString());

        //承號位置
        if (cb_17.isChecked() || cb_18.isChecked() || cb_19.isChecked() || cb_20.isChecked())
            set.chengHaoWeiZhi(cb_17.isChecked(), cb_18.isChecked(), cb_19.isChecked(), cb_20.isChecked());

        if (rb_12.isChecked()) {//二字定含除
            if (!et_25.getText().toString().equals("")) set.han(false, et_25.getText().toString());
        }
        if (rb_13.isChecked()) {//二字定含取
            if (!et_25.getText().toString().equals("")) set.han(true, et_25.getText().toString());
        }
        if (rb_14.isChecked()) {//三字定含除
            if (!et_27.getText().toString().equals("")) set.han(false, et_27.getText().toString());
        }
        if (rb_15.isChecked()) {//三字定含取
            if (!et_27.getText().toString().equals("")) set.han(true, et_27.getText().toString());
        }
        if (rb_16.isChecked()) {//四字定含除
            if (!et_29.getText().toString().equals("")) set.han(false, et_29.getText().toString());
        }
        if (rb_17.isChecked()) {//四字定含取
            if (!et_29.getText().toString().equals("")) set.han(true, et_29.getText().toString());
        }
        if (rb_18.isChecked()) {//雙重除
            set.shuangChong(false);
        }
        if (rb_19.isChecked()) {//雙重取
            set.shuangChong(true);
        }
        if (rb_20.isChecked()) {//雙雙重除
            set.shuangshuangChong(false);
        }
        if (rb_21.isChecked()) {//雙雙重取
            set.shuangshuangChong(true);
        }
        if (rb_22.isChecked()) {//三重除
            set.sanChong(false);
        }
        if (rb_23.isChecked()) {//三重取
            set.sanChong(true);
        }
        if (rb_24.isChecked()) {//四重除
            set.siChong(false);
        }
        if (rb_25.isChecked()) {//四重取
            set.siChong(true);
        }
        if (rb_26.isChecked()) {//二兄弟除
            set.erXiongDi(false);
        }
        if (rb_27.isChecked()) {//二兄弟取
            set.erXiongDi(true);
        }
        if (rb_28.isChecked()) {//三兄弟除
            set.sanXiongDi(false);
        }
        if (rb_29.isChecked()) {//三兄弟取
            set.sanXiongDi(true);
        }
        if (rb_30.isChecked()) {//四兄弟除
            set.siXiongDi(false);
        }
        if (rb_31.isChecked()) {//四兄弟取
            set.siXiongDi(true);
        }
        if (rb_32.isChecked()) {//對數除
            if (!et_31.getText().toString().equals("") || !et_32.getText().toString().equals("") || !et_33.getText().toString().equals(""))
                set.duiShu(false, et_31.getText().toString(), et_32.getText().toString(), et_33.getText().toString());
        }
        if (rb_33.isChecked()) {//對數取
            if (!et_31.getText().toString().equals("") || !et_32.getText().toString().equals("") || !et_33.getText().toString().equals(""))
                set.duiShu(true, et_31.getText().toString(), et_32.getText().toString(), et_33.getText().toString());
        }
        if (rb_34.isChecked()) {//單除2
            set.dan(false, cb_21.isChecked(), cb_22.isChecked(), false, false);
        }
        if (rb_35.isChecked()) {//單取2
            set.dan(true, cb_21.isChecked(), cb_22.isChecked(), false, false);
        }
        if (rb_36.isChecked()) {//單除3
            set.dan(false, cb_23.isChecked(), cb_24.isChecked(), cb_25.isChecked(), false);
        }
        if (rb_37.isChecked()) {//單取3
            set.dan(true, cb_23.isChecked(), cb_24.isChecked(), cb_25.isChecked(), false);
        }
        if (rb_38.isChecked()) {//單除4
            set.dan(false, cb_26.isChecked(), cb_27.isChecked(), cb_28.isChecked(), cb_29.isChecked());
        }
        if (rb_39.isChecked()) {//單取4
            set.dan(true, cb_26.isChecked(), cb_27.isChecked(), cb_28.isChecked(), cb_29.isChecked());
        }
        if (rb_40.isChecked()) {//雙除2
            set.shuang(false, cb_30.isChecked(), cb_31.isChecked(), false, false);
        }
        if (rb_41.isChecked()) {//雙取2
            set.shuang(true, cb_30.isChecked(), cb_31.isChecked(), false, false);
        }
        if (rb_42.isChecked()) {//雙除3
            set.shuang(false, cb_32.isChecked(), cb_33.isChecked(), cb_34.isChecked(), false);
        }
        if (rb_43.isChecked()) {//雙取3
            set.shuang(true, cb_32.isChecked(), cb_33.isChecked(), cb_34.isChecked(), false);
        }
        if (rb_44.isChecked()) {//雙除4
            set.shuang(false, cb_35.isChecked(), cb_36.isChecked(), cb_37.isChecked(), cb_38.isChecked());
        }
        if (rb_45.isChecked()) {//雙取4
            set.shuang(true, cb_35.isChecked(), cb_36.isChecked(), cb_37.isChecked(), cb_38.isChecked());
        }
        if (rb_46.isChecked()) {//二字現含除
            if (!et_34.getText().toString().equals("")) set.han(false, et_34.getText().toString());
        }
        if (rb_47.isChecked()) {//二字現含取
            if (!et_34.getText().toString().equals("")) set.han(true, et_34.getText().toString());
        }
        if (rb_48.isChecked()) {//三字現含除
            if (!et_36.getText().toString().equals("")) set.han(false, et_36.getText().toString());
        }
        if (rb_49.isChecked()) {//三字現含取
            if (!et_36.getText().toString().equals("")) set.han(true, et_36.getText().toString());
        }
        if (rb_50.isChecked()) {//四字現含除
            if (!et_38.getText().toString().equals("")) set.han(false, et_38.getText().toString());
        }
        if (rb_51.isChecked()) {//四字現含取
            if (!et_38.getText().toString().equals("")) set.han(true, et_38.getText().toString());
        }
    }

    public void selectlogs() {
        String s, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19;
        s1 = (rb_1.isChecked() ? "1" : "0") + "," + (rb_2.isChecked() ? "1" : "0") + "," +
                et_1.getText().toString() + "," + et_2.getText().toString() + "," + et_3.getText().toString() + "," + et_4.getText().toString() + ",";
        s2 = (rb_7.isChecked() ? "1" : "0") + "," + (rb_8.isChecked() ? "1" : "0") + "," +
                (cb_1.isChecked() ? "1" : "0") + "," + (cb_2.isChecked() ? "1" : "0") + "," + (cb_3.isChecked() ? "1" : "0") + "," + (cb_4.isChecked() ? "1" : "0") + "," +
                et_14.getText().toString() + "," +
                (cb_5.isChecked() ? "1" : "0") + "," + (cb_6.isChecked() ? "1" : "0") + "," + (cb_7.isChecked() ? "1" : "0") + "," + (cb_8.isChecked() ? "1" : "0") + "," +
                et_15.getText().toString() + "," +
                (cb_9.isChecked() ? "1" : "0") + "," + (cb_10.isChecked() ? "1" : "0") + "," + (cb_11.isChecked() ? "1" : "0") + "," + (cb_12.isChecked() ? "1" : "0") + "," +
                et_16.getText().toString() + "," +
                (cb_13.isChecked() ? "1" : "0") + "," + (cb_14.isChecked() ? "1" : "0") + "," + (cb_15.isChecked() ? "1" : "0") + "," + (cb_16.isChecked() ? "1" : "0") + "," +
                et_17.getText().toString() + ",";
        s3 = "0,0,,";
        if (rb_9.isChecked()) s3 = "1,0," + et_18.getText().toString() + ",";
        if (rb_10.isChecked() || rb_11.isChecked())
            s3 = (rb_10.isChecked() ? "1" : "0") + "," + (rb_11.isChecked() ? "1" : "0") + "," + et_18.getText().toString() + ",";
        s4 = "";
        if (!et_20.getText().toString().equals("") && !et_21.getText().toString().equals(""))
            s4 = et_20.getText().toString() + "," + et_21.getText().toString() + ",";
        s5 = et_22.getText().toString() + "," + et_23.getText().toString() + "," + et_24.getText().toString() + ",";
        s6 = (cb_17.isChecked() ? "1" : "0") + "," + (cb_18.isChecked() ? "1" : "0") + "," + (cb_19.isChecked() ? "1" : "0") + "," + (cb_20.isChecked() ? "1" : "0") + ",";
        s7 = "0,0,,,";
        if (rb_12.isChecked() || rb_13.isChecked())
            s7 = (rb_12.isChecked() ? "1" : "0") + "," + (rb_13.isChecked() ? "1" : "0") + "," + et_25.getText().toString() + "," + et_26.getText().toString() + ",";
        if (rb_14.isChecked() || rb_15.isChecked())
            s7 = (rb_14.isChecked() ? "1" : "0") + "," + (rb_15.isChecked() ? "1" : "0") + "," + et_27.getText().toString() + "," + et_28.getText().toString() + ",";
        if (rb_16.isChecked() || rb_17.isChecked())
            s7 = (rb_16.isChecked() ? "1" : "0") + "," + (rb_17.isChecked() ? "1" : "0") + "," + et_29.getText().toString() + "," + et_30.getText().toString() + ",";
        if (rb_46.isChecked() || rb_47.isChecked())
            s7 = (rb_46.isChecked() ? "1" : "0") + "," + (rb_47.isChecked() ? "1" : "0") + "," + et_34.getText().toString() + "," + et_35.getText().toString() + ",";
        if (rb_48.isChecked() || rb_49.isChecked())
            s7 = (rb_48.isChecked() ? "1" : "0") + "," + (rb_49.isChecked() ? "1" : "0") + "," + et_36.getText().toString() + "," + et_37.getText().toString() + ",";
        if (rb_50.isChecked() || rb_51.isChecked())
            s7 = (rb_50.isChecked() ? "1" : "0") + "," + (rb_51.isChecked() ? "1" : "0") + "," + et_38.getText().toString() + "," + et_39.getText().toString() + ",";
        s8 = (rb_18.isChecked() ? "1" : "0") + "," + (rb_19.isChecked() ? "1" : "0") + ",";//雙重
        s9 = (rb_20.isChecked() ? "1" : "0") + "," + (rb_21.isChecked() ? "1" : "0") + ",";//雙雙重
        s10 = (rb_22.isChecked() ? "1" : "0") + "," + (rb_23.isChecked() ? "1" : "0") + ",";//三重
        s11 = (rb_24.isChecked() ? "1" : "0") + "," + (rb_25.isChecked() ? "1" : "0") + ",";//四重
        s12 = (rb_26.isChecked() ? "1" : "0") + "," + (rb_27.isChecked() ? "1" : "0") + ",";//二兄弟
        s13 = (rb_28.isChecked() ? "1" : "0") + "," + (rb_29.isChecked() ? "1" : "0") + ",";//三兄弟
        s14 = (rb_30.isChecked() ? "1" : "0") + "," + (rb_31.isChecked() ? "1" : "0") + ",";//四兄弟
        s15 = (rb_32.isChecked() ? "1" : "0") + "," + (rb_33.isChecked() ? "1" : "0") + "," +
                et_31.getText().toString() + "," + et_32.getText().toString() + "," + et_33.getText().toString() + ",";
        s16 = "";//單
        switch (gameStyle) {
            case 1:
                s16 = "0,0,0,0,0,0,";
                if (rb_38.isChecked() || rb_39.isChecked())
                    s16 = (rb_38.isChecked() ? "1" : "0") + "," + (rb_39.isChecked() ? "1" : "0") + "," +
                            (cb_26.isChecked() ? "1" : "0") + "," + (cb_27.isChecked() ? "1" : "0") + "," + (cb_28.isChecked() ? "1" : "0") + "," + (cb_29.isChecked() ? "1" : "0") + ",";
                break;
            case 2:
                s16 = "0,0,0,0,0,0,";
                if (rb_38.isChecked() || rb_39.isChecked())
                    s16 = (rb_38.isChecked() ? "1" : "0") + "," + (rb_39.isChecked() ? "1" : "0") + "," +
                            (cb_26.isChecked() ? "1" : "0") + "," + (cb_27.isChecked() ? "1" : "0") + "," + (cb_28.isChecked() ? "1" : "0") + "," + (cb_29.isChecked() ? "1" : "0") + ",";
                break;
            case 3:
                s16 = "0,0,0,0,0,0,";
                if (rb_38.isChecked() || rb_39.isChecked())
                    s16 = (rb_38.isChecked() ? "1" : "0") + "," + (rb_39.isChecked() ? "1" : "0") + "," +
                            (cb_26.isChecked() ? "1" : "0") + "," + (cb_27.isChecked() ? "1" : "0") + "," + (cb_28.isChecked() ? "1" : "0") + "," + (cb_29.isChecked() ? "1" : "0") + ",";
                break;
            case 4:
                s16 = "0,0,0,0,";
                if (rb_34.isChecked() || rb_35.isChecked())
                    s16 = (rb_34.isChecked() ? "1" : "0") + "," + (rb_35.isChecked() ? "1" : "0") + "," +
                            (cb_21.isChecked() ? "1" : "0") + "," + (cb_22.isChecked() ? "1" : "0") + ",";
                break;
            case 5:
                s16 = "0,0,0,0,0,";
                if (rb_36.isChecked() || rb_37.isChecked())
                    s16 = (rb_36.isChecked() ? "1" : "0") + "," + (rb_37.isChecked() ? "1" : "0") + "," +
                            (cb_23.isChecked() ? "1" : "0") + "," + (cb_24.isChecked() ? "1" : "0") + "," + (cb_25.isChecked() ? "1" : "0") + ",";
                break;
            case 6:
                s16 = "0,0,0,0,0,0,";
                if (rb_38.isChecked() || rb_39.isChecked())
                    s16 = (rb_38.isChecked() ? "1" : "0") + "," + (rb_39.isChecked() ? "1" : "0") + "," +
                            (cb_26.isChecked() ? "1" : "0") + "," + (cb_27.isChecked() ? "1" : "0") + "," + (cb_28.isChecked() ? "1" : "0") + "," + (cb_29.isChecked() ? "1" : "0") + ",";
                break;
        }
        s17 = "";//雙
        switch (gameStyle) {
            case 1:
                s17 = "0,0,0,0,0,0,";
                if (rb_44.isChecked() || rb_45.isChecked())
                    s17 = (rb_44.isChecked() ? "1" : "0") + "," + (rb_45.isChecked() ? "1" : "0") + "," +
                            (cb_35.isChecked() ? "1" : "0") + "," + (cb_36.isChecked() ? "1" : "0") + "," + (cb_37.isChecked() ? "1" : "0") + "," + (cb_38.isChecked() ? "1" : "0") + ",";
                break;
            case 2:
                s17 = "0,0,0,0,0,0,";
                if (rb_44.isChecked() || rb_45.isChecked())
                    s17 = (rb_44.isChecked() ? "1" : "0") + "," + (rb_45.isChecked() ? "1" : "0") + "," +
                            (cb_35.isChecked() ? "1" : "0") + "," + (cb_36.isChecked() ? "1" : "0") + "," + (cb_37.isChecked() ? "1" : "0") + "," + (cb_38.isChecked() ? "1" : "0") + ",";
                break;
            case 3:
                s17 = "0,0,0,0,0,0,";
                if (rb_44.isChecked() || rb_45.isChecked())
                    s17 = (rb_44.isChecked() ? "1" : "0") + "," + (rb_45.isChecked() ? "1" : "0") + "," +
                            (cb_35.isChecked() ? "1" : "0") + "," + (cb_36.isChecked() ? "1" : "0") + "," + (cb_37.isChecked() ? "1" : "0") + "," + (cb_38.isChecked() ? "1" : "0") + ",";
                break;
            case 4:
                s17 = "0,0,0,0,";
                if (rb_40.isChecked() || rb_41.isChecked())
                    s17 = (rb_40.isChecked() ? "1" : "0") + "," + (rb_41.isChecked() ? "1" : "0") + "," +
                            (cb_30.isChecked() ? "1" : "0") + "," + (cb_31.isChecked() ? "1" : "0") + ",";
                break;
            case 5:
                s17 = "0,0,0,0,0,";
                if (rb_42.isChecked() || rb_43.isChecked())
                    s17 = (rb_42.isChecked() ? "1" : "0") + "," + (rb_43.isChecked() ? "1" : "0") + "," +
                            (cb_32.isChecked() ? "1" : "0") + "," + (cb_33.isChecked() ? "1" : "0") + "," + (cb_34.isChecked() ? "1" : "0") + ",";
                break;
            case 6:
                s17 = "0,0,0,0,0,0,";
                if (rb_44.isChecked() || rb_45.isChecked())
                    s17 = (rb_44.isChecked() ? "1" : "0") + "," + (rb_45.isChecked() ? "1" : "0") + "," +
                            (cb_35.isChecked() ? "1" : "0") + "," + (cb_36.isChecked() ? "1" : "0") + "," + (cb_37.isChecked() ? "1" : "0") + "," + (cb_38.isChecked() ? "1" : "0") + ",";
                break;
        }
        s18 = "";//配數
        switch (gameStyle) {
            case 1:
                if (rb_3.isChecked() || rb_4.isChecked())
                    s18 = (rb_3.isChecked() ? "1" : "0") + "," + (rb_4.isChecked() ? "1" : "0") + "," +
                            et_5.getText().toString() + "," + et_6.getText().toString() + "," + "" + "," + "" + ",0,0,0,0,";
                break;
            case 2:
                if (rb_3.isChecked() || rb_4.isChecked())
                    s18 = (rb_3.isChecked() ? "1" : "0") + "," + (rb_4.isChecked() ? "1" : "0") + "," +
                            et_7.getText().toString() + "," + et_8.getText().toString() + "," + et_9.getText().toString() + "," + "" + ",0,0,0,0,";
                break;
            case 3:
                if (rb_3.isChecked() || rb_4.isChecked())
                    s18 = (rb_3.isChecked() ? "1" : "0") + "," + (rb_4.isChecked() ? "1" : "0") + "," +
                            et_10.getText().toString() + "," + et_11.getText().toString() + "," + et_12.getText().toString() + "," + et_13.getText().toString() + ",0,0,0,0,";
                break;
            case 4:
                if (rb_5.isChecked() || rb_6.isChecked())
                    s18 = (rb_3.isChecked() ? "1" : "0") + "," + (rb_4.isChecked() ? "1" : "0") + "," +
                            et_5.getText().toString() + "," + et_6.getText().toString() + "," + "" + "," + "" + ",0,0,0,0,";
                break;
            case 5:
                if (rb_5.isChecked() || rb_6.isChecked())
                    s18 = (rb_3.isChecked() ? "1" : "0") + "," + (rb_4.isChecked() ? "1" : "0") + "," +
                            et_7.getText().toString() + "," + et_8.getText().toString() + "," + et_9.getText().toString() + "," + "" + ",0,0,0,0,";
                break;
            case 6:
                if (rb_5.isChecked() || rb_6.isChecked())
                    s18 = (rb_3.isChecked() ? "1" : "0") + "," + (rb_4.isChecked() ? "1" : "0") + "," +
                            et_10.getText().toString() + "," + et_11.getText().toString() + "," + et_12.getText().toString() + "," + et_13.getText().toString() + ",0,0,0,0,";
                break;
        }
        s19 = "";
        selectlogs = s1 + "|" + s2 + "|" + s3 + "|" + s4 + "|" + s5 + "|" + s6 + "|" + s7 + "|" + s8 + "|" + s9 + "|" + s10 +
                "|" + s11 + "|" + s12 + "|" + s13 + "|" + s14 + "|" + s15 + "|" + s16 + "|" + s17 + "|" + s18 + "|" + s19;
    }

    public void rbClick() {
        rb_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb_1.isChecked()) {
                    resetRg1And2();
                    ll_2.setVisibility(View.VISIBLE);
                    ll_3.setVisibility(View.VISIBLE);
                }
            }
        });
        rb_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb_2.isChecked()) {
                    resetRg1And2();
                    ll_2.setVisibility(View.VISIBLE);
                    ll_3.setVisibility(View.VISIBLE);
                }
            }
        });
        rb_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb_3.isChecked()) {
                    resetRg1And2();
                    switch (gameStyle) {
                        case 1:
                            ll_4.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            ll_5.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            ll_6.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            }
        });
        rb_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb_4.isChecked()) {
                    resetRg1And2();
                    switch (gameStyle) {
                        case 1:
                            ll_4.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            ll_5.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            ll_6.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            }
        });
        rb_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb_5.isChecked()) {
                    resetRg1And2();
                    switch (gameStyle) {
                        case 4:
                            ll_4.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            ll_5.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            ll_6.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            }
        });
        rb_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb_6.isChecked()) {
                    resetRg1And2();
                    switch (gameStyle) {
                        case 4:
                            ll_4.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            ll_5.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            ll_6.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            }
        });
        rb_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb_7.isChecked()) {
                    ll_7.setVisibility(View.VISIBLE);
                    ll_8.setVisibility(View.VISIBLE);
                }
            }
        });
        rb_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb_8.isChecked()) {
                    ll_7.setVisibility(View.VISIBLE);
                    ll_8.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void btn_erDing(View view) {
        gameStyle = 1;
        tv_gameStyle.setText("---二字定---");
        resetAll();
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
        rg_23.setVisibility(View.GONE);
        rg_24.setVisibility(View.GONE);
        rg_25.setVisibility(View.GONE);
    }

    public void btn_sanDing(View view) {
        gameStyle = 2;
        tv_gameStyle.setText("---三字定---");
        resetAll();
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
        rg_23.setVisibility(View.GONE);
        rg_24.setVisibility(View.GONE);
        rg_25.setVisibility(View.GONE);
    }

    public void btn_siDing(View view) {
        gameStyle = 3;
        tv_gameStyle.setText("---四字定---");
        resetAll();
        ll_1.setVisibility(View.VISIBLE);
        ll_9.setVisibility(View.VISIBLE);
        ll_10.setVisibility(View.VISIBLE);
        ll_11.setVisibility(View.GONE);
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
        rg_23.setVisibility(View.GONE);
        rg_24.setVisibility(View.GONE);
        rg_25.setVisibility(View.GONE);
    }

    public void btn_erXian(View view) {
        gameStyle = 4;
        tv_gameStyle.setText("---二字現---");
        resetAll();
        ll_1.setVisibility(View.VISIBLE);
        ll_9.setVisibility(View.GONE);
        ll_10.setVisibility(View.GONE);
        ll_11.setVisibility(View.GONE);
        rg_1.setVisibility(View.GONE);
        rg_2.setVisibility(View.VISIBLE);
        rg_3.setVisibility(View.GONE);
        rg_4.setVisibility(View.VISIBLE);
        rg_5.setVisibility(View.GONE);
        rg_6.setVisibility(View.GONE);
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
        rg_23.setVisibility(View.VISIBLE);
        rg_24.setVisibility(View.GONE);
        rg_25.setVisibility(View.GONE);
    }

    public void btn_sanXian(View view) {
        gameStyle = 5;
        tv_gameStyle.setText("---三字現---");
        resetAll();
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
        rg_23.setVisibility(View.GONE);
        rg_24.setVisibility(View.VISIBLE);
        rg_25.setVisibility(View.GONE);
    }

    public void btn_siXian(View view) {
        gameStyle = 6;
        tv_gameStyle.setText("---四字現---");
        resetAll();
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
        rg_8.setVisibility(View.GONE);
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
        rg_23.setVisibility(View.GONE);
        rg_24.setVisibility(View.GONE);
        rg_25.setVisibility(View.VISIBLE);
    }

    public void resetRg1And2() {
        rg_1.clearCheck();
        rg_2.clearCheck();
        ll_2.setVisibility(View.GONE);
        ll_3.setVisibility(View.GONE);
        ll_4.setVisibility(View.GONE);
        ll_5.setVisibility(View.GONE);
        ll_6.setVisibility(View.GONE);
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
    }

    public void resetAll() {
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
        et_34.setText("");
        et_35.setText("");
        et_36.setText("");
        et_37.setText("");
        et_38.setText("");
        et_39.setText("");
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
        rg_23.clearCheck();
        rg_24.clearCheck();
        rg_25.clearCheck();
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