package com.example.myweatherapp.WeatherRetrofit



import com.example.myweather.weatherretrofit.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast?")
    fun getWeather(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("appid") apiKey: String,
        @Query("units") units:String,
    ): Call<Response>

    @GET("forecast?")
    fun getWeatherInFarenheit(
//        @Query("lat") lat: Double?,
//        @Query("lon") lon: Double?,
        @Query("q") city: String?,
        @Query("appid") apiKey: String,
    ): Call<Response>


    @GET("forecast?")
    fun getWeatherByCity(
        @Query("q") city: String?,
        @Query("appid") apiKey: String,
        @Query("units") units:String,
    ): Call<Response>

    @GET("forecast?")
    fun getWeatherInCelcius(
        @Query("q") city: String?,
        @Query("appid") apiKey: String,
        @Query("units") units:String,
    ): Call<Response>

}