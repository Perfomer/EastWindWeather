package com.volkovmedia.perfo.eastwindweather.data.entities.relations

import android.arch.persistence.room.Embedded
import com.volkovmedia.perfo.eastwindweather.data.entities.City
import com.volkovmedia.perfo.eastwindweather.data.entities.Weather

data class CityWeather(@field:Embedded(prefix = "c_")
                       val city: City,

                       @field:Embedded
                       val weather: Weather)