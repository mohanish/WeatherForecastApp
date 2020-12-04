package com.weatherforecastapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherforecastapp.box.apphelper.*
import com.weatherforecastapp.box.appmodels.weather.WeatherDetails
import com.weatherforecastapp.box.appmodels.weather.WeatherRepo
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class MainViewModel(private val weatherRepo: WeatherRepo) : ViewModel(), KoinComponent {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    val weatherResultData = weatherRepo.weatherResultData

    fun fetchWeatherData(city: String) {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                weatherRepo.getWeatherApiData(city)
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
            }
        }
    }

    fun prepareForecastAdapterData(list: List<WeatherDetails>?): MutableList<WeatherAdapterModel> {
        val weatherAdapterList = mutableListOf<WeatherAdapterModel>()
        val uniqueDate = ArrayList<Int>()
        for (listWeatherInfo in list!!) {
            val dateOfTheMonth = getDateOfTheMonth(listWeatherInfo.dtTxt)
            val weatherAdapterDetailsModel = WeatherAdapterDetailsModel(
                temperature = listWeatherInfo.main?.temp.toString(),
                weatherType = listWeatherInfo.weather?.get(0)?.main,
                time = getTime(listWeatherInfo.dtTxt),
                dayOfTheWeek = getDayOfTheWeek(listWeatherInfo.dtTxt),
                dateOfTheMonth = dateOfTheMonth.toString(),
                monthOftheYear = getMonthOfTheYear(listWeatherInfo.dtTxt),
                date = ("${getDayOfTheWeek(listWeatherInfo.dtTxt)} ${dateOfTheMonth}, ${
                    getMonthOfTheYear(listWeatherInfo.dtTxt)
                }")
            )

            if (uniqueDate.contains(dateOfTheMonth)) {
                val weatherDetailsModelList =
                    weatherAdapterList[uniqueDate.indexOf(dateOfTheMonth)].weatherAdapterDetailsModelList
                weatherDetailsModelList.add(weatherAdapterDetailsModel)
            } else {
                uniqueDate.add(dateOfTheMonth)
                val weatherDetailsAdapterList = mutableListOf<WeatherAdapterDetailsModel>()
                weatherDetailsAdapterList.add(weatherAdapterDetailsModel)
                val weatherAdapterModel = WeatherAdapterModel(
                    weatherAdapterDetailsModel.date,
                    weatherDetailsAdapterList
                )
                weatherAdapterList.add(weatherAdapterModel)
            }
        }
        return weatherAdapterList
    }
}