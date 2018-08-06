package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.weather.mvp

import com.volkovmedia.perfo.eastwindweather.data.entities.partial.CityName
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.CityWeather

internal interface SettingsWeatherView {

    fun onWeatherLoaded(items: List<CityWeather>)

    fun onCitiesNamesLoaded(items: List<CityName>)

}