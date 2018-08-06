package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.weather.mvp

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.data.entities.partial.CityName
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.CityWeather
import com.volkovmedia.perfo.eastwindweather.ui.base.BaseFragment
import com.volkovmedia.perfo.eastwindweather.ui.settings.pager.AddExecutor
import com.volkovmedia.perfo.eastwindweather.ui.settings.pager.weather.adapter.SettingsWeatherAdapter
import com.volkovmedia.perfo.eastwindweather.ui.settings.pager.weather.dialog.SettingsWeatherAddDialog
import kotlinx.android.synthetic.main.settings_list.view.*

class SettingsWeatherFragment : BaseFragment(), AddExecutor, SettingsWeatherView {

    override val layoutResource = R.layout.settings_list

    private val adapter by lazy { SettingsWeatherAdapter(::onItemClicked) }

    private val presenter by lazy { SettingsWeatherPresenter(this) }

    private var dialog: SettingsWeatherAddDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view.settings_list) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SettingsWeatherFragment.adapter
        }

        presenter.subscribe(this)
    }

    override fun onAddItemRequest() = showWeatherSettingsDialog()

    override fun onWeatherLoaded(items: List<CityWeather>) = adapter.setItems(items)

    override fun onCitiesNamesLoaded(items: List<CityName>) {
        dialog?.cities = items
    }

    private fun onItemClicked(weather: CityWeather) = showWeatherSettingsDialog(weather)

    private fun showWeatherSettingsDialog(weather: CityWeather? = null) {
        dialog = SettingsWeatherAddDialog(layoutInflater, presenter::saveWeather, presenter::deleteWeather, weather).apply {
            presenter.requestCitiesNames()
            show()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = SettingsWeatherFragment()

    }

}