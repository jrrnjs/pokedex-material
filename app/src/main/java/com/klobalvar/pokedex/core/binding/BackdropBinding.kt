package com.klobalvar.pokedex.core.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.klobalvar.pokedex.ui.main.BackdropNavigationClickListener

object BackdropBinding {

    @JvmStatic
    @BindingAdapter("onNavigationClick")
    fun bindOnNavigationClick(view: MaterialToolbar, navigationClickListener: BackdropNavigationClickListener?) {
        view.setNavigationOnClickListener(navigationClickListener)
    }

    @JvmStatic
    @BindingAdapter("onBackdropClick")
    fun bindOnBackdropClick(view: View, hideBackdrop: (() -> Unit)?) {
        view.setOnClickListener {
            hideBackdrop?.invoke()
        }
    }
}