package com.example.myweather.weatherretrofit

import com.example.myweatherapp.WeatherRetrofit.WeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiClass {

    val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    var service: WeatherService = retrofit.create(WeatherService::class.java)

    companion object{
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

}