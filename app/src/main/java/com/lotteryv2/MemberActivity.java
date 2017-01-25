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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemberActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private ArrayList<String> list;
    private Button btn_List, btn_QG, btn_QS, btn_Member, btn_History;
    private Button btn_commit;
    private int odd_sw;
    private int left_show;
    private pDialog pDialog;
    private RadioButton rb_ac, rb_tr;
    private String enter_btn;
    private String cookie, app_net, webside;
    private String[] sa;
    private Spinner sp_1;
    private TextView tv_username, tv_rcedits, tv_rcedits_use, tv_rcedits_canUse;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        webside = it.getStringExtra("webside");

        initial();
        connectThread(0);
    }

    public void initial() {
        handler = new UIHandler();
        list = new ArrayList();
        pDialog = new pDialog(this);

        app_net = "http://" + webside + "/mobile/wap_ajax.php?action=";
        btn_List = (Button) findViewById(R.id.btn_memList);
        btn_QG = (Button) findViewById(R.id.btn_memQG);
        btn_QS = (Button) findViewById(R.id.btn_memQS);
        btn_Member = (Button) findViewById(R.id.btn_memMember);
        btn_History = (Button) findViewById(R.id.btn_memHistory);
        btn_commit = (Button) findViewById(R.id.btn_commit);
        rb_ac = (RadioButton) findViewById(R.id.rb_ac);
        rb_tr = (RadioButton) findViewById(R.id.rb_tr);
        sp_1 = (Spinner) findViewById(R.id.sp_1);
        tv_rcedits = (TextView) findViewById(R.id.tv_rcedits);
        tv_rcedits_use = (TextView) findViewById(R.id.tv_rcedits_use);
        tv_rcedits_canUse = (TextView) findViewById(R.id.tv_rcedits_canUse);
        tv_username = (TextView) findViewById(R.id.tv_username);

        btnClick();
    }

    public void btnClick() {
        btn_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, ListActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("webside", webside);
                startActivity(it);
                finish();
            }
        });
        btn_QG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, QGActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("webside", webside);
                startActivity(it);
                finish();
            }
        });
        btn_QS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, QSActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("webside", webside);
                startActivity(it);
                finish();
            }
        });
        btn_Member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(MemberActivity.this, MemberActivity.class);
//                it.putExtra("cookie", cookie);
//                startActivity(it);
//                finish();
            }
        });
        btn_History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, HistoryActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("webside", webside);
                startActivity(it);
                finish();
            }
        });
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectThread(1);
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
            MultipartUtility_tw mu = new MultipartUtility_tw(app_net + "app_get_mem_data");
            mu.sendCookie(cookie);
            JSONObject jo = mu.getJSONObjectData();
            int len = jo.length();
            Log("共有" + len + "筆資料");

            String username = jo.getJSONObject("head_data").getString("username");
            String rcedits = jo.getJSONObject("head_data").getString("rcedits");
            String rcedits_use = jo.getJSONObject("head_data").getString("rcedits_use");
            odd_sw = jo.getJSONObject("head_data").getInt("odd_sw");
            Log("錄碼模式：" + odd_sw);
            enter_btn = jo.getJSONObject("head_data").getString("enter_btn");
            Log("輸入模式：" + enter_btn);
            left_show = jo.getJSONObject("head_data").getInt("left_show");
            Log("小票打印功能：" + left_show);

            Iterator<String> iter = jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("1").keys();
            while (iter.hasNext()) {
                String key = iter.next();
//                Log(key);
                list.add(key);
            }
//            Log(list.toString());
            sa = list.toArray(new String[list.size()]);

            Message msg = new Message();
            Bundle b = new Bundle();
            b.putString("username", username);
            b.putString("rcedits", rcedits);
            b.putString("rcedits_use", rcedits_use);
            msg.setData(b);
            handler.sendMessage(msg);
        } catch (Exception e) {
            Toast("无法与伺服器取得连线");
            Log(e.toString());
        }
        handler.sendEmptyMessage(1);
    }

    public void sendData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw(app_net + "app_mem_data_act");
            mu.sendCookie(cookie);
            mu.postKeyValue("entermode", enter_btn);
            if (rb_ac.isChecked()) {
                mu.postKeyValue("isfpfrankhotzhuan", "0");
            } else if (rb_tr.isChecked()) {
                mu.postKeyValue("isfpfrankhotzhuan", "1");
            }
            mu.postKeyValue("sendmode", String.valueOf(left_show));
//            List<String> ret = mu.getHtml();
//            for (String line : ret) {
//                Log(line);
//            }
            JSONObject jo = mu.getJSONObjectData();
            Log(jo.getString("ERR_TAG"));
            Log(jo.getString("sys_msg"));

            finish();
            startActivity(getIntent());
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
                    String username = msg.getData().getString("username");
                    Log("username = " + username);
                    String rcedits = msg.getData().getString("rcedits");
                    Log("rcedits = " + rcedits);
                    String rcedits_use = msg.getData().getString("rcedits_use");
                    Log("rcedits_use = " + rcedits_use);
                    tv_username.setText("帐号：" + username);
                    tv_rcedits.setText(rcedits);
                    tv_rcedits_use.setText(rcedits_use);
                    tv_rcedits_canUse.setText(String.valueOf(Integer.parseInt(rcedits) - Integer.parseInt(rcedits_use)));
                    switch (odd_sw) {
                        case 0:
                            rb_ac.setChecked(true);
                            break;
                        case 1:
                            rb_tr.setChecked(true);
                            break;
                        default:
                            break;
                    }
                    adapter = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, sa);
                    sp_1.setAdapter(adapter);
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
                MemberActivity.this.finish();//關閉activity
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
