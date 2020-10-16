package com.petter.mapsapplication.manager

import com.petter.mapsapplication.notification.Notification


interface INotificationServiceListener {
    fun sendNotification(notification: Notification)
    fun sendRegistrationToServer(token: String?)
}