package com.weatherforecastapp.box.appmodels.weather

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {
    @Headers("Content-Type: application/json")
    @GET("forecast?")
    fun getWeatherApiData(
        @Query("appid") appid: String,
        @Query("q") city: String
    ): Deferred<WeatherData>
}