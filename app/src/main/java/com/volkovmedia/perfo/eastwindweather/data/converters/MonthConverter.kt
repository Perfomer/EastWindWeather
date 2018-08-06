package com.volkovmedia.perfo.eastwindweather.data.converters

import android.arch.persistence.room.TypeConverter
import com.volkovmedia.perfo.eastwindweather.utils.constants.Month

object MonthConverter {

    @JvmStatic
    @TypeConverter
    fun fromEnum(size: Month) = size.ordinal

    @JvmStatic
    @TypeConverter
    fun toEnum(size: Int) = Month.values()[size]

}