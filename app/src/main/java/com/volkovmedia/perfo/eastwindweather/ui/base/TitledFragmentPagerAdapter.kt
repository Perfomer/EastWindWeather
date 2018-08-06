package com.volkovmedia.perfo.eastwindweather.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TitledFragmentPagerAdapter(fragmentManager: FragmentManager,
                                 private val context: Context,
                                 private val fragments: List<Pair<Fragment, Int>>)
    : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = fragments[position].first

    override fun getPageTitle(position: Int) = context.getString(fragments[position].second)

    override fun getCount() = fragments.size

}