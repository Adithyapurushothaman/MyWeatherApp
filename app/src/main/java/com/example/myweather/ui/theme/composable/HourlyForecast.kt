package com.example.myweather.ui.theme.composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.ui.theme.composable.weatherRepo.Companion.weatherRepoObj
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourelyWeatherForecastCard(
    temperature: Double?,
    dtTme: String?,
    modifier: Modifier = Modifier,
    weatherDesc: String?){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(400.dp)
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        val DateTime = dtTme?.split(" ")
//        val time = DateTime?.get(1)
        if (dtTme != null) {
            val date = convertStringToDate(dtTme)
            println(date)
            val time = date?.let { getTimeFromDate(date = it) }
            Text(
                text = "$time",
                color = Color.White
            )
        }
        weatherDesc?.let { weatherTypeIcons(weatherDesc = it) }
            ?.let { painterResource(it) }?.let {
                Image(
                painter = it,
                contentDescription = null,
                modifier = Modifier.width(20.dp)
            )
            }
            



        Text(
            text = "${temperature}°C",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecast(
    modifier: Modifier = Modifier,
){

    val forecast = weatherRepoObj.liveWeather.collectAsState()
    Column(
        modifier = modifier
            .height(200.dp)
            .padding(horizontal = 8.dp)

    ) {
        Text(
            text = "Today",
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(5.dp))

        val list = forecast?.value?.list
        val size : Int = 1
        Card(
            modifier = Modifier
                .height(150.dp),
            elevation = 5.dp,
//            backgroundColor = Color.Transparent,
            backgroundColor = Color(0xFF00162A8),
            shape = RoundedCornerShape(10.dp)

        ) {
            Text(
                text = "Hourly Forecast",
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,

            )
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .height(200.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                size?.let {
                    items(it) {
                        for (e in 0 until 15) {
                            HourelyWeatherForecastCard(
                                temperature = list?.get(e)?.main?.temp as Double?,
                                dtTme = list?.get(e)?.dtTxt,
                                modifier = Modifier
                                    .height(100.dp)
                                    .padding(
                                        horizontal = 16.dp,
                                    ),
                                weatherDesc = list?.get(e)?.weather?.get(0)?.description,
                            )
                        }

                    }
                }
            }
        }
//        forecastRepo.getForecastDetails()
    }
}


