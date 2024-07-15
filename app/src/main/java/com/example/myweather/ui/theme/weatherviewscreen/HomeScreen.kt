package com.example.myweather.ui.theme.weatherviewscreen


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myweather.R
import com.example.myweather.ui.theme.composable.DailyForecast
import com.example.myweather.ui.theme.composable.WeatherForecast
import com.example.myweather.ui.theme.composable.WeatherForecastScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherHomeScreen(navController : NavController){

    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .scrollable(state = scrollState, Orientation.Vertical)
        .fillMaxSize(),
    )
    {
        Image(painter = painterResource(id = R.drawable.blueskyhm),
            contentDescription = null,

            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight

            )
        LazyColumn(
            modifier = Modifier
//                .fillMaxSize()
                .fillMaxWidth(),


            ) {


            item { WeatherForecastScreen(navController) }
            item { Spacer(modifier = Modifier.height(50.dp)) }

            item { WeatherForecast() }
            item { DailyForecast() }

        }
    }
}