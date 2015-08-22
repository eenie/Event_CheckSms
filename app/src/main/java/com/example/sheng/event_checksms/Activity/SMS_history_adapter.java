package com.example.sheng.event_checksms.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sheng.event_checksms.ApplicationBase.AIMA_mobile_infos;
import com.example.sheng.event_checksms.R;

import java.util.ArrayList;

/**
 * Created by sheng on 2015/8/22.
 */
public class SMS_history_adapter extends BaseAdapter{

    ArrayList<AIMA_mobile_infos> mobiles;
    Context context;
    private LayoutInflater inflater;



    SMS_history_adapter(Context context, ArrayList<AIMA_mobile_infos> mobiles) {
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

        View view = inflater.inflate(R.layout.sms_history_list_item, null);
        Holder_SMS holder = new Holder_SMS();
        holder.text_pid = (TextView) view.findViewById(R.id.text_history_pid);
        holder.text_mobile = (TextView) view.findViewById(R.id.text_history_mobile);
        holder.text_context=(TextView)view.findViewById(R.id.text_history_context);


        holder.text_pid.setText("项目编号：" + mobiles.get(position).getPid());
        holder.text_mobile.setText("手机号码：" + mobiles.get(position).getRecnum());
        holder.text_context.setText("短信内容：\n" + mobiles.get(position).getContext());




        return view;
    }



}


class Holder_SMS {
    TextView text_pid, text_mobile, text_time,text_context;
    int flag=0;
}