package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.weather.dialog

import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Spinner
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.data.entities.Weather
import com.volkovmedia.perfo.eastwindweather.data.entities.other.Temperature
import com.volkovmedia.perfo.eastwindweather.data.entities.partial.CityName
import com.volkovmedia.perfo.eastwindweather.data.entities.relations.CityWeather
import com.volkovmedia.perfo.eastwindweather.ui.base.BaseCustomDialog
import com.volkovmedia.perfo.eastwindweather.utils.constants.Month
import com.volkovmedia.perfo.eastwindweather.utils.constants.TemperatureUnit
import com.volkovmedia.perfo.eastwindweather.utils.extensions.toEditable
import com.volkovmedia.perfo.eastwindweather.utils.implementations.OnItemSelectedListener
import com.volkovmedia.perfo.eastwindweather.utils.implementations.TextWatcher

class SettingsWeatherAddDialog(layoutInflater: LayoutInflater,
                               private val onWeatherAddListener: (Weather) -> Unit,
                               private val onWeatherRemoveListener: (Weather) -> Unit,
                               private val cityWeather: CityWeather? = null)
    : BaseCustomDialog(layoutInflater) {

    var cities: List<CityName> = listOf()
        set(value) {
            field = value
            initCitiesAdapter(value)
        }

    override val layoutResource = R.layout.settings_dialog_weather_add
    override val title = if (cityWeather == null) R.string.settings_dialog_weather_title_add else R.string.settings_dialog_weather_title_edit

    private val cityName by lazy { findViewById<Spinner>(R.id.settings_dialog_weather_name) }
    private val temperature by lazy { findViewById<EditText>(R.id.settings_dialog_weather_temperature) }
    private val temperatureUnit by lazy { findViewById<Spinner>(R.id.settings_dialog_weather_temperature_unit) }
    private val month by lazy { findViewById<Spinner>(R.id.settings_dialog_weather_month) }

    private lateinit var cityNameAdapter: CityNameAdapter

    private var selectedCityId: Long = 0


    /* ======================================================================================== */


    override fun onViewCreated() {
        cityName.onItemSelectedListener = OnItemSelectedListener { _, _, position, _ ->
            selectedCityId = cities[position].id
        }

        temperature.addTextChangedListener(TextWatcher(afterTextChanged = {
            buttonPositive.isEnabled = it.isNotEmpty() && it.toString().toDoubleOrNull() != null
        }))

        cityWeather?.let {
            selectedCityId = it.city.id

            temperature.text = it.weather.temperature.value.toString().toEditable()
            temperatureUnit.setSelection(it.weather.temperature.unit.ordinal)

            month.setSelection(it.weather.month.ordinal)

            with(buttonNeutral) {
                setTextColor(cityName.resources.getColor(android.R.color.holo_red_dark))
                setText(R.string.settings_dialog_delete)

                setOnClickListener {
                    onWeatherRemoveListener(cityWeather.weather)
                    dismiss()
                }
            }
        }


        initButtons()
    }


    /* ======================================================================================== */


    private fun initButtons() = with(buttonPositive) {
        isEnabled = temperature.text.isNotEmpty()

        setOnClickListener {
            val weather = Weather(getWeatherId(), getTemperature(), selectedCityId, getMonth())
            onWeatherAddListener(weather)
            dismiss()
        }
    }

    private fun setCityInformation(item: CityName) = with(item) {
        selectedCityId = id

        val itemIndex = cities.indexOfFirst { it.id == item.id }
        cityName.setSelection(itemIndex)
    }

    private fun initCitiesAdapter(value: List<CityName>) = with(cityName) {
        cityNameAdapter = CityNameAdapter(context, value)
        adapter = cityNameAdapter

        if (selectedCityId != 0L) {
            setCityInformation(value.first { it.id == selectedCityId })
        }
    }


    /* ======================================================================================== */


    private fun getWeatherId() = cityWeather?.weather?.id ?: 0

    private fun getMonth() = Month.values()[month.selectedItemPosition]

    private fun getTemperature(): Temperature {
        val temperature = temperature.text.toString().toDouble()
        val temperatureUnit = TemperatureUnit.values()[temperatureUnit.selectedItemPosition]
        return Temperature(temperature, temperatureUnit)
    }

}