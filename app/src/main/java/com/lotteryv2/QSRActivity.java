package com.lotteryv2;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QSRActivity extends AppCompatActivity {
    private ArrayList<String> set;
    private Button btn_sendGameSet;
    private EditText et_perMoney;
    private int rcedits, rcedits_use;
    private pDialog pDialog;
    private String cookie, app_net, webside;
    private String size;
    private StringBuffer sb, sb2;
    private TextView tv_qselectres, tv_howMany, tv_totalMoney;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qsr);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        webside = it.getStringExtra("webside");
        size = it.getStringExtra("size");
        set = it.getStringArrayListExtra("set");

        initial();
        connectThread(0);
    }

    public void initial() {
        handler = new UIHandler();
        pDialog = new pDialog(this);
        sb = new StringBuffer();
        sb2 = new StringBuffer();

        app_net = "http://" + getResources().getString(R.string.app_net) + "/mobile/wap_ajax.php?action=";
        btn_sendGameSet = (Button) findViewById(R.id.btn_sendGameSet);
        et_perMoney = (EditText) findViewById(R.id.et_perMoney);
        tv_qselectres = (TextView) findViewById(R.id.tv_qselectres);
        tv_howMany = (TextView) findViewById(R.id.tv_howMany);
        tv_totalMoney = (TextView) findViewById(R.id.tv_totalMoney);

        tv_howMany.setText("笔数：" + size);
        for (String s : set) {
            sb.append(s);
            sb.append("\n");
            sb2.append(s);
            sb2.append(",");
        }
        tv_qselectres.setText(sb.toString());

        btnClick();
    }

    public void btnClick() {
        btn_sendGameSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_perMoney.getText().toString().equals("")) {
                    if (Integer.parseInt(et_perMoney.getText().toString()) * Integer.parseInt(size) > rcedits - rcedits_use) {
                        Toast("余额不足");
                    } else {
                        connectThread(1);
                    }
                } else {
                    Toast("未输入金额");
                }
            }
        });
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
                        sendData();
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
            rcedits = jo.getInt("rcedits");
            Log("rcedits= " + rcedits);
            rcedits_use = jo.getInt("rcedits_use");
            Log("rcedits_use= " + rcedits_use);
        } catch (Exception e) {
            Toast("无法与伺服器取得连线");
            Log(e.toString());
        }
        handler.sendEmptyMessage(1);
    }

    public void sendData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw(app_net + "app_soonselect");
            mu.sendCookie(cookie);
            mu.postKeyValue("post_money", et_perMoney.getText().toString());
            mu.postKeyValue("post_number_money", sb2.toString());
//            mu.postKeyValue("selectlogsclassid", String.valueOf(gameStyle));
//            mu.postKeyValue("selectlogs", selectlogs);
            List<String> ret = mu.getHtml();
            for (String line : ret) Log(line);
            Toast("下注完成");
            finish();
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

                    break;
                case 1:
                    if (pDialog.isShowing()) pDialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    }

    public void Toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void Log(String s) {
        Log.i("troy", s);
    }
}
