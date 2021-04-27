package com.klobalvar.pokedex.core.binding

import androidx.databinding.BindingAdapter
import com.skydoves.progressview.ProgressView

object ProgressViewBinding {

    @JvmStatic
    @BindingAdapter("progressView_progress")
    fun bindProgress(view: ProgressView, progress: Int?) {
        progress?.let {
            view.progress = it.toFloat()
        }
    }

    @JvmStatic
    @BindingAdapter("progressView_max")
    fun bindMax(view: ProgressView, max: Int?) {
        max?.let {
            view.max = it.toFloat()
        }
    }

    @JvmStatic
    @BindingAdapter("progressView_labelText")
    fun bindLabelText(view: ProgressView, labelText: String?) {
        view.labelText = labelText
    }
}