package com.testerkit;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.testerkit.agent.RotationAgent;
import com.testerkit.util.Log;

/**
 * Created by able on 2018/6/21.
 * 1、Anti-checked and Anti-killed
 * 2、https://www.cnblogs.com/travellife/p/Android-Notification-xiang-jie.html
 */

public class ServiceAntiKilled extends Service {

    RotationAgent rotation = new RotationAgent("rotationagent");
    private NotificationCompat.Builder builder;
    private static final int NOTIFICATION_ID = 0x1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(String channelId, String channelName) {
        NotificationChannel chan = new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        service.createNotificationChannel(chan);
        return channelId;
    }

    @Override
    public void onCreate() {
        Log.info("Service onCreate--->begin");
        super.onCreate();
        try {
            Intent notificationIntent = new Intent(this, MainActivity.class);
            String channelId = "";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                channelId = createNotificationChannel("monitor service", "Monitor Service");
            }
            builder = new NotificationCompat.Builder(this,channelId);
            builder.setContentIntent(PendingIntent.getActivity(this, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)) // 设置PendingIntent
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                    .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                    .setContentText("Running")
                    .setContentTitle("UIA TARGET")
                    .setWhen(System.currentTimeMillis());
            Notification notification = builder.build(); // 获取构建好的Notification
            // 放置在"正在运行"栏目中并且不可清除。
            notification.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR; ;
            startForeground(NOTIFICATION_ID, notification);

        }catch (Exception e){
            Log.error(e.getMessage(),e);
        }

        try {
            rotation.start();
            Log.info("start rotationagent--->end");
        }catch (Exception e){
            Log.error(e.getMessage(),e);
        }
    }

    @Override
    public void onDestroy() {
        Log.info("onDestroy");
        super.onDestroy();
        stopForeground(true);

        try {
            rotation.interrupt();
        }catch (Exception e){
            Log.error(e.getMessage(),e);
        }
    }
}
