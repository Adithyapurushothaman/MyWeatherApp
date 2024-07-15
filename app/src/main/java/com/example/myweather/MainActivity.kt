package com.example.myweather


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.core.app.ActivityCompat
import com.example.myweather.ui.theme.composable.getMyweatherAppScreen
import com.example.myweather.ui.theme.composable.weatherRepo
import com.example.myweather.ui.theme.composable.weatherRepo.Companion.weatherRepoObj
import com.google.android.gms.location.LocationServices


class MainActivity : ComponentActivity() {


//    private lateinit var data: DataStore<Preferences>
//    private val dataStore = UserPreference(this)


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getCurrentLocation()
        setContent {
            getMyweatherAppScreen()
        }

    }

    private fun getCurrentLocation(): Location? {


        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {

                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {

                } else -> {

            }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
        {


            val locationClient = LocationServices.getFusedLocationProviderClient(applicationContext)
            weatherRepoObj.getWeatherByCity("Kochi")
            locationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val latitude = it.latitude
                    val longitude = it.longitude
                    weatherRepoObj.getWeather(latitude!!,longitude!!)
                }

            }
        }
        return null
    }
}








