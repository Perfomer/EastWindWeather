package com.volkovmedia.perfo.eastwindweather.ui.cityweather.mvp

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import com.volkovmedia.perfo.eastwindweather.data.ApplicationDatabase
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.CityWeather
import com.volkovmedia.perfo.eastwindweather.utils.SeasonWeatherListLoggingDecorator
import com.volkovmedia.perfo.eastwindweather.utils.constants.Season
import com.volkovmedia.perfo.eastwindweather.utils.constants.TemperatureUnit
import com.volkovmedia.perfo.eastwindweather.utils.extensions.castTemperature
import com.volkovmedia.perfo.eastwindweather.utils.extensions.extractSeasonsWeather
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class CitiesWeatherPresenter(private val view: CitiesWeatherView) {

    var currentSeason = Season.values()[0]
        set(value) {
            field = value
            liveData.value?.let { updateValues(it) }
        }

    var currentTemperatureUnit = TemperatureUnit.values()[0]
        set(value) {
            field = value
            liveData.value?.let { updateValues(it) }
        }

    private val model by lazy { CitiesWeatherModel(ApplicationDatabase.instance) }

    private lateinit var liveData: LiveData<List<CityWeather>>

    fun subscribe(lifecycleOwner: LifecycleOwner) {
        liveData = model.readWeather()
        liveData.observe(lifecycleOwner, Observer { it?.let { updateValues(it) } })
    }

    private fun updateValues(items: List<CityWeather>) {
        val calculation = async {
            items.extractSeasonsWeather(currentSeason)
                    .castTemperature(currentTemperatureUnit)
        }

        launch(UI) {
            val newItems = calculation.await()
            view.onItemsLoaded(SeasonWeatherListLoggingDecorator(newItems))
        }
    }

}