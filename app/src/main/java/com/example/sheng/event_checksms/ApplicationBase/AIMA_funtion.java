package com.example.sheng.event_checksms.ApplicationBase;

import android.os.Message;
import android.widget.Button;

import com.example.sheng.event_checksms.Activity.AIMAHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by sheng on 2015/8/21.
 */
public class AIMA_funtion {
    AIMAHandler aimaHandler;

    public AIMA_funtion(AIMAHandler aimaHandler) {
        this.aimaHandler = aimaHandler;
    }

    public void getUserInfo() {
        new Thread() {
            @Override
            public void run() {
                Message msg_info = new Message();
                msg_info.what = 1;
                msg_info.obj = AimaAPI.AIMA_getinfos(AimaAPI.UID, AimaAPI.TOKEN);
                aimaHandler.sendMessage(msg_info);
            }
        }.start();
    }


    public void login() {

    }


    public void getMobile() {
        new Thread() {
            @Override
            public void run() {
                AimaAPI.MOBILE = AimaAPI.AIMA_getMobilenum("1012", AimaAPI.UID, AimaAPI.TOKEN, "", "1");
                Message msg_mobile = new Message();
                msg_mobile.what = 2;
                msg_mobile.obj = AimaAPI.MOBILE;
                getMobileNums();
                aimaHandler.sendMessage(msg_mobile);
            }
        }.start();
    }


    public void getSMS(final Button btn_flag, final String btn_context, final int sp_time) {
        new Thread() {
            @Override
            public void run() {
                AimaAPI.SMS = AimaAPI.EEROW_CODE_NOT_RECEIVE;
                while (btn_flag.getText().equals(btn_context)) {
                    AimaAPI.SMS = AimaAPI.AIMA_getVcodeAndReleaseMobile(AimaAPI.MOBILE, AimaAPI.TOKEN, AimaAPI.UID);
                    if (AimaAPI.SMS.equals(AimaAPI.EEROW_CODE_NOT_RECEIVE) || AimaAPI.SMS.equals("parameter_error")|| AimaAPI.SMS.equals("not_found_moblie")) {


                        try {

                            Message msg_error;
                            for (int i = 0; i < 5; i++) {
                                msg_error = new Message();
                                msg_error.what = 3;
                                msg_error.obj = "没有收到短信"+"("+AimaAPI.SMS+")," + (sp_time - i) + "秒后重新获取...";
                                aimaHandler.sendMessage(msg_error);
                                Thread.sleep(1000);
                            }
                            msg_error = new Message();
                            msg_error.what = 3;
                            msg_error.obj = "正在获取...";
                            aimaHandler.sendMessage(msg_error);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Message msg_sms = new Message();
                        msg_sms.what = 3_1;
                        msg_sms.obj = AimaAPI.SMS;
                        getUserInfo();
                        aimaHandler.sendMessage(msg_sms);
                        break;
                    }
                }


            }
        }.start();
    }

    public void getMobileNums() {
        new Thread() {
            @Override
            public void run() {
                StringBuffer stringBuffer = new StringBuffer(AimaAPI.AIMA_getRecvingInfo(AimaAPI.UID, AimaAPI.TOKEN, "1012"));
                int len = stringBuffer.indexOf("Start_time");
                while (len > 0) {
                    int end = len;
                    while (!(stringBuffer.toString().charAt(end) == '}')) {
                        end++;
                    }
                    stringBuffer.delete(len - 2, end);
                    len = stringBuffer.indexOf("Start_time");
                }
                try {
                    JSONArray jsonArray = new JSONArray(stringBuffer.toString());

                    ArrayList<AIMA_mobile_infos> mobiles = new ArrayList<AIMA_mobile_infos>();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        AIMA_mobile_infos mobile_infos = new AIMA_mobile_infos();
                        mobile_infos.setPid(jsonArray.getJSONObject(i).getString("Pid"));
                        mobile_infos.setRecnum(jsonArray.getJSONObject(i).getString("Recnum"));
                        mobile_infos.setTimeout(jsonArray.getJSONObject(i).getString("Timeout"));
                        mobiles.add(mobile_infos);
                    }
                    Message msg_nums = new Message();
                    msg_nums.what = 4;
                    msg_nums.obj = mobiles;
                    aimaHandler.sendMessage(msg_nums);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void giveUpNum() {
        new Thread() {
            @Override
            public void run() {
                String str = AimaAPI.AIMA_cancelSMSRecv(AimaAPI.UID, AimaAPI.TOKEN, AimaAPI.MOBILE);
                Message message_giveup = new Message();
                message_giveup.what = 5;
                message_giveup.obj = str;
                try {
                    Thread.sleep(1500);
                    getMobileNums();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                aimaHandler.sendMessage(message_giveup);
            }
        }.start();
    }


    public void getMobile(final String mobile) {
        new Thread() {
            @Override
            public void run() {
                AimaAPI.MOBILE = AimaAPI.AIMA_getMobilenum("1012", AimaAPI.UID, AimaAPI.TOKEN, mobile, "1");
                Message msg_mobile = new Message();
                msg_mobile.what = 2;
                msg_mobile.obj = AimaAPI.MOBILE;
                getMobileNums();
                aimaHandler.sendMessage(msg_mobile);
            }
        }.start();
    }


}
