package com.example.myweather.weatherRepository

import android.util.Log
import com.example.myweather.WeatherUnits
import com.example.myweather.weatherretrofit.Response
import com.example.myweather.weatherretrofit.WeatherApiClass
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback


class WeatherRepository {


    val liveWeather = MutableStateFlow<Response?>(null)
    val api = WeatherApiClass()


    fun getWeather(lat:Double,lon:Double) {
        api.service.getWeather(lat = lat,lon=lon , API_KEY, WeatherUnits.METRIC.unitString).enqueue(object :Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                liveWeather.value = response.body()!!
                Log.e("Api  call Success", "API call success")
                Log.e("temperature",liveWeather.value?.list?.get(0)?.main?.temp.toString())
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("Api  call Failed", "Api call failed - ${t.message}")

            }
        })

    }
    fun getWeatherInFarenheit() {
        api.service.getWeatherInFarenheit(liveWeather.value?.city?.name,API_KEY).enqueue(object :Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                liveWeather.value = response.body()!!
                Log.e("Api  call Success", "API call success")


            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("Api  call Failed", "Api call failed - ${t.message}")

            }
        })

    }
    fun getWeatherByCity(city:String?){
        api.service.getWeatherByCity(city, API_KEY,WeatherUnits.METRIC.unitString).enqueue(object :Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                liveWeather.value = response.body()!!
                Log.e("Api  call Success", "API call success")
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("Api  call Failed", "Api call failed - ${t.message}")

            }
        })
    }

    companion object {
        const val API_KEY = "eb7ef42dcd6080bf0efbe4977dc1b1b2"
    }


}


