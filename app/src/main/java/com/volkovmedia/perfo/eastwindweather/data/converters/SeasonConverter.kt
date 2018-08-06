package com.volkovmedia.perfo.eastwindweather.data.converters

import android.arch.persistence.room.TypeConverter
import com.volkovmedia.perfo.eastwindweather.utils.constants.Season

object SeasonConverter {

    @JvmStatic
    @TypeConverter
    fun fromSeason(season: Season): List<Int> = season.months.map { it.ordinal }

}