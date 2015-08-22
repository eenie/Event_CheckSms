package com.example.sheng.event_checksms.Utile;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Getsrcoce
{



public String sroceByPost(String url)
{
	HttpPost httpPost=new HttpPost(url);
	try
	{
		HttpResponse httpResponse=new DefaultHttpClient().execute(httpPost);
		return EntityUtils.toString(httpResponse.getEntity());
	} catch (ClientProtocolException e)
	{
		e.printStackTrace();
	} catch (IOException e)
	{
		e.printStackTrace();

}
	return "";

}



public static String sroceByPost(String url,List<NameValuePair> params)
{
	HttpPost httpPost=new HttpPost(url);
	try
	{
		httpPost.setEntity(new UrlEncodedFormEntity(params));
		HttpResponse httpResponse=new DefaultHttpClient().execute(httpPost);
		return EntityUtils.toString(httpResponse.getEntity());
	} catch (ClientProtocolException e)
	{
		e.printStackTrace();
	} catch (IOException e)
	{
		e.printStackTrace();

}
	return "0";

}


public String sroceByGet(String url)
{

	HttpGet httpget=new HttpGet(url);
	try
	{
		HttpResponse httpResponse=new DefaultHttpClient().execute(httpget);
		return EntityUtils.toString(httpResponse.getEntity());
	} catch (ClientProtocolException e)
	{
		e.printStackTrace();
	} catch (IOException e)
	{
		e.printStackTrace();

}
	return "";

}



}
