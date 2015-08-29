package com.example.sheng.event_checksms.Activity;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

import com.example.sheng.event_checksms.ApplicationBase.AimaAPI;
import com.example.sheng.event_checksms.R;
import com.example.sheng.event_checksms.Utile.Getsrcoce;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {


    Getsrcoce Net_tool;
    Button btn_login;
    EditText edit_user, edit_pwd;
    AIMAHandler aimaHandler;

    final static String DB_NAME = "didi_sms.db";
    final static String TABLE_NAME = "sms_history";
    ApplicationInfo appinfo;
    String packgeName;
    String dbPath;

  //  DBTool dbTool;


    private NotificationManager notificationManager = null;
    private Notification notification = null;
    private RemoteViews contentView = null;
    private boolean isRecording = false;
    private boolean needCancel = false;


    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Net_tool = new Getsrcoce();
        btn_login = (Button) findViewById(R.id.btn_login);
        edit_user = (EditText) findViewById(R.id.edit_user);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        aimaHandler=new AIMAHandler(MainActivity.this);
        initdatabase();
        dialog=new Dialog(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.setTitle("Loading...");
                dialog.show();


                new Thread() {

                    @Override
                    public void run() {
                        String str=AimaAPI.AIMA_login(edit_user.getText().toString(),edit_pwd.getText().toString());
                        if (str.indexOf("|")>0)
                        {
                            AimaAPI.UID = str.substring(0, str.lastIndexOf("|"));
                            AimaAPI.TOKEN = str.substring(str.lastIndexOf("|") + 1, str.length());
                            startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
                            dialog.dismiss();
                            finish();
                        }
                        else {
                            Message msg=new Message();
                            msg.obj=str;
                            aimaHandler.sendMessage(msg);
                        }
                    }
                }.start();


              //  createRecordingNotification();
            }
        });


    }

    private void initdatabase() {
        packgeName = getPackageName();
       //System.out.println(packgeName);
        try {
            appinfo = getPackageManager().getApplicationInfo(packgeName,
                    PackageManager.GET_META_DATA);


            String dbDir = appinfo.dataDir + File.separator + "database";

            dbPath = dbDir + File.separator + DB_NAME;
            //System.out.println("--dbpath=" + dbPath);
            DBTool.databasePath=dbPath;
            File dbdir2 = new File(dbDir);
            if (!dbdir2.exists()) {
                dbdir2.mkdir();
            }

            InputStream ins = getAssets().open(DB_NAME);
            File dbFile = new File(dbPath);
            if (!dbFile.exists())

            {
                dbFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(dbFile);
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = ins.read(buffer)) != -1) {

                    fos.write(buffer, 0, count);

                }

                fos.close();
                ins.close();


            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public  void createRecordingNotification() {
    //创建通知
        notificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Resources res = this.getResources();
        long when = System.currentTimeMillis();


        notification =new Notification( );



        notification.flags = Notification.FLAG_ONGOING_EVENT;

        contentView = new RemoteViews(this.getPackageName(),
                R.layout.notify_view);
        contentView.setImageViewResource(R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        contentView.setTextViewText(R.id.text_notify_mobile, "sdsdssd");//录音中
        contentView.setTextViewText(R.id.text_notify_status, "00:00");//录音时长

        notification.contentView = contentView;//设置通知样式为自定义的样式

//        Intent notificationIntent =new Intent();
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//        PendingIntent contentItent = PendingIntent.getActivity(this, 0,
//                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        notification.contentIntent = contentItent;
        notificationManager.notify(0, notification);





 //       NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("My notification").setContentText("Hello World!");
//        Intent resultIntent = new Intent(this, MainActivity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(MainActivity.class);
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//        mBuilder.setContentIntent(resultPendingIntent);

//        notification =new Notification( );
//        contentView=new RemoteViews(this.getPackageName(), R.layout.notify_view);
//        contentView.setTextViewText(R.id.text_notify_mobile, "00:00");
//        notification.contentView=contentView;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, notification);
    }

}
