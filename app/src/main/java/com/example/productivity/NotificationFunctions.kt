package com.example.productivity

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

fun Notification(
    context: Context,
    CHANNEL_ID: String,
    icon: Int,
    title: String,
    text: String,
    priority: Int                                   // input importance as value from 0-2: Low, Default, High
): Notification {
    val pInt = when (priority) {
        0 -> NotificationCompat.PRIORITY_LOW
        1 -> NotificationCompat.PRIORITY_DEFAULT
        2 -> NotificationCompat.PRIORITY_HIGH
        else -> throw IndexOutOfBoundsException("Importance must be in range [0, 2]")
    }

    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(icon)
        .setContentTitle(title)
        .setContentText(text)
        .setPriority(pInt)

    return builder.build()
}

fun schedNotification(
    context: Context,
    notification: Notification,
    id: Int,
    hourDelay: Int = 0,
    minuteDelay: Int = 0
) {
    val notificationIntent = Intent(context, NotificationPublisher::class.java)
    notificationIntent.putExtra("notification-id", id)
    notificationIntent.putExtra("notification", notification)
    notificationIntent.setAction("foo")             // dummy action to prevent dropping of extras
    val pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT)

    val alarmTime: Long = System.currentTimeMillis() + timetoMillis(hourDelay, minuteDelay)   // modify alarmTime to accept input from settings. probably end up changing to a var
    val alarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarm.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent)

}

fun createNotificationChannel(
    context: Context,
    CHANNEL_ID: String,
    CHANNEL_NAME: String,
    DESCRIPTION: String,
    importance: Int                                    // input importance as value from 0-2: Low, Default, High
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val importInt = when (importance) {
            0 -> NotificationManager.IMPORTANCE_LOW
            1 -> NotificationManager.IMPORTANCE_DEFAULT
            2 -> NotificationManager.IMPORTANCE_HIGH
            else -> throw IndexOutOfBoundsException("Importance must be in range [0, 2]")
        }

        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importInt).apply{
            description = DESCRIPTION
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

private fun timetoMillis(hours: Int = 0, minutes: Int = 0) = minutes*60*1000