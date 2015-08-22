package com.example.sheng.event_checksms.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.ClipboardManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheng.event_checksms.ApplicationBase.AIMA_funtion;
import com.example.sheng.event_checksms.ApplicationBase.AimaAPI;
import com.example.sheng.event_checksms.R;
import com.example.sheng.event_checksms.Utile.Utils;

public class UserInfoActivity extends AppCompatActivity {

    TextView text_userinfos, text_sms;
    Button btn_get_user_info, btn_get_mobile, btn_get_sms, btn_copy, btn_giveup;
    static ListView list_sms_history;
    static Context context;
    Spinner spinner_mobiles, spinner_pid;


    AIMAHandler aimaHandler;
    AIMA_funtion aima_funtion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        text_userinfos = (TextView) findViewById(R.id.text_user_info);
        text_sms = (TextView) findViewById(R.id.text_sms);


        context=UserInfoActivity.this;

        btn_get_mobile = (Button) findViewById(R.id.btn_get_mobile);
        btn_get_sms = (Button) findViewById(R.id.btn_get_sms);
        btn_get_user_info = (Button) findViewById(R.id.btn_getuserinfo);
        btn_giveup = (Button) findViewById(R.id.btn_giveup);
        btn_copy=(Button)findViewById(R.id.btn_copy);

        spinner_mobiles = (Spinner) findViewById(R.id.spinner_mobiles);

        spinner_mobiles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AimaAPI.MOBILE = ((TextView) view.findViewById(R.id.text_mobile)).getText().subSequence(5, 16).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_pid = (Spinner) findViewById(R.id.spinner_pid);
        spinner_pid.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, new String[]{"1012--->滴滴打车", "1013--->淘宝", "1014--->美团"}));


        list_sms_history = (ListView) findViewById(R.id.list_mobile);

        list_sms_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




                aima_funtion.getMobile(((TextView) view.findViewById(R.id.text_history_mobile)).getText().subSequence(5, 16).toString());


            }
        });

        aimaHandler = new AIMAHandler(this, text_userinfos, text_sms, list_sms_history, spinner_mobiles);
        aima_funtion = new AIMA_funtion(aimaHandler);


        View.OnClickListener btn_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_getuserinfo:

                        System.out.println(Utils.getFood().size());
                        //aima_funtion.getUserInfo();

                        break;


                    case R.id.btn_get_mobile:
                        aima_funtion.getMobile();
                        break;

                    case R.id.btn_get_sms:


                        if (btn_get_sms.getText().equals("获取信息")) {
                            text_sms.setText("正在获取短信...");
                            btn_get_sms.setText("停止获取");
                            aima_funtion.getSMS(btn_get_sms, "停止获取", 5);
                        } else {

                            btn_get_sms.setText("获取信息");
                        }


                        break;


                    case R.id.btn_giveup:

                        aima_funtion.giveUpNum();
                        break;


                    case R.id.btn_copy:
                        ClipboardManager copy = (ClipboardManager) UserInfoActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                        copy.setText(AimaAPI.MOBILE);
                        Toast.makeText(UserInfoActivity.this,"复制成功:"+AimaAPI.MOBILE,Toast.LENGTH_SHORT);

                        break;

                }

            }
        };

        btn_get_user_info.setOnClickListener(btn_listener);
        btn_get_mobile.setOnClickListener(btn_listener);
        btn_get_sms.setOnClickListener(btn_listener);
        btn_giveup.setOnClickListener(btn_listener);
        btn_copy.setOnClickListener(btn_listener);
        aima_funtion.getUserInfo();
        aima_funtion.getMobileNums();
        reflushSMSList();
    }



    public static void reflushSMSList()
    {
        SMS_history_adapter sms_history_adapter=new SMS_history_adapter(context,Utils.getFood());
        list_sms_history.setAdapter(sms_history_adapter);
    }


}


