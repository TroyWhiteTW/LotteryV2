package com.lotteryv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_List, btn_QG, btn_QS, btn_Member, btn_History;
    private String cookie, app_net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        Log(cookie);

        initial();
        getDataThread();
    }

    public void initial() {
        app_net = "http://" + getResources().getString(R.string.app_net) + "/mobile/wap_ajax.php?action=";
        btn_List = (Button) findViewById(R.id.btn_mainList);
        btn_QG = (Button) findViewById(R.id.btn_mainQG);
        btn_QS = (Button) findViewById(R.id.btn_mainQS);
        btn_Member = (Button) findViewById(R.id.btn_mainMember);
        btn_History = (Button) findViewById(R.id.btn_mainHistory);

        btnClick();
    }

    public void btnClick() {
        btn_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, ListActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_QG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, QGActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_QS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, QSActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_Member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, MemberActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, HistoryActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
    }

    public void getDataThread() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                getData();
                Looper.loop();
            }
        }.start();
    }

    public void getData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw(app_net + "app_head_data");
            mu.sendCookie(cookie);
            int i = mu.getResponseCode();
            Log("ResponseCod: " + i);
            List<String> ret = mu.getHtml();
            for (String line : ret) {
                Log(line);
            }
//            String a = mu.getJSONObjectData().getString("list");
//            Log.i("troy", a);
//            String rec = new JSONArray(a).getJSONObject(0).getString("ticket");
//            Log.i("troy", rec);
        } catch (Exception e) {
            Toast("无法与伺服器取得连线");
            Log(e.toString());
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
                MainActivity.this.finish();//關閉activity
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
