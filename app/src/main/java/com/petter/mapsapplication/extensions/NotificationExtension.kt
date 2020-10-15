package com.petter.mapsapplication.extensions

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.petter.mapsapplication.R
import com.petter.mapsapplication.maps.MainActivity
import com.petter.mapsapplication.notification.Notification
import com.petter.mapsapplication.receiver.SnoozeReceiver

private const val NOTIFICATION_ID = 0
private const val REQUEST_CODE = 0
private const val FLAGS = 0

fun NotificationManager.sendNotification(notification: Notification, applicationContext: Context) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val image = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.ic_baseline_notifications_24
    )
    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(image)
        .bigLargeIcon(null)

    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(
        applicationContext,
        REQUEST_CODE,
        snoozeIntent,
        FLAGS
    )

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.notification_channel_id)
    ).setSmallIcon(R.drawable.ic_baseline_notifications_24)
        .setContentTitle(
            applicationContext
                .getString(R.string.notification_title)
        )
        .setContentText(notification.body)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setStyle(bigPicStyle)
        .setLargeIcon(image)
        .addAction(
            R.drawable.ic_launcher_foreground,
            applicationContext.getString(R.string.notification_title),
            snoozePendingIntent
        )
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    notify(NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}