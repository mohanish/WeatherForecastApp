package com.weatherforecastapp.box.appmodels.weather

import com.weatherforecastapp.BuildConfig.API_KEY
import com.weatherforecastapp.box.db.WeatherDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepo(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao
) {
    val weatherResultData = weatherDao.fetchAll()

    suspend fun getWeatherApiData(city: String) {
        withContext(Dispatchers.IO) {
            val weatherDataResult = weatherApi.getWeatherApiData(API_KEY, city).await()
            weatherDao.replaceAll(weatherDataResult)
        }
    }
}