package com.volkovmedia.perfo.eastwindweather.data.entities.relations

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.TypeConverters
import com.volkovmedia.perfo.eastwindweather.data.converters.TemperatureConverter
import com.volkovmedia.perfo.eastwindweather.data.entities.City
import com.volkovmedia.perfo.eastwindweather.data.entities.other.Temperature

data class SeasonWeather(@field:Embedded(prefix = "c_")
                         val city: City,

                         @field:TypeConverters(TemperatureConverter::class)
                         var average: Temperature)