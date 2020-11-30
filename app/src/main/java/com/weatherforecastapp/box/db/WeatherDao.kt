package com.weatherforecastapp.box.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.weatherforecastapp.box.appmodels.weather.WeatherData

@Dao
interface WeatherDao {

    @Query("SELECT * FROM " + "weatherforecast")
    fun fetchAll(): LiveData<WeatherData>

    @Transaction
    fun replaceAll(weatherData: WeatherData) {
//        deleteAll()
        insertAll(weatherData = weatherData)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weatherData: WeatherData)

//    @Query("DELETE FROM " + "weatherforecast")
//    fun deleteAll()
}