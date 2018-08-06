package com.volkovmedia.perfo.eastwindweather.utils.extensions

import com.volkovmedia.perfo.eastwindweather.data.entities.City
import com.volkovmedia.perfo.eastwindweather.data.entities.Weather
import com.volkovmedia.perfo.eastwindweather.data.entities.other.Temperature
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.CityWeather
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.SeasonWeather
import com.volkovmedia.perfo.eastwindweather.utils.constants.Season
import com.volkovmedia.perfo.eastwindweather.utils.constants.TemperatureUnit

fun List<SeasonWeather>.castTemperature(temperatureUnit: TemperatureUnit): List<SeasonWeather> {
    val result = mutableListOf<SeasonWeather>()

    forEach {
        val temperature = with(it.average) { temperatureUnit.toThis(this) }
        result += SeasonWeather(it.city, temperature)
    }

    return result
}

fun List<CityWeather>.extractSeasonsWeather(season: Season): List<SeasonWeather> {
    val result = mutableListOf<SeasonWeather>()

    for (city in extractCities()) {
        val cityWeather = extractWeatherForCity(city.id)
        cityWeather.extractSeasonWeather(city, season)?.let { result += it }
    }

    return result
}

fun List<Weather>.extractSeasonWeather(city: City, season: Season): SeasonWeather? {
    val seasonWeather = extractWeathersForSeason(season)
    if (seasonWeather.isEmpty()) return null

    val average = seasonWeather.temperatureAverage()

    return SeasonWeather(city, Temperature(average, TemperatureUnit.CELSIUS))
}

fun List<Weather>.temperatureAverage() = temperatureSum() / size

fun List<Weather>.temperatureSum() = sumByDouble {
    with(it.temperature) { unit.toCelsius(value) }
}

fun List<Weather>.extractWeathersForSeason(season: Season) = filter { season.months.contains(it.month) }

fun List<CityWeather>.extractWeatherForCity(id: Long) = filter { it.city.id == id }.map { it.weather }

fun List<CityWeather>.extractCities(): List<City> {
    val cities = mutableMapOf<Long, City>()

    for (weather in this) {
        with(weather.city) { cities[id] = this }
    }

    return cities.values.toList()
}