package com.weatherforecastapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weatherforecastapp.R
import com.weatherforecastapp.box.apphelper.LoadingState
import org.koin.core.KoinComponent
import org.koin.core.get
import java.util.*

class MainFragment : Fragment(), KoinComponent {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel = get()
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        showWeatherData()
        mainViewModel.loadingState.observe(viewLifecycleOwner, {
            when (it.status) {
                LoadingState.Status.FAILED -> Toast.makeText(context, it.msg, Toast.LENGTH_LONG)
                    .show()
                LoadingState.Status.RUNNING -> Toast.makeText(context, "Loading", Toast.LENGTH_LONG)
                    .show()
                LoadingState.Status.SUCCESS -> Toast.makeText(context, "Success", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun showWeatherData() {
        mainViewModel.weatherResultData.observe(viewLifecycleOwner, { weatherData ->
            if (weatherData != null) {
                view?.findViewById<TextView>(R.id.txtCity)?.text = weatherData.city?.name
                val weatherAdapterDataList =
                    mainViewModel.prepareForecastAdapterData(list = weatherData.list)
                weatherAdapter.setData(weatherAdapterDataList)
            }
        })
    }

    private fun initView() {
        val imgSearchCity = view?.findViewById<ImageView>(R.id.imgSearchCity)
        val edSearchCity = view?.findViewById<EditText>(R.id.edSearchCity)
        imgSearchCity?.setOnClickListener {
            val cityName = edSearchCity?.text?.trim().toString()
            val cityNameFromDB = mainViewModel.weatherResultData.value?.city
            if (cityName.isNotEmpty() && !cityName.equals(cityNameFromDB?.toString(), true))
                mainViewModel.fetchWeatherData(cityName.toLowerCase(Locale.getDefault()))
        }
        val rvWeather = view?.findViewById<RecyclerView>(R.id.rvWeather)
        rvWeather?.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            weatherAdapter = WeatherAdapter()
            adapter = weatherAdapter
        }
    }
}