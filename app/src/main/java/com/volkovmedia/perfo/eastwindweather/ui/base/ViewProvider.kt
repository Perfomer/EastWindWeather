package com.volkovmedia.perfo.eastwindweather.ui.base

import android.support.annotation.IdRes
import android.view.View

interface ViewProvider {
    fun <T : View> findViewById(@IdRes id: Int): T
}