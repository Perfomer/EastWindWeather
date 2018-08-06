package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.citites.mvp

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.data.entities.City
import com.volkovmedia.perfo.eastwindweather.ui.base.BaseFragment
import com.volkovmedia.perfo.eastwindweather.ui.settings.pager.AddExecutor
import com.volkovmedia.perfo.eastwindweather.ui.settings.pager.citites.adapter.SettingsCitiesAdapter
import com.volkovmedia.perfo.eastwindweather.ui.settings.pager.citites.dialog.SettingsCityAddDialog
import kotlinx.android.synthetic.main.settings_list.view.*

class SettingsCitiesFragment : BaseFragment(), AddExecutor, SettingsCitiesView {

    override val layoutResource = R.layout.settings_list

    private val adapter by lazy { SettingsCitiesAdapter(::onItemClicked) }

    private val presenter by lazy { SettingsCitiesPresenter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view.settings_list) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SettingsCitiesFragment.adapter
        }

        presenter.subscribe(this)
    }

    override fun onItemsLoaded(items: List<City>) = adapter.setItems(items)

    override fun onAddItemRequest() = showCitySettingsDialog()

    private fun onItemClicked(city: City) = showCitySettingsDialog(city)

    private fun showCitySettingsDialog(city: City? = null) {
        SettingsCityAddDialog(layoutInflater, presenter::saveCity, presenter::deleteCity, city).show()
    }

    companion object {

        @JvmStatic
        fun newInstance() = SettingsCitiesFragment()

    }

}