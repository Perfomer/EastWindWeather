package com.volkovmedia.perfo.eastwindweather.data.converters

import android.arch.persistence.room.TypeConverter
import com.volkovmedia.perfo.eastwindweather.utils.constants.CitySize

object CitySizeConverter {

    @JvmStatic
    @TypeConverter
    fun fromEnum(size: CitySize) = size.ordinal

    @JvmStatic
    @TypeConverter
    fun toEnum(size: Int) = CitySize.values()[size]

}