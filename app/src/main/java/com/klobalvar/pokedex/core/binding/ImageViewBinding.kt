package com.klobalvar.pokedex.core.binding

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.klobalvar.pokedex.core.base.GlideApp

object ImageViewBinding {

    @JvmStatic
    @BindingAdapter(value = ["glide_url", "glide_background_view"], requireAll = false)
    fun bindGlide(view: ImageView, url: String?, backgroundView: View?) {
        GlideApp.with(view)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .listener(
                backgroundView?.let {
                    GlidePalette.with(url)
                        .use(BitmapPalette.Profile.MUTED_DARK)
                        .intoCallBack { palette ->
                            palette?.darkMutedSwatch?.rgb?.let { rgb ->
                                if (backgroundView is CardView) {
                                    backgroundView.setCardBackgroundColor(rgb)
                                } else {
                                    backgroundView.setBackgroundColor(rgb)
                                }
                            }
                        }.crossfade(true)
                }
            ).into(view)
    }
}