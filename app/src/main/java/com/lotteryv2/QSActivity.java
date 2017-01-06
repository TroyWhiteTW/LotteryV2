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
import android.widget.TextView;
import android.widget.Toast;

public class QSActivity extends AppCompatActivity {
    private Button btn_List, btn_QG, btn_QS, btn_Member, btn_History;
    private Button btn_erDing, btn_sanDing, btn_siDing, btn_erXian, btn_sanXian, btn_siXian, btn_qsr, btn_reset;
    private pDialog pDialog;
    private String cookie, app_net, webside;
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
        btn_erDing = (Button) findViewById(R.id.btn_erDing);
        btn_sanDing = (Button) findViewById(R.id.btn_sanDing);
        btn_siDing = (Button) findViewById(R.id.btn_siDing);
        btn_erXian = (Button) findViewById(R.id.btn_erXian);
        btn_sanXian = (Button) findViewById(R.id.btn_sanXian);
        btn_siXian = (Button) findViewById(R.id.btn_siXian);
        btn_qsr = (Button) findViewById(R.id.btn_qsr);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        tv_gameStyle = (TextView) findViewById(R.id.tv_gameStyle);
        tv_gameOpenFalse = (TextView) findViewById(R.id.tv_gameOpenFalse);
        tv_gameOpenTrue = (TextView) findViewById(R.id.tv_gameOpenTrue);

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
