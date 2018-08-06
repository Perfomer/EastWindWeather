package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.citites.adapter

import android.support.annotation.LayoutRes
import android.view.View
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.data.entities.City
import com.volkovmedia.perfo.eastwindweather.ui.base.BaseListAdapter

class SettingsCitiesAdapter(private val onItemClickListener: (City) -> Unit)
    : BaseListAdapter<City, SettingsCityViewHolder>() {

    @LayoutRes
    override val layoutResource = R.layout.settings_item_city

    override fun createViewHolder(view: View) = SettingsCityViewHolder(view, onItemClickListener)

    override fun onBindViewHolder(viewHolder: SettingsCityViewHolder, item: City, position: Int) {
        viewHolder.bind(item)
    }

}