package com.example.myweather.ui.theme.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myweather.R
import com.example.myweather.ui.theme.composable.weatherRepo.Companion.weatherRepoObj
import androidx.navigation.compose.rememberNavController
import com.example.myweather.ui.theme.weatherviewscreen.Screen


@Composable
fun WeatherForecastScreen(navController: NavController) {

    val weather = weatherRepoObj.liveWeather.collectAsState()


    MaterialTheme {
        Box {
//            val navController = rememberNavController()
            IconButton(
                onClick = { navController.navigate(Screen.Setting.route) }) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings",
                    //                modifier = Modifier.absolutePadding(left = 350.dp)
                )
            }
        }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    CityDisplay(weather.value?.city?.name)
                    Spacer(modifier = Modifier.height(40.dp))
                    TemperatureDisplay(weather.value?.list?.get(0)?.main?.temp as Double?)
                    DateDisplay(
                        weather.value?.list?.get(0)?.dtTxt,
                        weather.value?.list?.get(0)?.main?.tempMax as Double?,
                        weather.value?.list?.get(0)?.main?.tempMin as Double?
                    )


                }

            }

    }




@Composable
fun TemperatureDisplay(temperature : Double?) {
    Text(text = "${temperature}°C",
        style = TextStyle(fontSize = 70.sp, color = Color.White, ))


}
@Composable
fun CityDisplay(City : String?){
    Row() {
        Icon(modifier = Modifier.padding(3.dp),
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = null
        )
        Text(text = "${City}",
            style = TextStyle(fontSize = 15.sp, color = Color.White, fontStyle = FontStyle.Italic))
    }

}
@Composable
fun DateDisplay(DateTime:String?,temperatureMax : Double?,temperatureMin : Double?,){
    val DateTimelist = DateTime?.split(" ")
    val Date = DateTimelist?.get(0)
    Row() {
        Text(text = "  $temperatureMax°C / ",style = TextStyle(fontSize = 20.sp , color = Color.White))
        Text(text = "$temperatureMin°C",style = TextStyle(fontSize = 20.sp , color = Color.White))

    }
}

