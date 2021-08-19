package com.petter.mapsapplication.manager

import android.content.Context
import android.os.Bundle
import android.view.View

interface IMapManager {
    fun configMap(context: Context, savedInstanceState: Bundle? = null)
    fun fetchMapView(): View

    fun setOnMapReadyListener(onIMapReadyListener: IMapReadyListener)

    fun setPositionWithMarket(lat: Double, long: Double, zoom: Float)

    fun onStart()
    fun onStop()
    fun onDestroy()
    fun onPause()
    fun onResume()
    fun onLowMemory()
}
