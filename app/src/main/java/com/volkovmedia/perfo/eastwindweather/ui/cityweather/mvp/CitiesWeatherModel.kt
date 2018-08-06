package com.volkovmedia.perfo.eastwindweather.ui.cityweather.mvp

import com.volkovmedia.perfo.eastwindweather.data.ApplicationDatabase

class CitiesWeatherModel(private val database: ApplicationDatabase) {

    fun readWeather() = database.weatherDao().getAll()

}