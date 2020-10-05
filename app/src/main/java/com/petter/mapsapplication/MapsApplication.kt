package com.petter.mapsapplication

import android.app.Application
import com.petter.mapsapplication.di.mapModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MapsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MapsApplication)
            modules(
                listOf(
                    mapModule
                )
            )
        }
    }
}