package com.example.myweather.ui.theme.composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myweather.ui.theme.weatherviewscreen.Screen
import com.example.myweather.ui.theme.weatherviewscreen.WeatherHomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getMyweatherAppScreen(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            WeatherHomeScreen(navController = navController)
        }
        composable(Screen.Setting.route) {
            Settings(navController = navController)
        }
    }
}


