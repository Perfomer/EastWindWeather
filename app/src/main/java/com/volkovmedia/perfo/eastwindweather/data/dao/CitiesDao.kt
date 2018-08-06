package com.volkovmedia.perfo.eastwindweather.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.volkovmedia.perfo.eastwindweather.data.dao.base.BaseDao
import com.volkovmedia.perfo.eastwindweather.data.entities.City
import com.volkovmedia.perfo.eastwindweather.data.entities.TABLE_CITIES
import com.volkovmedia.perfo.eastwindweather.data.entities.partial.CityName

@Dao
interface CitiesDao: BaseDao<City> {

    @Query("SELECT * FROM $TABLE_CITIES")
    fun getAll(): LiveData<List<City>>

    @Query("SELECT id, name FROM $TABLE_CITIES")
    fun getAllNames(): List<CityName>

}