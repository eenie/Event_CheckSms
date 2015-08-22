package com.example.sheng.event_checksms.ApplicationBase;

import com.example.sheng.event_checksms.Utile.Getsrcoce;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class AimaAPI {
    public static final String AIMA_HOST = "http://api.f02.cn/http.do";
    public static String TOKEN = "";
    public static String UID = "";
    public static String MOBILE = "";
    public static String SMS = "";
    //登录
    public static final String ACTION_LOGININ = "loginIn";
    //获取用户信息
    public static final String ACTION_GETUSERINFOS = "getUserInfos";
    //获取手机号码
    public static final String ACTION_GETMOBILENUM = "getMobilenum";
    //获取验证码并不再使用本号
    public static final String ACTION_GETVCODEANDRELEASEMBILE = "getVcodeAndReleaseMobile";
    //获取验证码并继续使用本号
    public static final String ACTION_GETVCODEANDHOLDMOBILENUM = "getVcodeAndHoldMobilenum";
    //加黑无用号码
    public static final String ACTION_ADDLGNORELIST = "addlgnoreList";
    //释放无用号码
    public static final String ACTION_CANCELSMSRECV = "cancelSMSRecv";
    //发短信
    public static final String ACTION_SENDSMS = "sendSms";
    //获取短信发送状态
    public static final String ACTION_GETSMSSTATUS = "getSmsStatus";
    //已获取号码列表
    public static final String ACTION_GETRECVINGINFO = "getRecvingInfo";


    //用户名密码错误
    public static final String EEROW_CODE_LOGIN_ERROR = "login_error";
    public static final String EEROW_CODE_ACCOUNT_IS_LOCKED = "account_is_locked";
    public static final String EEROW_CODE_NOT_RECEIVE = "not_receive";


    //用户登录
    public static String AIMA_login(String uid, String pwd) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        NameValuePair paramAction = new BasicNameValuePair("action", "loginIn");
        NameValuePair paramUser = new BasicNameValuePair("uid", uid);
        NameValuePair paramPassword = new BasicNameValuePair("pwd", pwd);
        params.add(paramAction);
        params.add(paramUser);
        params.add(paramPassword);
        String str = Getsrcoce.sroceByPost(AimaAPI.AIMA_HOST, params);
        return str;
    }

    //获取用户信息
    public static String AIMA_getinfos(String uid, String token) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        NameValuePair paramAction = new BasicNameValuePair("action", AimaAPI.ACTION_GETUSERINFOS);
        NameValuePair paramUser = new BasicNameValuePair("uid", uid);
        NameValuePair paramToken = new BasicNameValuePair("token", token);
        params.add(paramAction);
        params.add(paramUser);
        params.add(paramToken);
        return Getsrcoce.sroceByPost(AimaAPI.AIMA_HOST, params);
    }

    //获取手机号码
    public static String AIMA_getMobilenum(String pid, String uid, String token, String mobile, String size) {


        List<NameValuePair> params = new ArrayList<NameValuePair>();
        NameValuePair paramAction = new BasicNameValuePair("action", "getMobilenum");
        NameValuePair paramPid = new BasicNameValuePair("pid", pid);
        NameValuePair paramUid = new BasicNameValuePair("uid", uid);
        NameValuePair paramToken = new BasicNameValuePair("token", token);
        NameValuePair paramMobile = new BasicNameValuePair("mobile", mobile);
        NameValuePair paramSize = new BasicNameValuePair("size", size);
        params.add(paramAction);
        params.add(paramPid);
        params.add(paramUid);
        params.add(paramToken);
        params.add(paramMobile);
        params.add(paramSize);
        return Getsrcoce.sroceByPost(AimaAPI.AIMA_HOST, params);
    }


    //获取验证码,下次不再使用。
    public static String AIMA_getVcodeAndReleaseMobile(String mobile, String token, String uid) {


        List<NameValuePair> params = new ArrayList<NameValuePair>();
        NameValuePair paramAction = new BasicNameValuePair("action", "getVcodeAndReleaseMobile");
        NameValuePair paramMobile = new BasicNameValuePair("mobile", mobile);
        NameValuePair paramToken = new BasicNameValuePair("token", token);
        NameValuePair paramauthor_uid = new BasicNameValuePair("author_uid", "eenie01");
        NameValuePair paramUid = new BasicNameValuePair("uid", uid);
        params.add(paramAction);
        params.add(paramUid);
        params.add(paramToken);
        params.add(paramMobile);
        return Getsrcoce.sroceByPost(AimaAPI.AIMA_HOST, params);
    }


    public static String AIMA_getVcodeAndHoldMobilenum(String mobile, String token, String uid) {


        List<NameValuePair> params = new ArrayList<NameValuePair>();
        NameValuePair paramAction = new BasicNameValuePair("action", "getVcodeAndHoldMobilenum");
        NameValuePair paramMobile = new BasicNameValuePair("mobile", mobile);
        NameValuePair paramToken = new BasicNameValuePair("token", token);
        NameValuePair paramUid = new BasicNameValuePair("uid", uid);
        params.add(paramAction);
        params.add(paramUid);
        params.add(paramToken);
        params.add(paramMobile);
        return Getsrcoce.sroceByPost(AimaAPI.AIMA_HOST, params);
    }


    //获取已获取号码列表
    public static String AIMA_getRecvingInfo(String uid, String token, String pid) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        NameValuePair paramAction = new BasicNameValuePair("action", "getRecvingInfo");
        NameValuePair paramUid = new BasicNameValuePair("uid", uid);
        NameValuePair paramToken = new BasicNameValuePair("token", token);
        NameValuePair paramPid = new BasicNameValuePair("pid", pid);
        params.add(paramAction);
        params.add(paramUid);
        params.add(paramToken);
        params.add(paramPid);
        return Getsrcoce.sroceByPost(AimaAPI.AIMA_HOST, params);
    }

    //释放号码
    public static String AIMA_cancelSMSRecv(String uid, String token, String mobile) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        NameValuePair paramAction = new BasicNameValuePair("action", "cancelSMSRecv");

        NameValuePair paramUid = new BasicNameValuePair("uid", uid);
        NameValuePair paramToken = new BasicNameValuePair("token", token);
        NameValuePair paramMobile = new BasicNameValuePair("mobile", mobile);
        params.add(paramAction);
        params.add(paramUid);
        params.add(paramToken);
        params.add(paramMobile);
        return Getsrcoce.sroceByPost(AimaAPI.AIMA_HOST, params);
    }


}
