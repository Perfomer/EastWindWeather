package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.citites.mvp

import com.volkovmedia.perfo.eastwindweather.data.ApplicationDatabase
import com.volkovmedia.perfo.eastwindweather.data.entities.City
import kotlinx.coroutines.experimental.launch

internal class SettingsCitiesModel(private val database: ApplicationDatabase) {

    fun readCities() = database.citiesDao().getAll()

    fun saveCity(city: City) {
        launch { database.citiesDao().insert(city) }
    }

    fun deleteCity(city: City) {
        launch { database.citiesDao().delete(city) }
    }

}