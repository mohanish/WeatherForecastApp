package com.weatherforecastapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherforecastapp.box.apphelper.LoadingState
import com.weatherforecastapp.box.appmodels.weather.WeatherRepo
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class MainViewModel(private val weatherRepo: WeatherRepo) : ViewModel(), KoinComponent {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    val weatherResultData = weatherRepo.weatherResultData

    fun fetchWeatherData() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                weatherRepo.getWeatherApiData()
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
            }
        }
    }
}