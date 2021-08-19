package com.petter.mapsapplication.notification

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import com.petter.mapsapplication.extensions.sendNotification
import com.petter.mapsapplication.manager.INotificationServiceListener
import org.koin.core.KoinComponent
import org.koin.core.inject

class NotificationServiceListener : INotificationServiceListener, KoinComponent {

    private val applicationContext: Context by inject()

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
        Log.d("Notification", "Refreshed token: $token")
    }
}