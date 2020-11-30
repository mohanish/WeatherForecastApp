package com.weatherforecastapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.weatherforecastapp.R
import com.weatherforecastapp.box.apphelper.LoadingState
import org.koin.core.KoinComponent
import org.koin.core.get

class MainFragment : Fragment(), KoinComponent {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel = get()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        showWeatherData()
        mainViewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                LoadingState.Status.FAILED -> Toast.makeText(context, it.msg, Toast.LENGTH_LONG)
                    .show()
                LoadingState.Status.RUNNING -> Toast.makeText(context, "Loading", Toast.LENGTH_LONG)
                    .show()
                LoadingState.Status.SUCCESS -> {
                    showWeatherData()
                }
            }
        })
        mainViewModel.fetchWeatherData()
    }

    private fun showWeatherData() {
        mainViewModel.weatherResultData.observe(viewLifecycleOwner, { weatherData ->
            view?.findViewById<TextView>(R.id.txtCity)?.text = weatherData.city?.name
            val sb = StringBuilder()
            weatherData.list?.forEach { data ->
                sb.append(data.weather?.get(0)?.description + "\n")
            }
            view?.findViewById<TextView>(R.id.txtDay1Temp)?.text = sb
//            view?.findViewById<TextView>(R.id.txtDay2Temp)?.text = weatherData.list?.get(8)?.weather?.get(0)?.icon
//            view?.findViewById<TextView>(R.id.txtDay3Temp)?.text = weatherData.list?.get(39)?.weather?.get(0)?.icon
        })
    }
}