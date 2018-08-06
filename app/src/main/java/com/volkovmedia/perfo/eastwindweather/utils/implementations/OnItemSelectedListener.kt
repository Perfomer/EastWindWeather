package com.volkovmedia.perfo.eastwindweather.utils.implementations

import android.view.View
import android.widget.AdapterView

class OnItemSelectedListener(private val onNothingSelected: ((AdapterView<*>) -> Unit)? = null,
                             private val onItemSelected: ((AdapterView<*>, View, Int, Long) -> Unit)? = null)
    : AdapterView.OnItemSelectedListener {


    override fun onNothingSelected(parent: AdapterView<*>) {
        onNothingSelected?.invoke(parent)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        onItemSelected?.invoke(parent, view, position, id)
    }


}