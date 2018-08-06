package com.volkovmedia.perfo.eastwindweather.ui.cityweather.adapter

import android.view.View
import com.volkovmedia.perfo.eastwindweather.data.entities.City
import com.volkovmedia.perfo.eastwindweather.data.entities.other.Temperature
import com.volkovmedia.perfo.eastwindweather.ui.base.BaseViewHolder
import com.volkovmedia.perfo.eastwindweather.utils.extensions.getTemperatureString
import kotlinx.android.synthetic.main.cityweather_item.view.*

class CityWeatherViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private val cityName by lazy { itemView.cityweather_item_city_name }
    private val citySize by lazy { itemView.cityweather_item_city_size }
    private val cityTemperature by lazy { itemView.cityweather_item_temperature }

    fun bind(city: City, temperature: Temperature) {
        with(city) {
            cityName.text = name
            citySize.text = context.getString(size.sizeName)
        }

        cityTemperature.text = temperature.getTemperatureString(context)
    }

}