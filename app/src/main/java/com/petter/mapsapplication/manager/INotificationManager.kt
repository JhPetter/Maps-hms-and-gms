package com.petter.mapsapplication.manager

import com.petter.mapsapplication.notification.Notification


interface INotificationManager {
    fun sendNotification(notification: Notification)
    fun sendRegistrationToServer(token: String?)
}