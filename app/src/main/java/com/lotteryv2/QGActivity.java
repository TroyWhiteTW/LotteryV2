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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class QGActivity extends AppCompatActivity {
    private Button btn_List, btn_QG, btn_QS, btn_Member, btn_History;
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_X;
    private Button btn_clear, btn_commit;
    private int textPos = 0;
    private int rcedits, rcedits_use;
    private LinearLayout ll_recentOrder;
    private pDialog pDialog;
    private RadioButton rb_allfour, rb_change, rb_normal;
    private String cookie, app_net, webside;
    private StringBuffer sb, sb_2, sb_fail;
    private ScrollView sv_gameContent;
    private TextView tv_number, tv_numberType, tv_money, tv_gameOpen, tv_fail;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qg);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        webside = it.getStringExtra("webside");

        initial();
        connectThread(0);
    }

    public void initial() {
        handler = new UIHandler();
        pDialog = new pDialog(this);
        sb = new StringBuffer();
        sb_2 = new StringBuffer();
        sb_fail = new StringBuffer();

        app_net = "http://" + webside + "/mobile/wap_ajax.php?action=";
        btn_List = (Button) findViewById(R.id.btn_qgList);
        btn_QG = (Button) findViewById(R.id.btn_qgQG);
        btn_QS = (Button) findViewById(R.id.btn_qgQS);
        btn_Member = (Button) findViewById(R.id.btn_qgMember);
        btn_History = (Button) findViewById(R.id.btn_qgHistory);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_X = (Button) findViewById(R.id.btn_X);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_commit = (Button) findViewById(R.id.btn_commit);
        ll_recentOrder = (LinearLayout) findViewById(R.id.ll_recentOrder);
        rb_allfour = (RadioButton) findViewById(R.id.rb_allfour);
        rb_change = (RadioButton) findViewById(R.id.rb_change);
        rb_normal = (RadioButton) findViewById(R.id.rb_normal);
        sv_gameContent = (ScrollView) findViewById(R.id.sv_gameContent);
        tv_gameOpen = (TextView) findViewById(R.id.tv_gameOpen);
        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_numberType = (TextView) findViewById(R.id.tv_numberType);
        tv_fail = (TextView) findViewById(R.id.tv_fail);
        tv_money = (TextView) findViewById(R.id.tv_money);

        rb_normal.setChecked(true);
        tv_number.setBackgroundColor(Color.parseColor("#da8c8c"));

        btnClick();
        tvClick();
    }

    public void btnClick() {
        btn_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QGActivity.this, ListActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("webside", webside);
                startActivity(it);
                finish();
            }
        });
        btn_QG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(QGActivity.this, QGActivity.class);
//                it.putExtra("cookie", cookie);
//                startActivity(it);
//                finish();
            }
        });
        btn_QS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QGActivity.this, QSActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("webside", webside);
                startActivity(it);
                finish();
            }
        });
        btn_Member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QGActivity.this, MemberActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("webside", webside);
                startActivity(it);
                finish();
            }
        });
        btn_History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QGActivity.this, HistoryActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("webside", webside);
                startActivity(it);
                finish();
            }
        });
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "1";
                switchType(a);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "2";
                switchType(a);
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "3";
                switchType(a);
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "4";
                switchType(a);
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "5";
                switchType(a);
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "6";
                switchType(a);
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "7";
                switchType(a);
            }
        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "8";
                switchType(a);
            }
        });
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "9";
                switchType(a);
            }
        });
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "0";
                switchType(a);
            }
        });
        btn_X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "X";
                switchType(a);
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb_fail.setLength(0);
                tv_fail.setText("");
                String a = tv_number.getText().toString();
                String b = tv_money.getText().toString();
                int i = a.length();
                if (rb_allfour.isChecked() && i != 4) {
                    Toast("四字现号码错误");
                } else if (a.isEmpty() || b.isEmpty() || i > 4 || i < 2) {
                    Toast("号码或金额输入错误");
                } else if (Integer.parseInt(b) > rcedits - rcedits_use) {
                    Toast("信用余额不足");
                } else if (i == 2 && a.contains("X")) {
                    Toast("号码或金额输入错误");
                } else if (i == 3 && a.contains("X")) {
                    Toast("号码或金额输入错误");
                } else if (i == 4 && a.contains("X")) {
                    int x = 0;
                    if (String.valueOf(a.charAt(0)).equals("X")) x++;
                    if (String.valueOf(a.charAt(1)).equals("X")) x++;
                    if (String.valueOf(a.charAt(2)).equals("X")) x++;
                    if (String.valueOf(a.charAt(3)).equals("X")) x++;
                    Log("x=" + x);
                    if (x >= 3) {
                        Toast("号码或金额输入错误");
                    } else {
                        sendData(a, b);
                    }
                } else {
                    sendData(a, b);
                }
                reset();
            }
        });
    }

    public void tvClick() {
        tv_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_number.setBackgroundColor(Color.parseColor("#da8c8c"));
                tv_money.setBackgroundColor(Color.parseColor("#ffffff"));
                textPos = 0;
            }
        });
        tv_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_money.setBackgroundColor(Color.parseColor("#da8c8c"));
                tv_number.setBackgroundColor(Color.parseColor("#ffffff"));
                textPos = 1;
            }
        });
    }

    public void switchType(String a) {
        switch (a) {
            case "X":
                if (rb_allfour.isChecked() && textPos == 0) {
                } else if (textPos == 0) {
                    setNumberText(a);
                } else if (textPos == 1) {
                }
                break;
            default:
                switch (textPos) {
                    case 0:
                        setNumberText(a);
                        break;
                    case 1:
                        setMoneyText(a);
                        break;
                    default:
                        break;
                }
                break;
        }
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
            rcedits = jo.getInt("rcedits");
            Log("rcedits= " + rcedits);
            rcedits_use = jo.getInt("rcedits_use");
            Log("rcedits_use= " + rcedits_use);

            Message msg = new Message();
            Bundle b = new Bundle();
            b.putInt("game_open", jo.getInt("game_open"));
            msg.setData(b);
            handler.sendMessage(msg);

            MultipartUtility_tw mu_2 = new MultipartUtility_tw(app_net + "app_order_dtl");
            mu_2.sendCookie(cookie);
            JSONObject rec = mu_2.getJSONObjectData().getJSONArray("list").getJSONObject(0);
            String number = rec.getString("number");
            String money = rec.getString("money");
            String frank = rec.getString("frank");
            int stattuima = rec.getInt("stattuima");

            Message msg_2 = new Message();
            msg_2.what = 2;
            Bundle b_2 = new Bundle();
            b_2.putString("number", number);
            b_2.putString("money", money);
            b_2.putString("frank", frank);
            b_2.putInt("stattuima", stattuima);
            msg_2.setData(b_2);
            handler.sendMessage(msg_2);
        } catch (IOException e) {
            Toast("无法与伺服器取得连线");
            Log(e.toString());
        } catch (JSONException e) {
            Log(e.toString());
        }
        handler.sendEmptyMessage(1);
    }

    public void sendData(final String a, final String b) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                sendGameData(a, b);
                Looper.loop();
            }
        }.start();
    }

    public void sendGameData(String a, String b) {
        int fail = 0;
        try {
            int allfournumber = 0;
            if (rb_allfour.isChecked()) allfournumber = 1;
            MultipartUtility_tw mu = new MultipartUtility_tw(app_net + "app_soonsend");
            mu.sendCookie(cookie);
            mu.postKeyValue("post_number", a + "," + b + "," + allfournumber);
            JSONArray ja = mu.getJSONObjectData().getJSONObject("fail_dtl").getJSONArray("l");
            Log(ja.toString());
            int len = ja.length();
            Log.i("troy", "共有" + len + "筆資料");
            if (len != 0) {
                for (int i = 0; i < len; i++) {
                    sb_fail.append(ja.getJSONObject(i).getString("number"));
                    sb_fail.append(" x ");
                    sb_fail.append(ja.getJSONObject(i).getInt("money"));
                    sb_fail.append("\n");
                    if (ja.getJSONObject(i).getString("number").equals(a) && ja.getJSONObject(i).getString("money").equals(b)) {
                        fail = 1;
                    }
                }
            }
            handler.sendEmptyMessage(3);

            if (fail == 1) {
                Toast("下注失败");
            } else {
                MultipartUtility_tw mu_2 = new MultipartUtility_tw(app_net + "app_order_dtl");
                mu_2.sendCookie(cookie);
                JSONObject rec = mu_2.getJSONObjectData().getJSONArray("list").getJSONObject(0);
                String number = rec.getString("number");
                String money = rec.getString("money");
                String frank = rec.getString("frank");

                Message msg_2 = new Message();
                msg_2.what = 2;
                Bundle b_2 = new Bundle();
                b_2.putString("number", number);
                b_2.putString("money", money);
                b_2.putString("frank", frank);
                msg_2.setData(b_2);
                handler.sendMessage(msg_2);
                Toast("下注成功");
            }

            MultipartUtility_tw mu_3 = new MultipartUtility_tw(app_net + "app_head_data");
            mu_3.sendCookie(cookie);
            JSONObject jo = mu_3.getJSONObjectData();
            rcedits = jo.getInt("rcedits");
            Log("rcedits= " + rcedits);
            rcedits_use = jo.getInt("rcedits_use");
            Log("rcedits_use= " + rcedits_use);

        } catch (IOException e) {
            Toast("无法与伺服器取得连线");
            Log(e.toString());
        } catch (JSONException e) {
            Toast("资料错误");
            Log(e.toString());
        }
    }

    public void reset() {
        tv_number.setText("");
        tv_numberType.setText("--");
        tv_money.setText("");
        sb.setLength(0);
        sb_2.setLength(0);
        tv_number.setBackgroundColor(Color.parseColor("#da8c8c"));
        tv_money.setBackgroundColor(Color.parseColor("#ffffff"));
        textPos = 0;
    }

    public void setNumberText(String s) {
        if (!rb_allfour.isChecked()) {
            if (sb.length() < 4) {
                sb.append(s);
                Log("sb--" + sb.toString());
                tv_number.setText(sb.toString());
                switch (sb.length()) {
                    case 2:
                        tv_numberType.setText("現");
                        break;
                    case 3:
                        tv_numberType.setText("現");
                        break;
                    default:
                        tv_numberType.setText("--");
                        break;
                }
            }
        } else {
            tv_numberType.setText("");
            if (sb.length() < 4) {
                sb.append(s);
                Log("sb--" + sb.toString());
                tv_number.setText(sb.toString());
            }
        }
    }

    public void setMoneyText(String s) {
        if (sb_2.length() < 7) {
            sb_2.append(s);
            Log("sb_2--" + sb_2.toString());
            tv_money.setText(sb_2.toString());
        }
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log("msg.what = " + msg.what);
            switch (msg.what) {
                case 0:
                    int game_open_code = msg.getData().getInt("game_open");
                    if (game_open_code == 0) {
                        game_open_toast(0);
                        tv_gameOpen.setText("关盘中");
                    } else if (game_open_code == 1) {
                        game_open_toast(1);
                        tv_gameOpen.setText("开盘中");
                        sv_gameContent.setVisibility(View.VISIBLE);
                    }
                    break;
                case 1:
                    if (pDialog.isShowing()) pDialog.dismiss();
                    break;
                case 2:
                    String number = msg.getData().getString("number");
                    String money = msg.getData().getString("money");
                    String frank = msg.getData().getString("frank");
                    int stattuima = msg.getData().getInt("stattuima");
                    list(number, money, frank, stattuima);
                    break;
                case 3:
                    tv_fail.setText(sb_fail.toString());
                    break;
                default:
                    break;
            }
        }
    }

    public void game_open_toast(int i) {
        if (i == 0) {
            Toast("關盤中");
        } else if (i == 1) {
            Toast("開盤中");
        }
    }

    public void list(String number, String money, String frank, int stattuima) {
        LinearLayout ll = new LinearLayout(QGActivity.this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        Log("stattuima = " + stattuima);
        switch (stattuima) {
            case 1:
                ll.setBackgroundColor(Color.parseColor("#d1d0d0"));
                break;
        }

        TextView tv0 = new TextView(QGActivity.this);
        tv0.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));
        TextView tv1 = new TextView(QGActivity.this);
        tv1.setText(number);
        tv1.setTextSize(20);
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv2 = new TextView(QGActivity.this);
        tv2.setText(money);
        tv2.setTextSize(20);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv3 = new TextView(QGActivity.this);
        tv3.setText(frank);
        tv3.setTextSize(20);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv4 = new TextView(QGActivity.this);
        tv4.setLayoutParams(new LinearLayout.LayoutParams(50, LinearLayout.LayoutParams.MATCH_PARENT));
        ll.addView(tv0);
        ll.addView(tv1);
        ll.addView(tv2);
        ll.addView(tv3);
        ll.addView(tv4);
        ll_recentOrder.addView(ll);
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
                QGActivity.this.finish();//關閉activity
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
