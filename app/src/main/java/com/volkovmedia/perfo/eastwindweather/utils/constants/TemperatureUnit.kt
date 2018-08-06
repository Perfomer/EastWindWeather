package com.volkovmedia.perfo.eastwindweather.utils.constants

import android.support.annotation.StringRes
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.data.entities.other.Temperature

enum class TemperatureUnit(@StringRes val shortSign: Int,
                           @StringRes val fullSign: Int) {

    CELSIUS(R.string.temperature_unit_celsius_short, R.string.temperature_unit_celsius) {
        override fun toCelsius(value: Double) = value
        override fun toFahrenheit(value: Double) = value * 1.8 + 32
        override fun toKelvin(value: Double) = value + 273.15
        override fun toThis(temperature: Temperature) = with(temperature) {
            Temperature(unit.toCelsius(value), this@CELSIUS)
        }
    },
    FAHRENHEIT(R.string.temperature_unit_fahrenheit_short, R.string.temperature_unit_fahrenheit) {
        override fun toCelsius(value: Double) = value * 1.8 + 32
        override fun toFahrenheit(value: Double) = value
        override fun toKelvin(value: Double) = ((value - 32) / 1.8) + 273.15
        override fun toThis(temperature: Temperature) = with(temperature) {
            Temperature(unit.toFahrenheit(value), this@FAHRENHEIT)
        }
    },
    KELVIN(R.string.temperature_unit_kelvin_short, R.string.temperature_unit_kelvin) {
        override fun toCelsius(value: Double) = value - 273.15
        override fun toFahrenheit(value: Double) = (value - 273.15) * 1.8 + 32
        override fun toKelvin(value: Double) = value
        override fun toThis(temperature: Temperature) = with(temperature) {
            Temperature(unit.toKelvin(value), this@KELVIN)
        }
    };

    abstract fun toCelsius(value: Double): Double

    abstract fun toFahrenheit(value: Double): Double

    abstract fun toKelvin(value: Double): Double

    abstract fun toThis(temperature: Temperature): Temperature

}