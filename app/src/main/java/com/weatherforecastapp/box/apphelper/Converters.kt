package com.weatherforecastapp.box.apphelper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.weatherforecastapp.box.appmodels.weather.Weather
import com.weatherforecastapp.box.appmodels.weather.WeatherDetails
import java.util.*

class Converters {

//    val netModule: DependancyModule = get()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) Date() else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time ?: 0
    }

    @TypeConverter
    fun fromStringToArray(value: String?): Array<String>? {
        return value?.split(",")?.toTypedArray() ?: arrayOf()
    }

    @TypeConverter
    fun stringToStringArray(strings: Array<String>?): String? {
        return strings?.joinToString(",") ?: ""
    }

    // WeatherDetails converter
    @TypeConverter
    fun fromWeatherDetailsList(weatherDetails: List<WeatherDetails?>?): String? {
        val type = object : TypeToken<List<WeatherDetails>>() {}.type
        return Gson().toJson(weatherDetails, type)
    }

    @TypeConverter
    fun toWeatherDetailsList(weatherDetails: String?): List<WeatherDetails>? {
        val type = object : TypeToken<List<WeatherDetails>>() {}.type
        return Gson().fromJson<List<WeatherDetails>>(weatherDetails, type)
    }

    // Weather converter
    @TypeConverter
    fun fromWeatherList(weather: List<Weather?>?): String? {
        val type = object : TypeToken<List<Weather>>() {}.type
        return Gson().toJson(weather, type)
    }

    @TypeConverter
    fun toWeatherList(weather: String?): List<Weather>? {
        val type = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson<List<Weather>>(weather, type)
    }
}