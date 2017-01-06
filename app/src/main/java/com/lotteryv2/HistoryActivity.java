package com.lotteryv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class HistoryActivity extends AppCompatActivity {
    private Button btn_List, btn_QG, btn_QS, btn_Member, btn_History;
    private int totalPage;
    private LinearLayout ll_historyList;
    private pDialog pDialog;
    private String cookie, app_net, webside;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

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
        btn_List = (Button) findViewById(R.id.btn_hisList);
        btn_QG = (Button) findViewById(R.id.btn_hisQG);
        btn_QS = (Button) findViewById(R.id.btn_hisQS);
        btn_Member = (Button) findViewById(R.id.btn_hisMember);
        btn_History = (Button) findViewById(R.id.btn_hisHistory);
        ll_historyList = (LinearLayout) findViewById(R.id.ll_historyList);

        btnClick();
    }

    public void btnClick() {
        btn_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HistoryActivity.this, ListActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_QG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HistoryActivity.this, QGActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_QS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HistoryActivity.this, QSActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_Member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HistoryActivity.this, MemberActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(HistoryActivity.this, HistoryActivity.class);
//                it.putExtra("cookie", cookie);
//                startActivity(it);
//                finish();
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
            MultipartUtility_tw mu = new MultipartUtility_tw(app_net + "app_get_order_history");
            mu.sendCookie(cookie);
            JSONArray ja = mu.getJSONArrayData();
            int len = ja.length();
            Log("共有" + len + "筆資料");
            for (int i = 0; i < len; i++) {
                JSONObject rec = ja.getJSONObject(i);
                String issueno = rec.getString("issueno");
                String gold = rec.getString("gold");
                String war = rec.getString("war");
                String win = rec.getString("win");
                String profit = rec.getString("profit");

                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("issueno", issueno);
                b.putString("gold", gold);
                b.putString("war", war);
                b.putString("win", win);
                b.putString("profit", profit);
                b.putInt("i", i);
                msg.setData(b);
                handler.sendMessage(msg);
            }
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
                    String issueno = msg.getData().getString("issueno");
                    String gold = msg.getData().getString("gold");
                    String war = msg.getData().getString("war");
                    String win = msg.getData().getString("win");
                    String profit = msg.getData().getString("profit");
                    int i = msg.getData().getInt("i");
                    list(issueno, gold, war, win, profit, i);
                    break;
                case 1:
                    if (pDialog.isShowing()) pDialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    }

    public int dpToPx(int i) {
        return (int) ((i * getBaseContext().getResources().getDisplayMetrics().density) + 0.5);
    }

    /**
     * issueno : 期數名稱
     * is_result : 已結算 --暫無使用--
     * gold : 金額
     * war : 回水
     * win : 中獎
     * profit : 盈虧
     */
    public void list(final String issueno, String gold, String war, String win, String profit, int i) {
        LinearLayout ll = new LinearLayout(HistoryActivity.this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(50)));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        if (i % 2 == 0) {
            ll.setBackgroundColor(Color.parseColor("#d1d0d0"));
        }

        TextView tv0 = new TextView(HistoryActivity.this);
        tv0.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));

        Button btn1 = new Button(HistoryActivity.this);
        btn1.setText(issueno);
        btn1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData_2(issueno);
            }
        });

        TextView tv2 = new TextView(HistoryActivity.this);
        tv2.setText(gold);
        tv2.setTextSize(20);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv2.setGravity(Gravity.CENTER);
        tv2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv3 = new TextView(HistoryActivity.this);
        tv3.setText(war);
        tv3.setTextSize(20);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv3.setGravity(Gravity.CENTER);
        tv3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv4 = new TextView(HistoryActivity.this);
        tv4.setText(win);
        tv4.setTextSize(20);
        tv4.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv4.setGravity(Gravity.CENTER);
        tv4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv5 = new TextView(HistoryActivity.this);
        tv5.setText(profit);
        tv5.setTextSize(20);
        tv5.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv5.setGravity(Gravity.CENTER);
        tv5.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));

        TextView tv6 = new TextView(HistoryActivity.this);
        tv6.setLayoutParams(new LinearLayout.LayoutParams(dpToPx(10), LinearLayout.LayoutParams.MATCH_PARENT));

        ll.addView(tv0);
        ll.addView(btn1);
        ll.addView(tv2);
        ll.addView(tv3);
        ll.addView(tv4);
        ll.addView(tv5);
        ll.addView(tv6);
        ll_historyList.addView(ll);
    }

    private void getData_2(final String s) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                getListData(s);
                Looper.loop();
            }
        }.start();
    }

    private void getListData(String s) {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw(app_net + "app_order_dtl");
            mu.sendCookie(cookie);
            mu.postKeyValue("s_issueno", s);
            JSONObject jo = mu.getJSONObjectData();
            totalPage = jo.getInt("total_page");
            Log("共有" + totalPage + "頁");

            Intent it = new Intent(HistoryActivity.this, MoreListActivity.class);
            it.putExtra("cookie", cookie);
            it.putExtra("totalPage", totalPage);
            it.putExtra("s_issueno", s);
            it.putExtra("winList", false);
            startActivity(it);
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
                HistoryActivity.this.finish();//關閉activity
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
