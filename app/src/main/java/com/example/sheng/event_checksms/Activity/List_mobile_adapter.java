package com.example.sheng.event_checksms.Activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sheng.event_checksms.ApplicationBase.AIMA_mobile_infos;
import com.example.sheng.event_checksms.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Created by sheng on 2015/8/21.
 */
public class List_mobile_adapter extends BaseAdapter {

    ArrayList<AIMA_mobile_infos> mobiles;
    Context context;
    private LayoutInflater inflater;
    Thread time_thread;


    List_mobile_adapter(Context context, ArrayList<AIMA_mobile_infos> mobiles) {
        this.mobiles = mobiles;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mobiles.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.mobile_list_intem, null);
        Holder holder = new Holder();
        final Time_handler time_handler = new Time_handler(holder);
        holder.text_pid = (TextView) view.findViewById(R.id.text_pid);
        holder.text_mobile = (TextView) view.findViewById(R.id.text_mobile);
        holder.text_time = (TextView) view.findViewById(R.id.text_time);


        holder.text_pid.setText("项目编号：" + mobiles.get(position).getPid());
        holder.text_mobile.setText("手机号码：" + mobiles.get(position).getRecnum());
        holder.text_time.setText("有效时间：" + mobiles.get(position).getTimeout() + "秒");
        final int time = Integer.parseInt(mobiles.get(position).getTimeout());


        time_thread = new Thread() {
            @Override
            public void run() {
                int mtime = time;


                while (mtime > 0  || time_handler.holder.flag==1) {
                    time_handler.sendEmptyMessage(mtime);
                    mtime--;
                    System.out.println(mtime);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }


            @Override
            public void destroy() {
                System.out.println("death");
            }
        };

       // time_thread.start();

        return view;
    }


}


class Holder {
    TextView text_pid, text_mobile, text_time;
    int flag=0;
}

class Time_handler extends Handler {
    Holder holder;
    Time_handler(Holder holder) {
        this.holder = holder;
    }
    @Override
    public void handleMessage(Message msg) {
        holder.text_time.setText("有效时间：" + msg.what + "秒");
        System.out.println(holder.text_time.getVisibility());
    }
}