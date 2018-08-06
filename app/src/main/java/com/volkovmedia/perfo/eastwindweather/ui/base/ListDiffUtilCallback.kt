package com.volkovmedia.perfo.eastwindweather.ui.base

import android.support.v7.util.DiffUtil

class ListDiffUtilCallback<T>(private val oldList: List<T>,
                           private val newList: List<T>)
    : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = (oldList[oldItemPosition] === newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = (oldList[oldItemPosition] == newList[newItemPosition])

}