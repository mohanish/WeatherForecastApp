package com.weatherforecastapp.box.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.weatherforecastapp.box.apphelper.Converters
import com.weatherforecastapp.box.appmodels.weather.WeatherData
import com.weatherforecastapp.box.appmodels.weather.WeatherDetails

@Database(entities = [WeatherData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao
}