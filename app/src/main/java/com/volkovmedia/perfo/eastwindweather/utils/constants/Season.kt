package com.volkovmedia.perfo.eastwindweather.utils.constants

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.utils.constants.Month.*

enum class Season(@DrawableRes val iconRes: Int, @StringRes val nameRes: Int) {

    WINTER(R.drawable.ic_large_season_winter, R.string.season_winter) {
        override val months by lazy { listOf(DECEMBER, JANUARY, FEBRUARY) }
    },
    SPRING(R.drawable.ic_large_season_spring, R.string.season_spring) {
        override val months by lazy { listOf(MARCH, APRIL, MAY) }
    },
    SUMMER(R.drawable.ic_large_season_summer, R.string.season_summer) {
        override val months by lazy { listOf(JUNE, JULY, AUGUST) }
    },
    AUTUMN(R.drawable.ic_large_season_autumn, R.string.season_autumn) {
        override val months by lazy { listOf(SEPTEMBER, OCTOBER, NOVEMBER) }
    };

    abstract val months: List<Month>

}