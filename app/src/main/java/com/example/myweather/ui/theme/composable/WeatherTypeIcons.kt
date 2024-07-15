package com.example.myweather.ui.theme.composable

import androidx.compose.runtime.Composable
import com.example.myweather.R

@Composable
fun weatherTypeIcons(weatherDesc:String): Int {
    return when (weatherDesc) {
        "moderate rain" -> R.drawable.ic_rainy
        "rainy" -> R.drawable.ic_rainy
        "sunny" -> R.drawable.ic_sunny
        "broken clouds" -> R.drawable.ic_cloudy
        "overcast clouds" -> R.drawable.ic_very_cloudy
        "few clouds" -> R.drawable.ic_cloudy
        "scattered clouds" -> R.drawable.ic_cloudy
        "clear sky" -> R.drawable.ic_sunny
        "light rain" -> R.drawable.ic_rainshower
        "light snow" -> R.drawable.ic_snowy
        "snow" -> R.drawable.ic_snowy
        "heavy intensity rain" -> R.drawable.ic_rainythunder
        else -> R.drawable.ic_sunny
    }
}





