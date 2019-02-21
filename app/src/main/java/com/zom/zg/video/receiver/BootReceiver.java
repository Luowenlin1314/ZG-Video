package com.zom.zg.video.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zom.zg.video.MainActivity;


/**
 * 作者：Sky on 2018/3/5.
 * 用途：开机广播
 */

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("ZM","开机广播");
        Intent toDw = new Intent(context, MainActivity.class);
        toDw.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(toDw);
    }
}
