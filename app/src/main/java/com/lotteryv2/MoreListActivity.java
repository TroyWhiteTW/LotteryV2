package com.lotteryv2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MoreListActivity extends AppCompatActivity {
    private boolean winList = false;
    private Button btn_loadNextPage;
    private int totalPage;
    private int whatpage = 1;
    private LinearLayout ll_moreList;
    private pDialog pDialog;
    private String cookie, app_net, webside, s_issueno;
    private TextView tv_totalPages;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_list);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        webside = it.getStringExtra("webside");
        s_issueno = it.getStringExtra("s_issueno");
        winList = it.getBooleanExtra("winList", false);

        initial();
        connectThread(0);
    }

    public void initial() {
        handler = new UIHandler();
        pDialog = new pDialog(this);

        app_net = "http://" + getResources().getString(R.string.app_net) + "/mobile/wap_ajax.php?action=";
        btn_loadNextPage = (Button) findViewById(R.id.btn_loadNextPage);
        ll_moreList = (LinearLayout) findViewById(R.id.ll_moreList);
        tv_totalPages = (TextView) findViewById(R.id.tv_totalPages);

        btnClick();
    }

    public void btnClick() {
        btn_loadNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whatpage < totalPage) {
                    whatpage++;
                    connectThread(0);
                } else {
                    Toast("已无更多资料");
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
//                        sendData();
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
            String page = String.valueOf(whatpage);
            MultipartUtility_tw mu = new MultipartUtility_tw(app_net + "app_order_dtl");
            mu.sendCookie(cookie);
            mu.postKeyValue("page", page);
            mu.postKeyValue("s_issueno", s_issueno);
            if (winList == true) mu.postKeyValue("doaction", "award");
            JSONObject jo = mu.getJSONObjectData();
            totalPage = jo.getInt("total_page");
            Log("total_page：" + jo.getInt("total_page"));
            JSONArray ja = jo.getJSONArray("list");
            int len = ja.length();

            Message msg_2 = new Message();
            msg_2.what = 2;
            Bundle b_2 = new Bundle();
            b_2.putString("page", page);
            msg_2.setData(b_2);
            handler.sendMessage(msg_2);

            for (int i = 0; i < len; i++) {
                JSONObject rec = ja.getJSONObject(i);
                String number = rec.getString("number");
                String money = rec.getString("money");
                String frank = rec.getString("frank");

                Message msg = new Message();
                msg.what = 0;
                Bundle b = new Bundle();
                b.putString("number", number);
                b.putString("money", money);
                b.putString("frank", frank);
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
                    String number = msg.getData().getString("number");
                    String money = msg.getData().getString("money");
                    String frank = msg.getData().getString("frank");
                    int i = msg.getData().getInt("i");
                    list(number, money, frank, i);
                    break;
                case 1:
                    tv_totalPages.setText("共 " + totalPage + " 頁");
                    if (pDialog.isShowing()) pDialog.dismiss();
                    break;
                case 2:
                    String page = msg.getData().getString("page");
                    listTitle(page);
                    break;
                default:
                    break;
            }
        }
    }

    public void listTitle(String page) {
        TextView page_tv = new TextView(MoreListActivity.this);
        page_tv.setText("第" + page + "頁");
        page_tv.setTextSize(30);
        page_tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        page_tv.setBackgroundColor(Color.parseColor("#da8c8c"));
        page_tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_moreList.addView(page_tv);
    }

    public void list(String number, String money, String frank, int i) {
        LinearLayout ll = new LinearLayout(MoreListActivity.this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        if (i % 2 == 0) {
            ll.setBackgroundColor(Color.parseColor("#d1d0d0"));
        }
        TextView tv0 = new TextView(MoreListActivity.this);
        tv0.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));
        TextView tv1 = new TextView(MoreListActivity.this);
        tv1.setText(number);
        tv1.setTextSize(20);
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv2 = new TextView(MoreListActivity.this);
        tv2.setText(money);
        tv2.setTextSize(20);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv3 = new TextView(MoreListActivity.this);
        tv3.setText(frank);
        tv3.setTextSize(20);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv4 = new TextView(MoreListActivity.this);
        tv4.setLayoutParams(new LinearLayout.LayoutParams(50, LinearLayout.LayoutParams.MATCH_PARENT));
        ll.addView(tv0);
        ll.addView(tv1);
        ll.addView(tv2);
        ll.addView(tv3);
        ll.addView(tv4);
        ll_moreList.addView(ll);
    }

    public void Toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void Log(String s) {
        Log.i("troy", s);
    }
}
