package com.petter.mapsapplication.maps

import android.util.Log
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage
import com.petter.mapsapplication.manager.INotificationServiceListener
import com.petter.mapsapplication.notification.Notification
import org.koin.core.KoinComponent
import org.koin.core.inject

class NotificationService : HmsMessageService(), KoinComponent {

    private val listener: INotificationServiceListener by inject()

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: ${remoteMessage?.from}")

        remoteMessage?.data?.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        remoteMessage?.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            listener.sendNotification(Notification(it.title ?: "", it.body ?: ""))
        }
    }


    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}