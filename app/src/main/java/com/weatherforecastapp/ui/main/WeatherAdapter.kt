package com.weatherforecastapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weatherforecastapp.R

class WeatherAdapter :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var dataSet: List<WeatherAdapterModel> = listOf()

    fun setData(dataSet: List<WeatherAdapterModel>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: WeatherAdapterModel) = with(itemView) {
            itemView.findViewById<TextView>(R.id.txtDate).text =
                item.date//("${item.dayOfTheWeek} ${item.dateOfTheMonth}, ${item.monthOftheYear}")
            val rvWeatherDetails = itemView.findViewById<RecyclerView>(R.id.rvWeatherDetails)
            rvWeatherDetails?.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                hasFixedSize()
                val weatherDetailsAdapter =
                    WeatherDetailsAdapter(item.weatherAdapterDetailsModelList)
                adapter = weatherDetailsAdapter
                weatherDetailsAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_weather_record, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(item = dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}