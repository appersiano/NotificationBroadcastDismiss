package com.example.spikeforegroundservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyForegroundService : Service() {

    val TAG = "MyForegroundService"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand: ")
        super.onStartCommand(intent, flags, startId)

        Log.i(TAG, "onStartCommand: ")

        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()

        val name = "MyChannelName"
        val descriptionText = "MyChannelDescription"
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel =
            NotificationChannel("NOTIFICATION_BASE_CHANNEL_ID", name, importance).apply {
                description = descriptionText
                setShowBadge(false)
            }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            applicationContext?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        val intent = Intent(applicationContext, MyBroadcastReceiver::class.java)
        intent.setAction("stopAction")

        val stopAction =
            NotificationCompat.Action.Builder(
                0,
                "STOP",
                PendingIntent.getBroadcast(
                    applicationContext,
                    123,
                    intent,
                    PendingIntent.FLAG_CANCEL_CURRENT
                )
            )

        val builder = NotificationCompat.Builder(this, "NOTIFICATION_BASE_CHANNEL_ID")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(getString(R.string.app_name))
            .setContentText("Test Foreground Service")
            .setAutoCancel(false)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(stopAction.build())

        startForeground(123, builder?.build())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }
}
