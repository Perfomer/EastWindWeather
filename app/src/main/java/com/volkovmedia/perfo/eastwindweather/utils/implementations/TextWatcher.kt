package com.volkovmedia.perfo.eastwindweather.utils.implementations

import android.text.Editable
import android.text.TextWatcher

class TextWatcher(private val afterTextChanged: ((Editable) -> Unit)? = null,
                  private val beforeTextChanged: ((CharSequence, Int, Int, Int) -> Unit)? = null,
                  private val onTextChanged: ((CharSequence, Int, Int, Int) -> Unit)? = null)
    : TextWatcher {

    override fun afterTextChanged(text: Editable) {
        afterTextChanged?.invoke(text)
    }

    override fun beforeTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {
        beforeTextChanged?.invoke(text, start, count, after)
    }

    override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        onTextChanged?.invoke(text, start, before, count)
    }

}