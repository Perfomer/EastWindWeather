package com.volkovmedia.perfo.eastwindweather.ui.base

import android.app.Dialog
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.support.annotation.IdRes
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import com.volkovmedia.perfo.eastwindweather.utils.extensions.visible

abstract class BaseCustomDialog(layoutInflater: LayoutInflater) : LifecycleOwner {

    protected val buttonPositive by lazy { dialog.getButton(Dialog.BUTTON_POSITIVE) }
    protected val buttonNegative by lazy { dialog.getButton(Dialog.BUTTON_NEGATIVE) }

    protected val buttonNeutral by lazy {
        dialog.getButton(Dialog.BUTTON_NEUTRAL).apply {
            visible = true
        }
    }

    protected abstract val layoutResource: Int
    protected abstract val title: Int

    private val rootView by lazy { layoutInflater.inflate(layoutResource, null) }

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    private lateinit var dialog: AlertDialog

    override fun getLifecycle() = lifecycleRegistry

    fun show() {
        dialog = AlertDialog.Builder(rootView.context)
                .setView(rootView)
                .setTitle(title)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .setOnDismissListener { lifecycleRegistry.markState(Lifecycle.State.DESTROYED) }
                .create()

        lifecycleRegistry.markState(Lifecycle.State.CREATED)

        dialog.show()

        lifecycleRegistry.markState(Lifecycle.State.STARTED)

        onViewCreated()
    }

    protected abstract fun onViewCreated()

    protected fun <T : View> findViewById(@IdRes id: Int): T {
        return rootView.findViewById<T>(id)
    }

    protected fun dismiss() = dialog.dismiss()

}