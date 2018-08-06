package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.citites.mvp

import com.volkovmedia.perfo.eastwindweather.data.entities.City

internal interface SettingsCitiesView {

    fun onItemsLoaded(items: List<City>)

}