package com.testerkit;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by able on 2018/6/21.
 * 1、Anti-checked and Anti-killed
 * 2、https://www.cnblogs.com/travellife/p/Android-Notification-xiang-jie.html
 */

public class ServiceAntiKilled extends Service {

   private final static String TAG ="[TARGET]--->";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG,"onCreate");
        super.onCreate();
        try {
            Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
            Intent nfIntent = new Intent(this, MainActivity.class);
            builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                    .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                    .setContentText("Running")
                    .setContentTitle("TesterKit");
            Notification notification = builder.build(); // 获取构建好的Notification
            // 放置在"正在运行"栏目中并且不可清除。
            notification.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR; ;
            startForeground(110, notification);
        }catch (Exception e){
            Log.e(TAG,e.getMessage(),e);
        }

    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy");
        super.onDestroy();
        stopForeground(true);
    }
}
