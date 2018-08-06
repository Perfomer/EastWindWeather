package com.volkovmedia.perfo.eastwindweather.utils.constants

import android.support.annotation.StringRes
import com.volkovmedia.perfo.eastwindweather.R

enum class CitySize(@StringRes val sizeName: Int) {

    SMALL(R.string.city_size_small),
    MEDIUM(R.string.city_size_medium),
    BIG(R.string.city_size_big);

}