package com.example.myweather.ui.theme.weatherviewscreen

sealed class Screen(val route : String){
    object Home : Screen("home")
    object Setting : Screen("settings")

}
