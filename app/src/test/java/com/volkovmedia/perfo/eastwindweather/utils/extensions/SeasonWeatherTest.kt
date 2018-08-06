package com.volkovmedia.perfo.eastwindweather.utils.extensions

import com.volkovmedia.perfo.eastwindweather.data.entities.City
import com.volkovmedia.perfo.eastwindweather.data.entities.Weather
import com.volkovmedia.perfo.eastwindweather.data.entities.other.Temperature
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.CityWeather
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.SeasonWeather
import com.volkovmedia.perfo.eastwindweather.utils.constants.CitySize
import com.volkovmedia.perfo.eastwindweather.utils.constants.Month
import com.volkovmedia.perfo.eastwindweather.utils.constants.Season
import com.volkovmedia.perfo.eastwindweather.utils.constants.TemperatureUnit
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SeasonWeatherTest {

    private lateinit var cityWeatherList: List<CityWeather>

    @Before
    fun init() {
        cityWeatherList = listOf(
                createCityWeather(0, 100.0, Month.DECEMBER),
                createCityWeather(1, 20.0, Month.DECEMBER),
                createCityWeather(0, 200.0, Month.JANUARY),
                createCityWeather(0, 300.0, Month.FEBRUARY),
                createCityWeather(0, 10.0, Month.APRIL),
                createCityWeather(0, 20.0, Month.MAY),
                createCityWeather(1, 20.0, Month.NOVEMBER),
                createCityWeather(1, 40.0, Month.OCTOBER)
        )
    }

    @Test
    fun extractSeasonsWeather() {
        checkWinter()
        checkSpring()
        checkAutumn()
    }

    private fun checkWinter() {
        val seasonWeather = cityWeatherList.extractSeasonsWeather(Season.WINTER)

        val city0 = createCity(0)
        val city1 = createCity(1)

        val expected = listOf(SeasonWeather(city0, Temperature(200.0, TemperatureUnit.CELSIUS)),
                SeasonWeather(city1, Temperature(20.0, TemperatureUnit.CELSIUS)))

        assertEquals(expected, seasonWeather)
    }

    private fun checkSpring() {
        val seasonWeather = cityWeatherList.extractSeasonsWeather(Season.SPRING)

        val city0 = createCity(0)

        val expected = listOf(SeasonWeather(city0, Temperature(15.0, TemperatureUnit.CELSIUS)))

        assertEquals(expected, seasonWeather)
    }

    private fun checkAutumn() {
        val seasonWeather = cityWeatherList.extractSeasonsWeather(Season.AUTUMN)

        val city1 = createCity(1)

        val expected = listOf(SeasonWeather(city1, Temperature(30.0, TemperatureUnit.CELSIUS)))

        assertEquals(expected, seasonWeather)
    }

    private fun createCityWeather(cityId: Long, temperature: Double, month: Month): CityWeather {
        return CityWeather(createCity(cityId), createWeather(cityId, temperature, month))
    }

    private fun createCity(id: Long) = City(id, CitySize.MEDIUM, "testCity")

    private fun createWeather(cityId: Long, temperature: Double, month: Month): Weather {
        return Weather(0, Temperature(temperature, TemperatureUnit.CELSIUS), cityId, month)
    }

}