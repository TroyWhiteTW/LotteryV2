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

public class QSRActivity extends AppCompatActivity {
    private Button btn_sendGameSet;
    private EditText et_perMoney;
    private int rcedits, rcedits_use;
    private pDialog pDialog;
    private String cookie, app_net, webside;
    private String size, set;
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
        set = it.getStringExtra("set");

        initial();
        connectThread(0);
    }

    public void initial() {
        handler = new UIHandler();
        pDialog = new pDialog(this);

        app_net = "http://" + getResources().getString(R.string.app_net) + "/mobile/wap_ajax.php?action=";
        btn_sendGameSet = (Button) findViewById(R.id.btn_sendGameSet);
        et_perMoney = (EditText) findViewById(R.id.et_perMoney);
        tv_qselectres = (TextView) findViewById(R.id.tv_qselectres);
        tv_howMany = (TextView) findViewById(R.id.tv_howMany);
        tv_totalMoney = (TextView) findViewById(R.id.tv_totalMoney);

        tv_howMany.setText("笔数：" + size);
        tv_qselectres.setText(set);

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

        } catch (Exception e) {

        }
        handler.sendEmptyMessage(1);
    }

    public void sendData() {
        try {

        } catch (Exception e) {

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
