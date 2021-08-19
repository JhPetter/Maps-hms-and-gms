package com.petter.mapsapplication.maps

import android.content.Context
import android.os.Bundle
import android.view.View
import com.huawei.hms.maps.*
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.MarkerOptions
import com.petter.mapsapplication.manager.IMapManager
import com.petter.mapsapplication.manager.IMapReadyListener

class MapsManager : IMapManager, OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var map: HuaweiMap
    private var onIMapReadyListener: IMapReadyListener? = null

    override fun configMap(context: Context, savedInstanceState: Bundle?) {
        val options = HuaweiMapOptions().apply {
            compassEnabled(true)
            zoomControlsEnabled(false)
            scrollGesturesEnabled(false)
            zoomGesturesEnabled(false)
        }
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle =
                savedInstanceState.getBundle("MAPVIEW_BUNDLE_KEY")
        }
        mapView = MapView(context, options).apply {
            onCreate(mapViewBundle)
            getMapAsync(this@MapsManager)
        }
    }

    override fun fetchMapView(): View = mapView
    override fun setOnMapReadyListener(onIMapReadyListener: IMapReadyListener) {
        this.onIMapReadyListener = onIMapReadyListener
    }

    override fun onStart() = mapView.onStart()

    override fun onStop() = mapView.onStop()

    override fun onDestroy() = mapView.onDestroy()

    override fun onPause() = mapView.onPause()

    override fun onResume() = mapView.onResume()

    override fun onLowMemory() = mapView.onLowMemory()

    override fun onMapReady(map: HuaweiMap?) {
        map?.let { this.map = map }
        onIMapReadyListener?.onMapReady()
    }

    override fun setPositionWithMarket(lat: Double, long: Double, zoom: Float) {
        val place = LatLng(lat, long)
        with(map) {
            animateCamera(CameraUpdateFactory.newLatLngZoom(place, zoom))
            addMarker(MarkerOptions().position(place))
        }
    }
}