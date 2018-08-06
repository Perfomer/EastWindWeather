package com.volkovmedia.perfo.eastwindweather.data.converters

import android.arch.persistence.room.TypeConverter
import com.volkovmedia.perfo.eastwindweather.data.entities.other.Temperature
import com.volkovmedia.perfo.eastwindweather.utils.constants.TemperatureUnit

object TemperatureConverter {

    @JvmStatic
    @TypeConverter
    fun fromTemperature(temperature: Temperature): Double = with(temperature) { unit.toCelsius(value) }

    @JvmStatic
    @TypeConverter
    fun toTemperature(temperature: Double) = Temperature(temperature, TemperatureUnit.CELSIUS)

}