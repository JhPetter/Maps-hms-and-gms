package com.petter.mapsapplication.manager

import com.petter.mapsapplication.notification.Notification


interface INotificationService {
    fun sendNotification(notification: Notification)
    fun sendRegistrationToServer(token: String?)
}