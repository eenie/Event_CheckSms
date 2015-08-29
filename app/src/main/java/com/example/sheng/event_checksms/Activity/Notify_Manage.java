package com.example.sheng.event_checksms.Activity;

/**
 * Created by sheng on 2015/8/23.
 */

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.RemoteViews;

import com.example.sheng.event_checksms.R;

public class Notify_Manage extends Activity{


    private NotificationManager notificationManager = null;
    private Notification notification = null;
    private RemoteViews contentView = null;
    private boolean isRecording = false;
    private boolean needCancel = false;

    public  void createRecordingNotification() {//创建通知
        notificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Resources res = this.getResources();
        long when = System.currentTimeMillis();


        notification =new Notification( );



        notification.flags = Notification.FLAG_ONGOING_EVENT;

        contentView = new RemoteViews(this.getPackageName(),
                R.layout.notify_view);
//        contentView.setImageViewResource(R.id.notify_image, R.drawable.ic_launcher_soundrecorder);
//        contentView.setTextViewText(R.id.notify_name,
//                res.getString(R.string.wld_recording_now));//录音中
//        contentView.setTextViewText(R.id.notify_time, "00:00");//录音时长

        notification.contentView = contentView;//设置通知样式为自定义的样式

        Intent notificationIntent =new Intent();
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent contentItent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        notification.contentIntent = contentItent;
        notificationManager.notify(0, notification);
    }

    public void updateRecordingNotification(String time) {//更新通知
        if (notificationManager != null) {
            Resources res = this.getResources();
            notification.flags = Notification.FLAG_NO_CLEAR;
//            contentView.setTextViewText(R.id.notify_name, res.getString(R.string.wld_recording_now));
//            contentView.setTextViewText(R.id.notify_time, time);
            notificationManager.notify(0, notification);
        }
    }

    public void clearRecordingNotification() {//清除通知
        if (notificationManager != null) {
            notificationManager.cancel(0);
            notificationManager = null;
        }
    }
}
