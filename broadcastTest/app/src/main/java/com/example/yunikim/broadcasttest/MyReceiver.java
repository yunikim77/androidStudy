package com.example.yunikim.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public static final String MY_ACTION = "com.example.yunikim.action.ACTION_MY_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
            Toast.makeText(context, "전원이 연결되었지롱~", Toast.LENGTH_SHORT).show();
        }
        else if(MY_ACTION.equals(intent.getAction())) {
            Toast.makeText(context, "사용자 정의 브로드캐스트", Toast.LENGTH_SHORT).show();
        }
    }
}
