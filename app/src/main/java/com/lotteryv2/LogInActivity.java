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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class LogInActivity extends AppCompatActivity {
    private Button btn_agree, btn_login;
    private CheckBox cb_agree;
    private EditText et_act, et_pw;
    private String cookie, app_net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        initial();
    }

    public void initial() {
        app_net = "http://" + getResources().getString(R.string.app_net) + "/ajax_login.php?action=";
        et_act = (EditText) findViewById(R.id.et_act);
        et_pw = (EditText) findViewById(R.id.et_pw);
        cb_agree = (CheckBox) findViewById(R.id.cb_agree);
        btn_agree = (Button) findViewById(R.id.btn_agree);
        btn_login = (Button) findViewById(R.id.btn_login);

        btnClick();
    }

    public void btnClick() {
        btn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this, AgreementActivity.class));
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb_agree.isChecked()) {
                    loginThread();
                } else {
                    Toast("请先同意会员协议后方可登入");
                }
            }
        });
    }

    public void loginThread() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                login();
                Looper.loop();
            }
        }.start();
    }

    public void login() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw(app_net + "LogApp");
            mu.postKeyValue("username", String.valueOf(et_act.getText()));
            mu.postKeyValue("password", String.valueOf(et_pw.getText()));
//            List<String> html = mu.getHtml();
//            for (String line : html) {
//                Log.i("troy", line);
//            }
            String serverID = mu.getCookie().split("; ")[0];
            Log(serverID);
            JSONObject jo = mu.getJSONObjectData();
            Log(jo.getString("status"));
            if (jo.getInt("status") == 200) {
                Log(jo.getString("PHPSESSID"));
                cookie = "PHPSESSID=" + jo.getString("PHPSESSID") + "; " + serverID + "; path=/";
                Toast("成功登录");
                Intent it = new Intent(LogInActivity.this, MainActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            } else {
                Toast("账号和密码不匹配，请重新登录。");
            }
        } catch (IOException e) {
            Toast("无法与伺服器取得连线");
            Log("IOException：" + e.toString());
        } catch (JSONException e) {
            Log("JSONException：" + e.toString());
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
                LogInActivity.this.finish();//關閉activity
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
