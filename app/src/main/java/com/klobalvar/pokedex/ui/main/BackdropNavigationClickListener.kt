package com.klobalvar.pokedex.ui.main

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import com.google.android.material.card.MaterialCardView


class BackdropNavigationClickListener internal constructor(
    private val backdrop: ConstraintLayout,
    private val sheet: MaterialCardView,
    private val openIcon: Drawable?,
    private val closeIcon: Drawable?
) : View.OnClickListener {

    private lateinit var iconImageView: ImageView
    private var animatorSet = AnimatorSet()
    private var backdropShown = false
    private var sheetTransitionRange = 0f

    init {
        backdrop.doOnLayout {
            sheetTransitionRange = (backdrop.height - backdrop.paddingBottom).toFloat()
        }
    }

    override fun onClick(view: View) {
        if (!::iconImageView.isInitialized) {
            if (view !is ImageView) {
                throw IllegalArgumentException("updateIcon() must be called on an ImageView")
            }
            iconImageView = view
        }

        if (sheetTransitionRange == 0f) {
            return
        }

        if (backdropShown) {
            hideBackdrop()
        } else {
            showBackdrop()
        }
    }

    private fun updateIcon() {
        if (!::iconImageView.isInitialized) {
            return
        }

        if (openIcon != null && closeIcon != null) {
            if (backdropShown) {
                iconImageView.setImageDrawable(closeIcon)
            } else {
                iconImageView.setImageDrawable(openIcon)
            }
        }
    }

    private fun showBackdrop() {
        backdropShown = true
        updateIcon()

        animatorSet.end()
        animatorSet.cancel()
        animatorSet = AnimatorSet().apply {
            playTogether(
                sheetAnimator(0f, sheetTransitionRange),
                backdropAnimator()
            )
        }
        animatorSet.start()
    }

    fun hideBackdrop() {
        backdropShown = false
        updateIcon()

        animatorSet.end()
        animatorSet.cancel()
        animatorSet = AnimatorSet().apply {
            play(sheetAnimator(sheetTransitionRange, 0f))
        }
        animatorSet.start()
    }

    private fun sheetAnimator(start: Float, end: Float): ObjectAnimator =
        ObjectAnimator.ofFloat(
            sheet, "translationY", start, end
        ).apply {
            duration = 300
            interpolator = AccelerateDecelerateInterpolator()
        }

    private fun backdropAnimator(): ObjectAnimator =
        ObjectAnimator.ofPropertyValuesHolder(
            backdrop.getChildAt(0),
            PropertyValuesHolder.ofFloat("translationX", -200f, 0f),
            PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
        ).apply {
            duration = 500
            interpolator = DecelerateInterpolator()
        }
}
