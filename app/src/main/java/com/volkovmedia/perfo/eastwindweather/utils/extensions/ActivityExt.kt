package com.volkovmedia.perfo.eastwindweather.utils.extensions

import android.app.Activity
import android.content.Intent

inline fun <reified T: Activity> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T: Activity> Activity.toActivity() {
    startActivity<T>()
    finish()
}