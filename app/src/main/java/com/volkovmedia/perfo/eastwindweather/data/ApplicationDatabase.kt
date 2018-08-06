package com.volkovmedia.perfo.eastwindweather.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.volkovmedia.perfo.eastwindweather.data.dao.CitiesDao
import com.volkovmedia.perfo.eastwindweather.data.dao.WeatherDao
import com.volkovmedia.perfo.eastwindweather.data.entities.City
import com.volkovmedia.perfo.eastwindweather.data.entities.Weather

@Database(version = 1, entities = [City::class, Weather::class])
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    abstract fun citiesDao(): CitiesDao

    companion object {

        lateinit var instance: ApplicationDatabase

        fun init(context: Context) {
            instance = Room.databaseBuilder(context.applicationContext, ApplicationDatabase::class.java, "weather_db")
                    .fallbackToDestructiveMigration()
                    .build()
        }

    }

}