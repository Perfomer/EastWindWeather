package com.volkovmedia.perfo.eastwindweather.ui.base

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

abstract class BaseListAdapter<Item, ViewHolder : RecyclerView.ViewHolder> : RecyclerView.Adapter<ViewHolder>() {

    private var items: List<Item> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(layoutResource, parent, false)
        return createViewHolder(v)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        onBindViewHolder(viewHolder, items[position], position)
    }

    fun setItems(items: List<Item>) {
        val callback = ListDiffUtilCallback(this.items, items)
        val diffResult = async { DiffUtil.calculateDiff(callback) }

        launch(UI) {
            this@BaseListAdapter.items = items

            diffResult.await().dispatchUpdatesTo(this@BaseListAdapter)
        }
    }

    protected abstract val layoutResource: Int

    protected abstract fun createViewHolder(view: View): ViewHolder

    protected abstract fun onBindViewHolder(viewHolder: ViewHolder, item: Item, position: Int)

}