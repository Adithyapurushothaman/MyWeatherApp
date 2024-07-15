package com.example.myweather.ui.theme.composable

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.myweather.R
import com.example.myweather.data.datastore.DataStorePreferenceStorage
import com.example.myweather.data.datastore.UserPreference
import com.example.myweather.ui.theme.weatherviewscreen.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable()
fun Settings(navController: NavController){


    Box(modifier = Modifier
        .fillMaxSize(),

        ){
        Image(painter = painterResource(id = R.drawable.blueskyhm),
            contentDescription = null,

            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight

        )
        Column() {
            Text(
                text = "Settings",
                style = TextStyle(fontSize = 30.sp),
                color = Color.White,
                modifier = Modifier.padding(10.dp)

            )
            Spacer(modifier = Modifier.height(20.dp))
            Card(modifier = Modifier.padding(8.dp), elevation = 5.dp, backgroundColor = Color.Transparent) {
                switchFahrenHeit()
            }
            roundPlusButton(navController)
        }
    }

}

@Composable
fun switchFahrenHeit() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = UserPreference(context)
//    val dataStore = DataStorePreferenceStorage()
    val savedUnitState = dataStore.userUnitFlow.collectAsState(initial = true)
    if (savedUnitState.value){
        weatherRepo.weatherRepoObj.getWeatherInFarenheit()
    }
    val isChecked = remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.Transparent)
    ) {
        Text(text = "Units-Fahrenheit",
            style = TextStyle(fontSize = 24.sp,),
            modifier = Modifier.padding(20.dp),
            color = Color.White,
        )
        val switchColors = SwitchDefaults.colors(
            checkedThumbColor = Color(0xff453F78),
            uncheckedThumbColor = Color(0xff759AAB),
            checkedTrackColor = Color(0xff759AAB),
            uncheckedTrackColor = Color(0xff453F78)
        )

        isChecked.value?.let {
            Switch(
                checked = it,
                onCheckedChange = {
                    scope.launch {
                        isChecked.value = it
                        dataStore.saveUnit(isChecked)
                        if (isChecked.value){
                            weatherRepo.weatherRepoObj.getWeatherInFarenheit()
                        }
                    }
                },
                modifier = Modifier.absolutePadding(left = 100.dp, top= 16.dp,),
                colors = switchColors
            )
        }
    }
}

@Composable
fun roundPlusButton(navController: NavController) {
    var selectedCity by remember { mutableStateOf("") }
    val context = LocalContext.current
    Card(modifier = Modifier
        .absolutePadding(top = 50.dp, left = 8.dp)
        .width(500.dp), elevation = 5.dp, backgroundColor = Color.Transparent) {
        Column {
            Row(
                modifier = Modifier
            ) {
                Text(text = "Cities",
                    style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.absolutePadding(top = 40.dp, left = 16.dp),
                    color = Color.White,
                )
                addCityDialogButton()

            }
            CityListScreen(onCitySelected = { city ->
                selectedCity = city
//                Toast.makeText(context, selectedCity, Toast.LENGTH_SHORT).show()

                CoroutineScope(Dispatchers.IO).launch {
                    weatherRepo.weatherRepoObj.getWeatherByCity(selectedCity)
                }
                navController.navigate(Screen.Setting.route)

            })

        }
    }
}


@Composable
fun addCityDialogButton(){
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        addCityDialog(onDismiss = {showDialog.value = false})
    }
    Button(
        modifier = Modifier
            .absolutePadding(left = 220.dp, top = 30.dp)
            .height(60.dp)
            .width(60.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00162A8)),
        onClick = {showDialog.value = true}) {
                        Text("+", style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold), color = Color.White)
    }
}


@Composable
fun CityListScreen(onCitySelected: (String) -> Unit) {

    val context = LocalContext.current
    val dataStore = UserPreference(context)


    val cityList by dataStore.cityListFlow.collectAsState(listOf())
    Column() {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .height(150.dp)
                .width(200.dp)

        ) {
            for (e in cityList){
                Log.e("city",e)
            }
            items(cityList) { item ->
                Text(
                    text = item,
                    style = TextStyle(fontSize = 20.sp, color = Color.White),
                    modifier = Modifier.clickable {
                        run { onCitySelected(item) }}
                )
            }
        }
    }
}

@Composable
fun addCityDialog(onDismiss:() -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = UserPreference(context)

    var cityname by remember {
            mutableStateOf("")
    }
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(8.dp),
            elevation = 8.dp
        ) {
            Column(
                    Modifier
                        .background(Color.White)
                ) {

                    Text(
                        text = "Add City",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 20.sp
                    )

                    OutlinedTextField(
                        value = cityname,
                        onValueChange = { cityname = it }, modifier = Modifier.padding(8.dp),
                        label = { Text("City Name") }
                    )

                    Row {

                        Button(
                            onClick = {
                                scope.launch {
                                    dataStore.addCity(cityname)
                                }
                                Toast.makeText(context, cityname, Toast.LENGTH_SHORT).show()

                                onDismiss() },
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .weight(1F)
                        ) {
                            Text(text = "Done")
                        }
                    }
                }
            }
        }
    }


