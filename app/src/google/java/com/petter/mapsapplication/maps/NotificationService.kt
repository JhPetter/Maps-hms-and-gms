package com.petter.mapsapplication.maps


import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.petter.mapsapplication.manager.INotificationServiceListener
import com.petter.mapsapplication.notification.Notification
import org.koin.core.KoinComponent
import org.koin.core.inject

class NotificationService : FirebaseMessagingService(), KoinComponent {

    private val listener: INotificationServiceListener by inject()

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "From: ${remoteMessage?.from}")

        remoteMessage?.data?.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        remoteMessage?.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            listener.sendNotification(Notification(it.title ?: "", it.body ?: ""))
        }
    }

    override fun onNewToken(token: String?) {
        listener.sendRegistrationToServer(token)
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }

}