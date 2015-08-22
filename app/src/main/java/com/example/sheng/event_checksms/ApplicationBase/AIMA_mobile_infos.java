package com.example.sheng.event_checksms.ApplicationBase;

/**
 * Created by sheng on 2015/8/21.
 */
public class AIMA_mobile_infos {

    private String Pid;
    private String Recnum;
    private String Timeout;
    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getRecnum() {
        return Recnum;
    }

    public void setRecnum(String recnum) {
        Recnum = recnum;
    }

    public String getTimeout() {
        return Timeout;
    }

    public void setTimeout(String timeout) {
        Timeout = timeout;
    }
}
