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
import android.widget.AdapterView;
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
    private ArrayAdapter<String> adapter1, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7, adapter8, adapter9, adapter10,
            adapter11, adapter12;
    private ArrayList<String> al1, al2, al3, al4, al5, al6, al7, al8, al9, al10,
            al11, al12;
    private Button btn_List, btn_QG, btn_QS, btn_Member, btn_History;
    private Button btn_commit;
    private int odd_sw;
    private int left_show;
    private int i1, i2, i3, i4, i5, i6;
    private pDialog pDialog;
    private RadioButton rb_ac, rb_tr;
    private String enter_btn;
    private String cookie, app_net, webside;
    private Spinner sp_1, sp_2, sp_3, sp_4, sp_5, sp_6, sp_7, sp_8, sp_9, sp_10,
            sp_11, sp_12;
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
        pDialog = new pDialog(this);
        al1 = new ArrayList();
        al2 = new ArrayList();
        al3 = new ArrayList();
        al4 = new ArrayList();
        al5 = new ArrayList();
        al6 = new ArrayList();
        al7 = new ArrayList();
        al8 = new ArrayList();
        al9 = new ArrayList();
        al10 = new ArrayList();
        al11 = new ArrayList();
        al12 = new ArrayList();

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
        sp_2 = (Spinner) findViewById(R.id.sp_2);
        sp_3 = (Spinner) findViewById(R.id.sp_3);
        sp_4 = (Spinner) findViewById(R.id.sp_4);
        sp_5 = (Spinner) findViewById(R.id.sp_5);
        sp_6 = (Spinner) findViewById(R.id.sp_6);
        sp_7 = (Spinner) findViewById(R.id.sp_7);
        sp_8 = (Spinner) findViewById(R.id.sp_8);
        sp_9 = (Spinner) findViewById(R.id.sp_9);
        sp_10 = (Spinner) findViewById(R.id.sp_10);
        sp_11 = (Spinner) findViewById(R.id.sp_11);
        sp_12 = (Spinner) findViewById(R.id.sp_12);
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
//            List<String> a = mu.getHtml();
//            for (String line : a) {
//                Log.i("troy", line);
//            }
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

            String s1 = jo.getJSONObject("huishui_list").getJSONObject("idx").getString("102");
            String s2 = jo.getJSONObject("huishui_list").getJSONObject("idx").getString("106");
            String s3 = jo.getJSONObject("huishui_list").getJSONObject("idx").getString("5");
            String s4 = jo.getJSONObject("huishui_list").getJSONObject("idx").getString("6");
            String s5 = jo.getJSONObject("huishui_list").getJSONObject("idx").getString("7");
            String s6 = jo.getJSONObject("huishui_list").getJSONObject("idx").getString("107");

            Iterator<String> iter1 = jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("102").keys();
            while (iter1.hasNext()) {
                String key = iter1.next();
                if (s1.equals(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("102").getJSONObject(key).getString("huishui")))
                    i1 = al1.size();
                al1.add(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("102").getJSONObject(key).getString("huishui"));
                al2.add(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("102").getJSONObject(key).getString("frank"));
            }
            Iterator<String> iter2 = jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("106").keys();
            while (iter2.hasNext()) {
                String key = iter2.next();
                if (s2.equals(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("106").getJSONObject(key).getString("huishui")))
                    i2 = al3.size();
                al3.add(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("106").getJSONObject(key).getString("huishui"));
                al4.add(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("106").getJSONObject(key).getString("frank"));
            }
            Iterator<String> iter3 = jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("5").keys();
            while (iter3.hasNext()) {
                String key = iter3.next();
                if (s3.equals(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("5").getJSONObject(key).getString("huishui")))
                    i3 = al5.size();
                al5.add(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("5").getJSONObject(key).getString("huishui"));
                al6.add(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("5").getJSONObject(key).getString("frank"));
            }
            Iterator<String> iter4 = jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("6").keys();
            while (iter4.hasNext()) {
                String key = iter4.next();
                if (s4.equals(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("6").getJSONObject(key).getString("huishui")))
                    i4 = al7.size();
                al7.add(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("6").getJSONObject(key).getString("huishui"));
                al8.add(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("6").getJSONObject(key).getString("frank").split("\\/")[0]);
            }
            Iterator<String> iter5 = jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("7").keys();
            while (iter5.hasNext()) {
                String key = iter5.next();
                if (s5.equals(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("7").getJSONObject(key).getString("huishui")))
                    i5 = al9.size();
                al9.add(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("7").getJSONObject(key).getString("huishui"));
                al10.add(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("7").getJSONObject(key).getString("frank").split("\\/")[0]);
            }
            Iterator<String> iter6 = jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("107").keys();
            while (iter6.hasNext()) {
                String key = iter6.next();
                if (s6.equals(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("107").getJSONObject(key).getString("huishui")))
                    i6 = al11.size();
                al11.add(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("107").getJSONObject(key).getString("huishui"));
                al12.add(jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("107").getJSONObject(key).getString("frank").split("\\/")[0]);
            }

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

            mu.postKeyValue("formhash", "09ac9c04");
            mu.postKeyValue("editsubmit", "提交");
            //--回水賠率--
//            mu.postKeyValue("fixstrhuishui_1", sp_1.getSelectedItem().toString());//二定位
//            mu.postKeyValue("fixstrfrank_1", sp_1.getSelectedItem().toString());
//            mu.postKeyValue("fixstrhuishui_102", sp_1.getSelectedItem().toString());
//            mu.postKeyValue("fixstrfrank_102", sp_1.getSelectedItem().toString());
//            mu.postKeyValue("fixstrhuishui_101", sp_1.getSelectedItem().toString());
//            mu.postKeyValue("fixstrfrank_101", sp_1.getSelectedItem().toString());
//            mu.postKeyValue("fixstrhuishui_100", sp_1.getSelectedItem().toString());
//            mu.postKeyValue("fixstrfrank_100", sp_1.getSelectedItem().toString());
//            mu.postKeyValue("fixstrhuishui_99", sp_1.getSelectedItem().toString());
//            mu.postKeyValue("fixstrfrank_99", sp_1.getSelectedItem().toString());
//            mu.postKeyValue("fixstrhuishui_98", sp_1.getSelectedItem().toString());
//            mu.postKeyValue("fixstrfrank_98", sp_1.getSelectedItem().toString());
//            mu.postKeyValue("fixstrhuishui_97", sp_1.getSelectedItem().toString());
//            mu.postKeyValue("fixstrfrank_97", sp_1.getSelectedItem().toString());
//
//            mu.postKeyValue("fixstrhuishui_4", sp_3.getSelectedItem().toString());//三定位
//            mu.postKeyValue("fixstrfrank_4", sp_3.getSelectedItem().toString());
//            mu.postKeyValue("fixstrhuishui_106", sp_3.getSelectedItem().toString());
//            mu.postKeyValue("fixstrfrank_106", sp_3.getSelectedItem().toString());
//            mu.postKeyValue("fixstrhuishui_105", sp_3.getSelectedItem().toString());
//            mu.postKeyValue("fixstrfrank_105", sp_3.getSelectedItem().toString());
//            mu.postKeyValue("fixstrhuishui_104", sp_3.getSelectedItem().toString());
//            mu.postKeyValue("fixstrfrank_104", sp_3.getSelectedItem().toString());
//            mu.postKeyValue("fixstrhuishui_103", sp_3.getSelectedItem().toString());
//            mu.postKeyValue("fixstrfrank_103", sp_3.getSelectedItem().toString());
//
//            mu.postKeyValue("fixstrhuishui_5", sp_5.getSelectedItem().toString());//四定位
//            mu.postKeyValue("fixstrfrank_5", sp_5.getSelectedItem().toString());//四定位
//            mu.postKeyValue("fixstrhuishui_6", sp_7.getSelectedItem().toString());//二字現
//            mu.postKeyValue("fixstrfrank_6", sp_7.getSelectedItem().toString());//二字現
//            mu.postKeyValue("fixstrhuishui_7", sp_9.getSelectedItem().toString());//三字現
//            mu.postKeyValue("fixstrfrank_7", sp_9.getSelectedItem().toString());//三字現
//            mu.postKeyValue("fixstrhuishui_107", sp_11.getSelectedItem().toString());//四字現
//            mu.postKeyValue("fixstrfrank_107", sp_11.getSelectedItem().toString());//四字現

            mu.postKeyValue("fixstrhuishui_1", "0");//二定位
            mu.postKeyValue("fixstrfrank_1", "0");
            mu.postKeyValue("fixstrhuishui_102", "0");
            mu.postKeyValue("fixstrfrank_102", "0");
            mu.postKeyValue("fixstrhuishui_101", "0");
            mu.postKeyValue("fixstrfrank_101", "0");
            mu.postKeyValue("fixstrhuishui_100", "0");
            mu.postKeyValue("fixstrfrank_100", "0");
            mu.postKeyValue("fixstrhuishui_99", "0");
            mu.postKeyValue("fixstrfrank_99", "0");
            mu.postKeyValue("fixstrhuishui_98", "0");
            mu.postKeyValue("fixstrfrank_98", "0");
            mu.postKeyValue("fixstrhuishui_97", "0");
            mu.postKeyValue("fixstrfrank_97", "0");

            mu.postKeyValue("fixstrhuishui_4", "0");//三定位
            mu.postKeyValue("fixstrfrank_4", "0");
            mu.postKeyValue("fixstrhuishui_106", "0");
            mu.postKeyValue("fixstrfrank_106", "0");
            mu.postKeyValue("fixstrhuishui_105", "0");
            mu.postKeyValue("fixstrfrank_105", "0");
            mu.postKeyValue("fixstrhuishui_104", "0");
            mu.postKeyValue("fixstrfrank_104", "0");
            mu.postKeyValue("fixstrhuishui_103", "0");
            mu.postKeyValue("fixstrfrank_103", "0");

            mu.postKeyValue("fixstrhuishui_5", "0");//四定位
            mu.postKeyValue("fixstrfrank_5", "0");//四定位
            mu.postKeyValue("fixstrhuishui_6", "0");//二字現
            mu.postKeyValue("fixstrfrank_6", "0");//二字現
            mu.postKeyValue("fixstrhuishui_7", "0");//三字現
            mu.postKeyValue("fixstrfrank_7", "0");//三字現
            mu.postKeyValue("fixstrhuishui_107", "0");//四字現
            mu.postKeyValue("fixstrfrank_107", "0");//四字現

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
                    adapter1 = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, al1);
                    sp_1.setAdapter(adapter1);
                    adapter2 = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, al2);
                    sp_2.setAdapter(adapter2);
                    adapter3 = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, al3);
                    sp_3.setAdapter(adapter3);
                    adapter4 = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, al4);
                    sp_4.setAdapter(adapter4);
                    adapter5 = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, al5);
                    sp_5.setAdapter(adapter5);
                    adapter6 = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, al6);
                    sp_6.setAdapter(adapter6);
                    adapter7 = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, al7);
                    sp_7.setAdapter(adapter7);
                    adapter8 = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, al8);
                    sp_8.setAdapter(adapter8);
                    adapter9 = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, al9);
                    sp_9.setAdapter(adapter9);
                    adapter10 = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, al10);
                    sp_10.setAdapter(adapter10);
                    adapter11 = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, al11);
                    sp_11.setAdapter(adapter11);
                    adapter12 = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item, al12);
                    sp_12.setAdapter(adapter12);

                    sp_1.setSelection(i1);
                    sp_2.setSelection(i1);
                    sp_3.setSelection(i2);
                    sp_4.setSelection(i2);
                    sp_5.setSelection(i3);
                    sp_6.setSelection(i3);
                    sp_7.setSelection(i4);
                    sp_8.setSelection(i4);
                    sp_9.setSelection(i5);
                    sp_10.setSelection(i5);
                    sp_11.setSelection(i6);
                    sp_12.setSelection(i6);

                    sp_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int pos = sp_1.getSelectedItemPosition();
                            sp_2.setSelection(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    sp_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int pos = sp_2.getSelectedItemPosition();
                            sp_1.setSelection(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    sp_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int pos = sp_3.getSelectedItemPosition();
                            sp_4.setSelection(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    sp_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int pos = sp_4.getSelectedItemPosition();
                            sp_3.setSelection(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    sp_5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int pos = sp_5.getSelectedItemPosition();
                            sp_6.setSelection(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    sp_6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int pos = sp_6.getSelectedItemPosition();
                            sp_5.setSelection(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    sp_7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int pos = sp_7.getSelectedItemPosition();
                            sp_8.setSelection(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    sp_8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int pos = sp_8.getSelectedItemPosition();
                            sp_7.setSelection(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    sp_9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int pos = sp_9.getSelectedItemPosition();
                            sp_10.setSelection(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    sp_10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int pos = sp_10.getSelectedItemPosition();
                            sp_9.setSelection(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    sp_11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int pos = sp_11.getSelectedItemPosition();
                            sp_12.setSelection(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    sp_12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int pos = sp_12.getSelectedItemPosition();
                            sp_11.setSelection(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
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
