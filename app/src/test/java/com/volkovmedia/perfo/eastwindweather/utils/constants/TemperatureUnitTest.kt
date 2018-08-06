package com.volkovmedia.perfo.eastwindweather.utils.constants

import org.junit.Assert.assertEquals
import org.junit.Test

class TemperatureUnitTest {

    @Test
    fun toCelsius() {
        assertEquals(250, TemperatureUnit.CELSIUS.toCelsius(250.0).toInt())
        assertEquals(482, TemperatureUnit.FAHRENHEIT.toCelsius(250.0).toInt())
        assertEquals(-23, TemperatureUnit.KELVIN.toCelsius(250.0).toInt())
    }

    @Test
    fun toFahrenheit() {
        assertEquals(482, TemperatureUnit.CELSIUS.toFahrenheit(250.0).toInt())
        assertEquals(250, TemperatureUnit.FAHRENHEIT.toFahrenheit(250.0).toInt())
        assertEquals(-9, TemperatureUnit.KELVIN.toFahrenheit(250.0).toInt())
    }

    @Test
    fun toKelvin() {
        assertEquals(523, TemperatureUnit.CELSIUS.toKelvin(250.0).toInt())
        assertEquals(394, TemperatureUnit.FAHRENHEIT.toKelvin(250.0).toInt())
        assertEquals(250, TemperatureUnit.KELVIN.toKelvin(250.0).toInt())
    }

}