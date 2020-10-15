package com.petter.mapsapplication.di

import com.petter.mapsapplication.manager.IDemoNotificationManager
import com.petter.mapsapplication.manager.IMapManager
import com.petter.mapsapplication.maps.DemoNotificationManager
import com.petter.mapsapplication.maps.MapsManager
import org.koin.dsl.module

val mapModule = module {
    single<IMapManager> { MapsManager() }
    single<IDemoNotificationManager> { DemoNotificationManager() }
}