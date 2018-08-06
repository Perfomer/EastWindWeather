package com.volkovmedia.perfo.eastwindweather.ui.cityweather.adapter

import android.support.annotation.LayoutRes
import android.view.View
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.SeasonWeather
import com.volkovmedia.perfo.eastwindweather.ui.base.BaseListAdapter

class CitiesWeatherAdapter : BaseListAdapter<SeasonWeather, CityWeatherViewHolder>() {

    @LayoutRes
    override val layoutResource = R.layout.cityweather_item

    override fun createViewHolder(view: View) = CityWeatherViewHolder(view)

    override fun onBindViewHolder(viewHolder: CityWeatherViewHolder, item: SeasonWeather, position: Int) {
        with(item) { viewHolder.bind(city, average) }
    }

}