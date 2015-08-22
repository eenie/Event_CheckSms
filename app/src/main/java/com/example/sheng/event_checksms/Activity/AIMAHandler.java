package com.example.sheng.event_checksms.Activity;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheng.event_checksms.ApplicationBase.AIMA_mobile_infos;
import com.example.sheng.event_checksms.ApplicationBase.AimaAPI;
import com.example.sheng.event_checksms.Utile.Utils;

import java.util.ArrayList;

public class AIMAHandler extends Handler {

    Context context;
    TextView text_infos, text_sms;
    ListView list_mobile;
    Spinner spinner_mobiles;
    AIMAHandler(Context context) {

        this.context = context;
    }


    AIMAHandler(Context context, TextView text_infos, TextView text_sms, ListView list_mobile,Spinner spinner_mobiles) {
        this.context = context;
        this.text_infos = text_infos;
        this.text_sms = text_sms;
        this.list_mobile = list_mobile;
        this.spinner_mobiles=spinner_mobiles;
    }

    @Override
    public void handleMessage(Message msg) {


        if (msg.what == 1) {
            //获取用户信息

            String info_str=msg.obj.toString();

            String[] infos= info_str.split(";");

            text_infos.setText("会员名:" + infos[0] + "\n" + "积分:" + infos[1] + "\n" + "余额:" + infos[2]);

        } else if (msg.what == 2) {

            String str = msg.obj.toString();

            if (!str.equals("max_count_disable")) {

            } else {

                Toast.makeText(context, "超出可持有号码的最大值，请先释放！", Toast.LENGTH_LONG).show();
            }

        } else if (msg.what == 3) {
            //Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_LONG).show();
            text_sms.setText(msg.obj.toString());
        }
        else if (msg.what==3_1)
        {
            String str=msg.obj.toString();
            text_sms.setText(str);



            AIMA_mobile_infos aima_mobile_infos=new AIMA_mobile_infos();


            aima_mobile_infos.setPid("1012");
            aima_mobile_infos.setRecnum(str.substring(0,11));
            aima_mobile_infos.setContext(str.substring(12));
            Utils.addSMS(aima_mobile_infos);
            UserInfoActivity.reflushSMSList();



        }

        else if (msg.what == 4) {
            ArrayList<AIMA_mobile_infos> mobiles = (ArrayList<AIMA_mobile_infos>) msg.obj;

            List_mobile_adapter adapter=new List_mobile_adapter(context,mobiles);

            spinner_mobiles.setAdapter(adapter);

        }else if (msg.what == 5)
        {
            Toast.makeText(context,"成功释放"+ msg.obj.toString()+"个号码", Toast.LENGTH_LONG).show();
        }




        else {
            Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_LONG).show();
        }
    }


}



