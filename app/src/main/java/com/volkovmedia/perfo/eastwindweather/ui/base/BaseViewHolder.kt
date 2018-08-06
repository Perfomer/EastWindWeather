package com.volkovmedia.perfo.eastwindweather.ui.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val context: Context by lazy {
        itemView.context
    }

}