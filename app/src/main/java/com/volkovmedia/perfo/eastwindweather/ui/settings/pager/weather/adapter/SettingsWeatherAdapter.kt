package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.weather.adapter

import android.support.annotation.LayoutRes
import android.view.View
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.CityWeather
import com.volkovmedia.perfo.eastwindweather.ui.base.BaseListAdapter

class SettingsWeatherAdapter(private val onItemClickListener: (CityWeather) -> Unit)
    : BaseListAdapter<CityWeather, SettingsWeatherViewHolder>() {

    @LayoutRes
    override val layoutResource = R.layout.settings_item_weather

    override fun createViewHolder(view: View) = SettingsWeatherViewHolder(view, onItemClickListener)

    override fun onBindViewHolder(viewHolder: SettingsWeatherViewHolder, item: CityWeather, position: Int) {
        viewHolder.bind(item)
    }

}