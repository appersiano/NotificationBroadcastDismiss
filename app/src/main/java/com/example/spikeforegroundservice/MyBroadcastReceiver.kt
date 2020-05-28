package com.example.spikeforegroundservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

private const val TAG = "ZehusBroadcastReceiver"

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "onReceive: ")
        Toast.makeText(context, "Received intent!", Toast.LENGTH_LONG).show()

        context.stopService(
            Intent(
                context,
                MyForegroundService::class.java
            )
        )
    }
}