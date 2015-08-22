package com.example.sheng.event_checksms.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
}
