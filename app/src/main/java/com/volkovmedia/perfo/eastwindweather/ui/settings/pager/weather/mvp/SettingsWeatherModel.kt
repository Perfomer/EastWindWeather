package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.weather.mvp

import com.volkovmedia.perfo.eastwindweather.data.ApplicationDatabase
import com.volkovmedia.perfo.eastwindweather.data.entities.Weather
import kotlinx.coroutines.experimental.launch

internal class SettingsWeatherModel(private val database: ApplicationDatabase) {

    fun readCitiesNames() = database.citiesDao().getAllNames()

    fun readWeather() = database.weatherDao().getAll()

    fun saveWeather(weather: Weather) {
        launch { database.weatherDao().insert(weather) }
    }

    fun deleteWeather(weather: Weather) {
        launch { database.weatherDao().delete(weather) }
    }

}