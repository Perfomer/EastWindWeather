package com.volkovmedia.perfo.eastwindweather.utils

import android.util.Log
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.SeasonWeather

class SeasonWeatherListLoggingDecorator : ArrayList<SeasonWeather> {

    constructor() : super() {}

    constructor(items: Collection<SeasonWeather>) : super(items) {}

    override fun get(index: Int): SeasonWeather {
        return super.get(index).apply {
            Log.d("SeasonWeatherDecorator", "Average weather is:" + average.toString())
        }
    }

}