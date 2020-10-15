package com.petter.mapsapplication.maps

import android.content.Context
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import com.petter.mapsapplication.R
import com.petter.mapsapplication.manager.IDemoNotificationManager

class DemoNotificationManager : IDemoNotificationManager {
    override fun subscribeTopic(context: Context, topic: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
            .addOnCompleteListener { task ->
                var msg = context.getString(R.string.message_subscribed)
                if (!task.isSuccessful) {
                    msg = context.getString(R.string.message_subscribe_failed)
                }
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
    }
}