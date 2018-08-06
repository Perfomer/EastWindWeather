package com.volkovmedia.perfo.eastwindweather.ui.settings.pager.citites.dialog

import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Spinner
import com.volkovmedia.perfo.eastwindweather.R
import com.volkovmedia.perfo.eastwindweather.data.entities.City
import com.volkovmedia.perfo.eastwindweather.ui.base.BaseCustomDialog
import com.volkovmedia.perfo.eastwindweather.utils.constants.CitySize
import com.volkovmedia.perfo.eastwindweather.utils.extensions.toEditable
import com.volkovmedia.perfo.eastwindweather.utils.implementations.TextWatcher

class SettingsCityAddDialog(layoutInflater: LayoutInflater,
                            private val onCityAddListener: (City) -> Unit,
                            private val onCityRemoveListener: (City) -> Unit,
                            private val city: City? = null)
    : BaseCustomDialog(layoutInflater) {

    override val layoutResource = R.layout.settings_dialog_cities_add
    override val title = if (city == null) R.string.settings_dialog_city_title_add else R.string.settings_dialog_city_title_edit

    private val cityName by lazy { findViewById<EditText>(R.id.settings_dialog_city_name) }
    private val citySize by lazy { findViewById<Spinner>(R.id.settings_dialog_city_size) }

    override fun onViewCreated() {
        with(buttonPositive) {
            isEnabled = false

            setOnClickListener {
                val city = City(getCityId(), getCitySize(), getCityName())
                onCityAddListener(city)
                dismiss()
            }
        }

        cityName.addTextChangedListener(TextWatcher(afterTextChanged = {
            buttonPositive.isEnabled = it.isNotEmpty()
        }))

        city?.let {
            cityName.text = it.name.toEditable()
            citySize.setSelection(it.size.ordinal)

            with(buttonNeutral) {
                setTextColor(cityName.resources.getColor(android.R.color.holo_red_dark))
                setText(R.string.settings_dialog_delete)

                setOnClickListener {
                    onCityRemoveListener(city)
                    dismiss()
                }
            }
        }
    }

    private fun getCityId() = city?.id ?: 0

    private fun getCityName() = cityName.text.toString()

    private fun getCitySize() = CitySize.values()[citySize.selectedItemPosition]

}