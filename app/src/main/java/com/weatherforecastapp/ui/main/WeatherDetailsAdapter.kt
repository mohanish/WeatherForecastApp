package com.weatherforecastapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.weatherforecastapp.R
import com.weatherforecastapp.box.apphelper.getWeatherIcon

class WeatherDetailsAdapter(private val dataSet: List<WeatherAdapterDetailsModel>) :
    RecyclerView.Adapter<WeatherDetailsAdapter.WeatherDetailsViewHolder>() {

    class WeatherDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: WeatherAdapterDetailsModel) = with(itemView) {
            itemView.findViewById<TextView>(R.id.txtTime).text = item.time
            itemView.findViewById<TextView>(R.id.txtTemperature).text = item.temperature
            itemView.findViewById<ImageView>(R.id.imgWeatherIcon).setImageDrawable(
                getWeatherIcon(
                    itemView.context,
                    item.weatherType.toString()
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDetailsViewHolder {
        return WeatherDetailsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_weather_details, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherDetailsViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}