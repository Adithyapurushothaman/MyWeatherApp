package com.example.myweather.ui.theme.composable


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myweather.ui.theme.composable.weatherRepo.Companion.weatherRepoObj
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyForecastCard(
    temperature: Double?,
    dtTme: String?,
    modifier: Modifier = Modifier,
    weatherDesc: String?){

    Row(
        modifier = Modifier
            .height(60.dp)
            .width(400.dp)
            .padding(8.dp),
//        verticalAlignment = Alignment.CenterHorizontally,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        val date = dtTme?.let { convertStringToDate(it) }
        val day = date?.let { getDayFromDate(it) }
        if (day != null) {
            Text(
                text = day,
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
//        if (weatherDesc != null) {
//            Text(
//                text = weatherDesc,
//                color = Color.White,
//                fontWeight = FontWeight.SemiBold
//            )
//        }


        Text(
            text = "${temperature}°C",
            color = Color.White,
            fontWeight = FontWeight.Bold,

        )
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyForecast(
    modifier: Modifier = Modifier,
){

    val forecast = weatherRepoObj.liveWeather.collectAsState()
    Column(
        modifier = modifier
            .height(400.dp)
            .padding(horizontal = 8.dp)
            .padding(vertical = 20.dp)

    ) {
        val list = forecast?.value?.list
        val size : Int = 1

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp),

            elevation = 5.dp,
//            backgroundColor = Color.Transparent,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color(0xFF00162A8)
        ) {
            Column() {

                Text(
                    text = "Five days Forecasts",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier
                        .padding(vertical = 1.dp)


//                verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    size?.let {
                        items(it) {
                            for (e in 0 until 40 step 8) {
                                DailyForecastCard(
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
        }
    }
}

