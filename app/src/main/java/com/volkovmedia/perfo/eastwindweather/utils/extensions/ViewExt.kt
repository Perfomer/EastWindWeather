package com.volkovmedia.perfo.eastwindweather.utils.extensions

import android.view.View
import android.view.ViewGroup

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

operator fun ViewGroup.iterator() = ViewIterator(this)

class ViewIterator(private val view: ViewGroup) : Iterator<View> {

    private var currentPosition: Int = 0

    override fun hasNext(): Boolean = currentPosition < view.childCount

    override fun next(): View {
        return view.getChildAt(currentPosition++)
    }

}