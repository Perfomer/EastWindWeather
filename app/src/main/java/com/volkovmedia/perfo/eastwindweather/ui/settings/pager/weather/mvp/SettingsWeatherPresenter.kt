package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.weather.mvp

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import com.volkovmedia.perfo.eastwindweather.data.ApplicationDatabase
import com.volkovmedia.perfo.eastwindweather.data.entities.Weather
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

internal class SettingsWeatherPresenter(private val view: SettingsWeatherView) {

    private val model by lazy { SettingsWeatherModel(ApplicationDatabase.instance) }

    fun subscribe(lifecycleOwner: LifecycleOwner) {
        model.readWeather().observe(lifecycleOwner, Observer { it?.let { view.onWeatherLoaded(it) } })
    }

    fun saveWeather(weather: Weather) = model.saveWeather(weather)

    fun deleteWeather(weather: Weather) = model.deleteWeather(weather)

    fun requestCitiesNames() {
        val citiesNames = async { model.readCitiesNames() }
        launch(UI) { view.onCitiesNamesLoaded(citiesNames.await()) }
    }

}