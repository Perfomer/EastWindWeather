package com.volkovmedia.perfo.eastwindweather.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.volkovmedia.perfo.eastwindweather.data.converters.CitySizeConverter
import com.volkovmedia.perfo.eastwindweather.utils.constants.CitySize

const val TABLE_CITIES: String = "cities"

@Entity(tableName = TABLE_CITIES)
data class City(@field:PrimaryKey(autoGenerate = true)
                var id: Long,

                @field:TypeConverters(CitySizeConverter::class)
                var size: CitySize,

                var name: String)