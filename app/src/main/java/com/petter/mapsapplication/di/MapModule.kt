package com.petter.mapsapplication.di

import com.petter.mapsapplication.manager.IDemoNotificationManager
import com.petter.mapsapplication.manager.IMapManager
import com.petter.mapsapplication.manager.INotificationServiceListener
import com.petter.mapsapplication.maps.DemoNotificationManager
import com.petter.mapsapplication.maps.MapsManager
import com.petter.mapsapplication.notification.NotificationServiceListener
import org.koin.dsl.module

val mapModule = module {
    single<IMapManager> { MapsManager() }
    single<IDemoNotificationManager> { DemoNotificationManager() }
    single<INotificationServiceListener> { NotificationServiceListener() }
}