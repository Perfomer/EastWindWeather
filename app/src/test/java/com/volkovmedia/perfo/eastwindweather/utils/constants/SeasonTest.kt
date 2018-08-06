package com.volkovmedia.perfo.eastwindweather.utils.constants

import com.volkovmedia.perfo.eastwindweather.utils.constants.Month.*
import org.junit.Assert.assertEquals
import org.junit.Test

class SeasonTest {

    @Test
    fun getMonths() {
        assertEquals(Season.WINTER.months, listOf(DECEMBER, JANUARY, FEBRUARY))
        assertEquals(Season.SPRING.months, listOf(MARCH, APRIL, MAY))
        assertEquals(Season.SUMMER.months, listOf(JUNE, JULY, AUGUST))
        assertEquals(Season.AUTUMN.months, listOf(SEPTEMBER, OCTOBER, NOVEMBER))
    }
}