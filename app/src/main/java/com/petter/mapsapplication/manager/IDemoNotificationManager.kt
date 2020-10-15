package com.petter.mapsapplication.manager

import android.content.Context

interface IDemoNotificationManager {
    fun subscribeTopic(context: Context, topic: String)
}