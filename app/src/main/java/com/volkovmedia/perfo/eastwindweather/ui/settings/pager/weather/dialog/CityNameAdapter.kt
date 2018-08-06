package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.weather.dialog

import android.content.Context
import android.widget.ArrayAdapter
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.data.entities.partial.CityName

class CityNameAdapter(context: Context, items: List<CityName>)
    : ArrayAdapter<String>(context, R.layout.settings_spinner_item, items.toStringList()) {

    companion object {
        fun List<CityName>.toStringList() = map { it.name }
    }

}