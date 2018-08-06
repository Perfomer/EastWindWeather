package com.volkovmedia.perfo.eastwindweather.utils.extensions

import android.content.Context
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.utils.constants.CitySize

fun CitySize.getFullString(context: Context) = context.getString(R.string.city_size, context.getString(sizeName))