package com.petter.mapsapplication.maps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petter.mapsapplication.manager.IMapManager
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivity : AppCompatActivity(), KoinComponent {
    private val mapManager: IMapManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configMap(savedInstanceState)
    }

    private fun configMap(savedInstanceState: Bundle?) {
        mapManager.configMap(this, savedInstanceState)
        val map = mapManager.fetchMapView()
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

}