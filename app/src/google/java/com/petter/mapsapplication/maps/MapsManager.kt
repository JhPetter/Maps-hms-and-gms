package com.petter.mapsapplication.maps

import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.petter.mapsapplication.manager.IMapManager
import com.petter.mapsapplication.manager.IMapReadyListener

class MapsManager : IMapManager, OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap
    private var onIMapReadyListener: IMapReadyListener? = null
    override fun configMap(context: Context, savedInstanceState: Bundle?) {
        val options = GoogleMapOptions().apply {
            compassEnabled(true)
            zoomControlsEnabled(false)
            scrollGesturesEnabled(false)
            zoomGesturesEnabled(false)
        }
        mapView = MapView(context, options).apply {
            onCreate(savedInstanceState)
            getMapAsync(this@MapsManager)
        }
    }

    override fun fetchMapView(): View = mapView
    override fun setOnMapReadyListener(onIMapReadyListener: IMapReadyListener) {
        this.onIMapReadyListener = onIMapReadyListener
    }

    override fun setPositionWithMarket(lat: Double, long: Double, zoom: Float) {
        val place = LatLng(lat, long)
        with(map) {
            animateCamera(CameraUpdateFactory.newLatLngZoom(place, zoom))
            addMarker(MarkerOptions().position(place))
        }
    }

    override fun onStart() = mapView.onStart()

    override fun onStop() = mapView.onStop()

    override fun onDestroy() = mapView.onDestroy()

    override fun onPause() = mapView.onPause()

    override fun onResume() = mapView.onResume()

    override fun onLowMemory() = mapView.onLowMemory()
    override fun onMapReady(map: GoogleMap?) {
        map?.let { this.map = map }
        onIMapReadyListener?.onMapReady()

    }
}