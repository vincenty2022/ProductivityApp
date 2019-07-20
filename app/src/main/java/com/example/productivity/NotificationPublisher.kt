package com.example.productivity

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationPublisher: BroadcastReceiver() {
    var NOTIFICATION_ID: String = "notification-id"
    var NOTIFICATION: String = "notification"

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification: Notification = intent.getParcelableExtra(NOTIFICATION)
        val id: Int = intent.getIntExtra(NOTIFICATION_ID, 0)
        notificationManager.notify(id, notification)
    }
}