package com.example.foregroundservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;;

public class MainActivity extends AppCompatActivity {
    private Button changeButtonText;
    MyReceiver myReceiver;
    int myReceiverCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("MainActivity-CLASS-onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ExampleService.MY_ACTION);
        registerReceiver(myReceiver, intentFilter);

        Intent serviceIntent = new Intent(this, ExampleService.class);

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService(View v){
        System.out.println("MainActivity-CLASS-stopService");

        changeButtonText = findViewById(R.id.button);
        changeButtonText.setText("Exit");

        Intent serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
        unregisterReceiver(myReceiver);

    }
    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            System.out.println("MainActivity-CLASS-MyReceiver-CLASS-onReceive count: " + myReceiverCounter);

            int datapassed = arg1.getIntExtra("DATAPASSED", 0);

            TextView textView = findViewById(R.id.textview);
            textView.append(datapassed+",");

            myReceiverCounter++;
        }

    }

    public void onStart()
    {
        System.out.println("MainActivity-CLASS-onStart");
        super.onStart();
    }

    public void onRestart()
    {
        System.out.println("MainActivity-CLASS-onRestart");
        super.onRestart();
    }
    public void onResume()
    {
        System.out.println("MainActivity-CLASS-onResume");
        super.onResume();
    }
    public void onPause()
    {
        System.out.println("MainActivity-CLASS-onPause");
        super.onPause();
    }
    public void onStop()
    {
        System.out.println("MainActivity-CLASS-onStop");
        super.onStop();
    }
    public void onDestroy()
    {
        System.out.println("MainActivity-CLASS-onDestroy");
        Intent serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
        unregisterReceiver(myReceiver);
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }

}