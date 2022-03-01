package com.zzq.paul_tools.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class OneProcessService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
        TestThread testThread = new TestThread();
        testThread.start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends Binder {
        public OneProcessService getService() {
            return OneProcessService.this;
        }
    }

    class TestThread extends Thread {
        @Override
        public void run() {
            super.run();

            while (true) {
                try {
                    Thread.sleep(2000);
                    Log.e("eee", "-----------运行中");
                } catch (InterruptedException e) {


                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("eee", "---------service  onDestroy00");
        Intent intent = new Intent();
        intent.setAction("com.restart.service");
        //发送广播
        sendBroadcast(intent);

//        unregisterReceiver(mReceiver);
    }
}
