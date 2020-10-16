package com.petter.mapsapplication.maps

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage
import com.petter.mapsapplication.extensions.sendNotification
import com.petter.mapsapplication.manager.INotificationService
import com.petter.mapsapplication.notification.Notification

class NotificationService : INotificationService, HmsMessageService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "From: ${remoteMessage?.from}")

        remoteMessage?.data?.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        remoteMessage?.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(Notification(it.title ?: "", it.body ?: ""))
        }
    }


    override fun sendNotification(notification: Notification) {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
            notification,
            applicationContext
        )
    }

    override fun sendRegistrationToServer(token: String?) {
        Log.d(TAG, "Refreshed token: $token")
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}