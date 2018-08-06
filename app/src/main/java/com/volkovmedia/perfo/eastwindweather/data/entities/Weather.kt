package com.volkovmedia.perfo.eastwindweather.data.entities

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import com.volkovmedia.perfo.eastwindweather.data.converters.MonthConverter
import com.volkovmedia.perfo.eastwindweather.data.converters.TemperatureConverter
import com.volkovmedia.perfo.eastwindweather.data.entities.other.Temperature
import com.volkovmedia.perfo.eastwindweather.utils.constants.Month

const val TABLE_WEATHER: String = "weather"

@Entity(tableName = TABLE_WEATHER,
        foreignKeys = [ForeignKey(entity = City::class, parentColumns = ["id"], childColumns = ["city_id"], onDelete = CASCADE)],
        indices = [Index(value = ["city_id", "month"], unique = true)]
)
data class Weather(@field:PrimaryKey(autoGenerate = true)
                   var id: Long,

                   @field:TypeConverters(TemperatureConverter::class)
                   @field:ColumnInfo(name = "value")
                   var temperature: Temperature,

                   @field:ColumnInfo(name = "city_id")
                   var cityId: Long,

                   @field:TypeConverters(MonthConverter::class)
                   var month: Month)