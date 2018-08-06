package com.volkovmedia.perfo.eastwindweather.utils.extensions

import android.content.Context
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.data.entities.other.Temperature

fun Temperature.getTemperatureString(context: Context): String {
    val prefix = if (value > 0) "+" else ""
    val temperatureUnitSign = context.getString(unit.shortSign)
    val fullString = context.getString(R.string.temperature_value, value.toInt(), temperatureUnitSign)

    return prefix + fullString
}