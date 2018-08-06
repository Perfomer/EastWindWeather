package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.citites.adapter

import android.view.View
import com.volkovmedia.perfo.eastwindweather.data.entities.City
import com.volkovmedia.perfo.eastwindweather.ui.base.BaseViewHolder
import com.volkovmedia.perfo.eastwindweather.utils.extensions.getFullString
import kotlinx.android.synthetic.main.settings_item_city.view.*

class SettingsCityViewHolder(itemView: View,
                             onItemClickListener: (City) -> Unit)
    : BaseViewHolder(itemView) {

    private val cityName by lazy { itemView.settings_item_city_name }
    private val citySize by lazy { itemView.settings_item_city_size }

    private lateinit var city: City

    init {
        itemView.setOnClickListener { onItemClickListener(city) }
    }

    fun bind(city: City) {
        with(city) {
            this@SettingsCityViewHolder.city = this

            citySize.text = size.getFullString(context)
            cityName.text = name
        }
    }

}