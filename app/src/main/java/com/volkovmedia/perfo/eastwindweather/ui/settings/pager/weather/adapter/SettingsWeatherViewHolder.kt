package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.weather.adapter

import android.view.View
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.CityWeather
import com.volkovmedia.perfo.eastwindweather.ui.base.BaseViewHolder
import com.volkovmedia.perfo.eastwindweather.utils.extensions.getTemperatureString
import kotlinx.android.synthetic.main.settings_item_weather.view.*

class SettingsWeatherViewHolder(itemView: View,
                                onItemClickListener: (CityWeather) -> Unit)
    : BaseViewHolder(itemView) {

    private val cityName by lazy { itemView.settings_item_weather_name }
    private val weatherMonth by lazy { itemView.settings_item_weather_month }
    private val weatherTemperature by lazy { itemView.settings_item_weather_temperature }

    private lateinit var cityWeather: CityWeather

    init {
        itemView.setOnClickListener { onItemClickListener(cityWeather) }
    }

    fun bind(cityWeather: CityWeather) = with(cityWeather) {
        this@SettingsWeatherViewHolder.cityWeather = this
        cityName.text = city.name
        weatherMonth.text = context.getString(weather.month.nameRes)
        weatherTemperature.text = weather.temperature.getTemperatureString(context)
    }

}