package com.volkovmedia.perfo.eastwindweather.utils.extensions

import android.text.Editable

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)