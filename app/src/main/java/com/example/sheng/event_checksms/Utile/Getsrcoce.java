package com.example.sheng.event_checksms.Utile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Getsrcoce {


    public String sroceByPost(String url) {
        HttpPost httpPost = new HttpPost(url);
        try {
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return "";

    }


    public static String sroceByPost(String url, List<NameValuePair> params) {
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";

    }


    public String sroceByGet(String url) {

        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpget);
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return "";

    }


    public void connByGET(String url) {
        try {
            //声明URL
            URL web_url = new URL(url);
            //打开Url
            HttpURLConnection huc = (HttpURLConnection) web_url.openConnection();
            //设置访问方式GET、POST
            huc.setRequestMethod("GET");
            //获得流
            huc.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            StringBuffer SB = new StringBuffer();
            String str = reader.readLine();

            while (str != null) {
                SB.append(str);
                str = reader.readLine();
            }
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

/*
* ceshi
* */
    public void connByPOST(String url, HashMap<String, String> params) {
        try {
            URL web_url = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) web_url.openConnection();
            huc.setRequestMethod("POST");
            huc.setDoInput(true);
            huc.setDoOutput(true);
            huc.setUseCaches(false);
            StringBuffer param = new StringBuffer();

            for (String k : (String[]) params.keySet().toArray()) {
                param.append(k + "=" + params.get(k));
            }
            huc.getOutputStream().write(param.toString().getBytes());
            BufferedReader reader = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            String context;
            StringBuffer contextbuffer = new StringBuffer();

            while ((context = reader.readLine()) != null) {
                contextbuffer.append(context);
            }
            reader.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




    public void connByPOST(String url, String params,int timeout) {
        try {
            URL web_url = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) web_url.openConnection();
            huc.setConnectTimeout(timeout);

            huc.setRequestMethod("POST");
            huc.setDoInput(true);
            huc.setDoOutput(true);
            huc.setUseCaches(false);
            huc.getOutputStream().write(params.getBytes());
            BufferedReader reader = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            String context;
            StringBuffer contextbuffer = new StringBuffer();
            while ((context = reader.readLine()) != null) {
                contextbuffer.append(context);
            }
            reader.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
