package com.volkovmedia.perfo.eastwindweather.ui.cityweather.filter

import android.support.transition.*
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.ui.base.ViewProvider
import com.volkovmedia.perfo.eastwindweather.utils.constants.Season
import com.volkovmedia.perfo.eastwindweather.utils.constants.TemperatureUnit
import com.volkovmedia.perfo.eastwindweather.utils.extensions.iterator
import com.volkovmedia.perfo.eastwindweather.utils.extensions.visible

class CitiesWeatherFilterManager(viewProvider: ViewProvider,
                                 onSeasonClickListener: (Season) -> Unit,
                                 onTemperatureUnitClickListener: (TemperatureUnit) -> Unit,
                                 private val onClosedWithoutSavingListener: () -> Unit) {

    var isOpened = false
        set(value) {
            field = value
            showLayout(value)
        }

    var currentSeason = 0
        set(value) {
            field = value
            seasonsButtons.selectButton(value)
        }

    var currentTemperatureUnit = 0
        set(value) {
            field = value
            temperatureUnitsButtons.selectButton(value)
        }

    private val layoutBackground by lazy { viewProvider.findViewById<View>(R.id.cityweather_filter_background) }

    private val layoutWrap by lazy { viewProvider.findViewById<ViewGroup>(R.id.cityweather_filter_wrap) } // for animation
    private val layout by lazy { viewProvider.findViewById<ViewGroup>(R.id.cityweather_filter) }

    private val seasonsButtons by lazy { getButtonsList(viewProvider, R.id.cityweather_filter_seasons) }
    private val temperatureUnitsButtons by lazy { getButtonsList(viewProvider, R.id.cityweather_filter_temperatureunits) }

    private val applyButton by lazy { viewProvider.findViewById<View>(R.id.cityweather_filter_apply) }

    private var selectedSeason = currentSeason
    private var selectedTemperatureUnit = currentTemperatureUnit

    init {
        backgroundColorSelected = applyButton.resources.getColor(R.color.black_transparent_light)

        seasonsButtons.setButtonsOnClickListeners {
            selectedSeason = it
        }

        temperatureUnitsButtons.setButtonsOnClickListeners {
            selectedTemperatureUnit = it
        }

        applyButton.setOnClickListener {
            if (isSeasonUpdated()) {
                currentSeason = selectedSeason
                onSeasonClickListener(Season.values()[currentSeason])
            }

            if (isTemperatureUnitUpdated()) {
                currentTemperatureUnit = selectedTemperatureUnit
                onTemperatureUnitClickListener(TemperatureUnit.values()[currentTemperatureUnit])
            }

            isOpened = false
        }

        layoutBackground.setOnClickListener { isOpened = false }
    }

    private fun showLayout(show: Boolean) {
        if (show) {
            selectedSeason = currentSeason
            selectedTemperatureUnit = currentTemperatureUnit

            seasonsButtons.selectButton(currentSeason)
            temperatureUnitsButtons.selectButton(currentTemperatureUnit)
        } else if (isTemperatureUnitUpdated() || isSeasonUpdated()) {
            onClosedWithoutSavingListener()
        }

        startAnimationTransition(show)

        layout.visible = show
        layoutBackground.visible = show
    }

    private fun isSeasonUpdated() = currentSeason != selectedSeason
    private fun isTemperatureUnitUpdated() = currentTemperatureUnit != selectedTemperatureUnit

    private fun startAnimationTransition(show: Boolean) {
        TransitionManager.beginDelayedTransition(layoutWrap, TransitionSet().apply {
            addTransition(Slide(Gravity.TOP).addTarget(layout))
            addTransition(Fade(if (show) Visibility.MODE_IN else Visibility.MODE_OUT).addTarget(layoutBackground))
            duration = 250
        })
    }

    private companion object {

        private var backgroundColorSelected: Int = 0

        private fun List<View>.setButtonsOnClickListeners(onClickListener: (index: Int) -> Unit) {
            forEachIndexed { i, view ->
                view.setOnClickListener {
                    selectButton(i)
                    onClickListener(i)
                }
            }
        }

        private fun List<View>.selectButton(index: Int) {
            forEachIndexed { i, view ->
                with(view) {
                    if (i == index) {
                        setBackgroundColor(backgroundColorSelected)
                        alpha = 1.0f
                    } else {
                        background = null
                        alpha = 0.4f
                    }
                }
            }
        }

        private fun getButtonsList(viewProvider: ViewProvider, layoutResource: Int) = mutableListOf<View>().apply {
            val parent = viewProvider.findViewById<ViewGroup>(layoutResource)

            for (child in parent) {
                add(child)
            }
        }

    }

}