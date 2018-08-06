package com.volkovmedia.perfo.eastwindweather.ui.cityweather.mvp

import com.volkovmedia.perfo.eastwindweather.data.entities.relations.SeasonWeather

interface CitiesWeatherView {

    fun onItemsLoaded(items: List<SeasonWeather>)

}