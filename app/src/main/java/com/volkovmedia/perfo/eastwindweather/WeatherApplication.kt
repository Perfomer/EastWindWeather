package com.volkovmedia.perfo.eastwindweather

import android.app.Application
import com.volkovmedia.perfo.eastwindweather.data.ApplicationDatabase

class WeatherApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        ApplicationDatabase.init(this)
    }

}