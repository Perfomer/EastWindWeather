package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.citites.mvp

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import com.volkovmedia.perfo.eastwindweather.data.ApplicationDatabase
import com.volkovmedia.perfo.eastwindweather.data.entities.City

internal class SettingsCitiesPresenter(private val view: SettingsCitiesView) {

    private val model by lazy { SettingsCitiesModel(ApplicationDatabase.instance) }

    fun subscribe(lifecycleOwner: LifecycleOwner) {
        model.readCities().observe(lifecycleOwner, Observer { it?.let { view.onItemsLoaded(it) } })
    }

    fun saveCity(city: City) = model.saveCity(city)

    fun deleteCity(city: City) = model.deleteCity(city)

}