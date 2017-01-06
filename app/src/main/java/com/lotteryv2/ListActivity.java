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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ArrayList<String> cancelList;
    private Button btn_List, btn_QG, btn_QS, btn_Member, btn_History;
    private Button btn_orderCancel, btn_BTP, btn_winGame, btn_moreData;
    private int totalPage;
    private int s_issueno;
    private LinearLayout ll_orderList;
    private pDialog pDialog;
    private String cookie, app_net, webside, ListID;
    private StringBuffer sb;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        webside = it.getStringExtra("webside");

        initial();
        connectThread(0);
    }

    public void initial() {
        handler = new UIHandler();
        cancelList = new ArrayList();
        sb = new StringBuffer();
        pDialog = new pDialog(this);

        app_net = "http://" + getResources().getString(R.string.app_net) + "/mobile/wap_ajax.php?action=";
        btn_List = (Button) findViewById(R.id.btn_listList);
        btn_QG = (Button) findViewById(R.id.btn_listQG);
        btn_QS = (Button) findViewById(R.id.btn_listQS);
        btn_Member = (Button) findViewById(R.id.btn_listMember);
        btn_History = (Button) findViewById(R.id.btn_listHistory);
        btn_orderCancel = (Button) findViewById(R.id.btn_orderCancel);
        btn_BTP = (Button) findViewById(R.id.btn_BTP);
        btn_winGame = (Button) findViewById(R.id.btn_winGame);
        btn_moreData = (Button) findViewById(R.id.btn_moreData);
        ll_orderList = (LinearLayout) findViewById(R.id.ll_orderList);

        btnClick();
    }

    public void btnClick() {
        btn_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(ListActivity.this, ListActivity.class);
//                it.putExtra("cookie", cookie);
//                startActivity(it);
//                finish();
            }
        });
        btn_QG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListActivity.this, QGActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_QS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListActivity.this, QSActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_Member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListActivity.this, MemberActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListActivity.this, HistoryActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_orderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectThread(1);
            }
        });
        btn_BTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListActivity.this, BTPActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("ListID", ListID);
                startActivity(it);
            }
        });
        btn_winGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListActivity.this, MoreListActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("totalPage", totalPage);
                it.putExtra("s_issueno", String.valueOf(s_issueno));
                it.putExtra("winList", true);
                startActivity(it);
            }
        });
        btn_moreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListActivity.this, MoreListActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("totalPage", totalPage);
                it.putExtra("s_issueno", String.valueOf(s_issueno));
                it.putExtra("winList", false);
                startActivity(it);
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
                        orderCancel();
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
            MultipartUtility_tw mu = new MultipartUtility_tw(app_net + "app_order_dtl");
            mu.sendCookie(cookie);
//            List<String> a = mu.getHtml();
//            for (String line : a) {
//                Log.i("troy", line);
//            }
            JSONObject jo = mu.getJSONObjectData();
            totalPage = jo.getInt("total_page");
            Log("共有" + totalPage + "頁");
            s_issueno = jo.getInt("s_issueno");
            Log("第" + s_issueno + "期");

            JSONArray ja = jo.getJSONArray("list");
            int len = ja.length();
            Log("共有" + len + "筆資料");
            if (len != 0) {
                ListID = ja.getJSONObject(0).getString("id");

                for (int i = 0; i < len; i++) {
                    JSONObject rec = ja.getJSONObject(i);
                    String number = rec.getString("number");
                    String money = rec.getString("money");
                    String frank = rec.getString("frank");
                    int cancel_able = rec.getInt("cancel_able");
                    String id = rec.getString("id");

                    Message msg = new Message();
                    Bundle b = new Bundle();
                    b.putString("number", number);
                    b.putString("money", money);
                    b.putString("frank", frank);
                    b.putInt("cancel_able", cancel_able);
                    b.putInt("i", i);
                    b.putString("id", id);
                    msg.setData(b);
                    handler.sendMessage(msg);
                }
            }
        } catch (Exception e) {
            Toast("无法与伺服器取得连线");
            Log(e.toString());
        }
        handler.sendEmptyMessage(1);
    }

    public void orderCancel() {
        for (String a : cancelList) {
            Log.i("troy", a);
            sb.append(a);
            sb.append(",");
        }
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw(app_net + "app_exe_order_cancel");
            mu.sendCookie(cookie);
            mu.postKeyValue("idarray", sb.toString());
//            List<String> a = mu.getHtml();
//            for (String line : a) {
//                Log.i("troy", line);
//            }
            String b = mu.getJSONObjectData().getString("message");
            Log(b);
//            Toast(退码成功);
        } catch (Exception e) {
//            Toast(退码失败);
            Log(e.toString());
        }
        handler.sendEmptyMessage(1);
        finish();
        startActivity(getIntent());
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
                    int cancel_able = msg.getData().getInt("cancel_able");
                    int i = msg.getData().getInt("i");
                    String id = msg.getData().getString("id");
                    list(number, money, frank, i, cancel_able, id);
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

    public void list(String number, String money, String frank, int i, int cancel_able, final String id) {
        LinearLayout ll = new LinearLayout(ListActivity.this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(50)));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        if (i % 2 == 0) {
            ll.setBackgroundColor(Color.parseColor("#d1d0d0"));
        }

        TextView tv0 = new TextView(ListActivity.this);
        tv0.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));

        TextView tv1 = new TextView(ListActivity.this);
        tv1.setText(number);
        tv1.setTextSize(20);
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv1.setGravity(Gravity.CENTER);
        tv1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv2 = new TextView(ListActivity.this);
        tv2.setText(money);
        tv2.setTextSize(20);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv2.setGravity(Gravity.CENTER);
        tv2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv3 = new TextView(ListActivity.this);
        tv3.setText(frank);
        tv3.setTextSize(20);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv3.setGravity(Gravity.CENTER);
        tv3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));

        TextView tv5 = new TextView(ListActivity.this);
        tv5.setLayoutParams(new LinearLayout.LayoutParams(dpToPx(10), LinearLayout.LayoutParams.MATCH_PARENT));

        ll.addView(tv0);
        ll.addView(tv1);
        ll.addView(tv2);
        ll.addView(tv3);
        if (cancel_able == 1) {
            CheckBox tv4 = new CheckBox(ListActivity.this);
            int cbID = Integer.parseInt(id);
            tv4.setId(cbID);
            tv4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((CheckBox) view).isChecked()) {
                        cancelList.add(id);
                        Log(cancelList.toString());
                    } else {
                        cancelList.remove(id);
                        Log(cancelList.toString());
                    }
                }
            });
            tv4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            ll.addView(tv4);
        } else {
            TextView tv4 = new TextView(ListActivity.this);
            tv4.setText("--");
            tv4.setTextSize(20);
            tv4.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            tv4.setGravity(Gravity.CENTER);
            tv4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            ll.addView(tv4);
        }
        ll.addView(tv5);
        ll_orderList.addView(ll);
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
                ListActivity.this.finish();//關閉activity
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
