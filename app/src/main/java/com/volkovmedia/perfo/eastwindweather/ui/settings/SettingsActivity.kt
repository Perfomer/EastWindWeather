package com.volkovmedia.perfo.eastwindweather.ui.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.ui.base.TitledFragmentPagerAdapter
import com.volkovmedia.perfo.eastwindweather.ui.settings.pager.AddExecutor
import com.volkovmedia.perfo.eastwindweather.ui.settings.pager.citites.mvp.SettingsCitiesFragment
import com.volkovmedia.perfo.eastwindweather.ui.settings.pager.weather.mvp.SettingsWeatherFragment
import kotlinx.android.synthetic.main.settings_activity.*

class SettingsActivity : AppCompatActivity() {

    private val citiesFragment by lazy { SettingsCitiesFragment.newInstance() }
    private val weatherFragment by lazy { SettingsWeatherFragment.newInstance() }

    private val viewPager by lazy { settings_viewpager }

    private val pagerAdapter by lazy {
        TitledFragmentPagerAdapter(supportFragmentManager, this,
                listOf(citiesFragment to R.string.settings_tab_cities,
                        weatherFragment to R.string.settings_tab_weather)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        setSupportActionBar(settings_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager.adapter = pagerAdapter
        settings_tablayout.setupWithViewPager(viewPager)

        settings_fab.setOnClickListener {
            val addExecutor = pagerAdapter.getItem(viewPager.currentItem) as AddExecutor
            addExecutor.onAddItemRequest()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}