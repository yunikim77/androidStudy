package com.example.yunikim.serviceexam;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = MyService.class.getSimpleName();
    private Thread mThread;
    private int mCount = 0;

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mThread == null) {
            mThread = new Thread("My Thread") {
                @Override
                public void run() {
                    for(int i=0;i<5;i++) {
                        try {
                            mCount++;
                            Thread.sleep(1000);
                            Log.d("My Service", "서비스 동작 중 " + mCount);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                    super.run();
                }
            };
            mThread.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");

        if(mThread != null) {
            mThread.interrupt();
            mThread = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
