package com.example.foregroundservice;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static com.example.foregroundservice.App.CHANNEL_ID;


public class ExampleService extends Service {
    final static String MY_ACTION = "MY_ACTION";

    @Override
    public void onCreate() {
        System.out.println("onCreate-SERVICE");
        MyThread myThread = new MyThread();
        myThread.start();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand-SERVICE");

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText("Service is started")
                .setSmallIcon(R.drawable.ic_android)
                .build();

        startForeground(1, notification);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy-SERVICE");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind-SERVICE");
        return null;
    }

    public class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println("MyThread-CLASS-run-SERVICE");
            // TODO Auto-generated method stub
            for(int i=0; i<10000; i++){
                try {
                    Thread.sleep(1000);
                    Intent intent = new Intent();
                    intent.setAction(MY_ACTION);

                    intent.putExtra("DATAPASSED", i);

                    sendBroadcast(intent);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // If you want to stop after finishing or you need to manually stop it from the button!
            //stopSelf();
        }

    }
}
