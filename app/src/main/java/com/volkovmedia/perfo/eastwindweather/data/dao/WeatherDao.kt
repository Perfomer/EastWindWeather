package com.volkovmedia.perfo.eastwindweather.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.volkovmedia.perfo.eastwindweather.data.dao.base.BaseDao
import com.volkovmedia.perfo.eastwindweather.data.entities.TABLE_CITIES
import com.volkovmedia.perfo.eastwindweather.data.entities.TABLE_WEATHER
import com.volkovmedia.perfo.eastwindweather.data.entities.Weather
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.CityWeather

@Dao
interface WeatherDao : BaseDao<Weather> {

    @Query("""SELECT $TABLE_WEATHER.*, $TABLE_CITIES.id AS c_id, $TABLE_CITIES.size AS c_size, $TABLE_CITIES.name AS c_name
              FROM $TABLE_WEATHER
              INNER JOIN $TABLE_CITIES ON city_id = c_id""")
    fun getAll(): LiveData<List<CityWeather>>

}