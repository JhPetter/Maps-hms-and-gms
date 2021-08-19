package com.petter.mapsapplication.maps

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petter.mapsapplication.R
import com.petter.mapsapplication.manager.IDemoNotificationManager
import com.petter.mapsapplication.manager.IMapManager
import com.petter.mapsapplication.manager.IMapReadyListener
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivity : AppCompatActivity(), KoinComponent, IMapReadyListener {

    private val TOPIC = "breakfast"

    private val mapManager: IMapManager by inject()
    private val notificationManager: IDemoNotificationManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configMap(savedInstanceState)
        configNotification(this)
    }

    private fun configNotification(context: Context) {

        createChannel(
            getString(R.string.notification_channel_id),
            getString(R.string.notification_channel_name)
        )

        createChannel(
            getString(R.string.demo_notification_channel_id),
            getString(R.string.demo_notification_channel_name)
        )
        notificationManager.subscribeTopic(context, TOPIC)
    }

    private fun configMap(savedInstanceState: Bundle?) {
        mapManager.configMap(this, savedInstanceState)
        val map = mapManager.fetchMapView()
        mapManager.setOnMapReadyListener(this)
        setContentView(map)
    }

    override fun onStart() {
        super.onStart()
        mapManager.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapManager.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapManager.onDestroy()
    }

    override fun onPause() {
        mapManager.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapManager.onResume()
    }


    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply { setShowBadge(false) }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Time for breakfast"

            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    override fun onMapReady() {
        mapManager.setPositionWithMarket(48.893478, 2.334595, 15f)
    }
}