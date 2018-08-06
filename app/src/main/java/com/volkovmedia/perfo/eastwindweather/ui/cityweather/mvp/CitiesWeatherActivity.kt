package com.volkovmedia.perfo.eastwindweather.ui.cityweather.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.SeasonWeather
import com.volkovmedia.perfo.eastwindweather.ui.base.ViewProvider
import com.volkovmedia.perfo.eastwindweather.ui.cityweather.adapter.CitiesWeatherAdapter
import com.volkovmedia.perfo.eastwindweather.ui.cityweather.filter.CitiesWeatherFilterManager
import com.volkovmedia.perfo.eastwindweather.ui.settings.SettingsActivity
import com.volkovmedia.perfo.eastwindweather.utils.constants.Season
import com.volkovmedia.perfo.eastwindweather.utils.constants.TemperatureUnit
import com.volkovmedia.perfo.eastwindweather.utils.extensions.startActivity
import com.volkovmedia.perfo.eastwindweather.utils.extensions.toast
import kotlinx.android.synthetic.main.cityweather_activity.*

class CitiesWeatherActivity : AppCompatActivity(), CitiesWeatherView, ViewProvider {

    private val presenter by lazy { CitiesWeatherPresenter(this) }

    private val adapter by lazy { CitiesWeatherAdapter() }

    private val filterManager by lazy {
        CitiesWeatherFilterManager(this, ::onSeasonSelected, ::onTemperatureUnitSelected, ::onFilterClosedWithoutSaving)
    }

    private val seasonName by lazy { cityweather_info_season_name }

    private val seasonIcon by lazy { cityweather_info_season_icon }

    private val temperatureUnit by lazy { cityweather_info_temperature_unit }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cityweather_activity)

        with(cityweather_list) {
            layoutManager = LinearLayoutManager(this@CitiesWeatherActivity)
            adapter = this@CitiesWeatherActivity.adapter
        }

        presenter.subscribe(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.cityweather_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_cityweather_filter -> filterManager.isOpened = !filterManager.isOpened
            R.id.menu_cityweather_settings -> startActivity<SettingsActivity>()
        }

        return true
    }

    override fun onResume() {
        super.onResume()

        val season = presenter.currentSeason
        val temperatureUnit = presenter.currentTemperatureUnit

        with(filterManager) {
            isOpened = false
            currentSeason = season.ordinal
            currentTemperatureUnit = temperatureUnit.ordinal
        }

        viewSeasonData(season)
        viewTemperatureUnitData(temperatureUnit)
    }

    private fun onFilterClosedWithoutSaving() {
        toast(getString(R.string.cityweather_filter_nochanges))
    }

    private fun onSeasonSelected(season: Season) {
        viewSeasonData(season)
        presenter.currentSeason = season
    }

    private fun onTemperatureUnitSelected(temperatureUnit: TemperatureUnit) {
        viewTemperatureUnitData(temperatureUnit)
        presenter.currentTemperatureUnit = temperatureUnit
    }

    private fun viewTemperatureUnitData(temperatureUnit: TemperatureUnit) {
        this.temperatureUnit.setText(temperatureUnit.fullSign)
    }

    private fun viewSeasonData(season: Season) {
        seasonIcon.setImageResource(season.iconRes)
        seasonName.setText(season.nameRes)
    }

    override fun onItemsLoaded(items: List<SeasonWeather>) {
        adapter.setItems(items)
    }

}