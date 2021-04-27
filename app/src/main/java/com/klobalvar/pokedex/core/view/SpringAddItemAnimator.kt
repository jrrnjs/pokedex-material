package com.klobalvar.pokedex.core.view

import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.klobalvar.pokedex.core.extensions.spring

class SpringAddItemAnimator : DefaultItemAnimator() {

    private val pendingAdds = mutableListOf<RecyclerView.ViewHolder>()

    /**
     * Setup initial values to animate. Derive initial translationY from the view's bottom to
     * produce a stagger effect, as lower items arrive from father displaced.
     */
    override fun animateAdd(holder: RecyclerView.ViewHolder): Boolean {
        holder.itemView.alpha = 0f
        holder.itemView.translationY = holder.itemView.bottom / 3f
        pendingAdds.add(holder)
        return true
    }

    /**
     * Animate back to full alpha and no translation.
     */
    override fun runPendingAnimations() {
        super.runPendingAnimations()
        if (pendingAdds.isNotEmpty()) {
            for (i in pendingAdds.indices.reversed()) {
                val holder = pendingAdds[i]

                val tySpring = holder.itemView.spring(
                    SpringAnimation.TRANSLATION_Y,
                    stiffness = 350f,
                    damping = 0.6f
                )
                val aSpring = holder.itemView.spring(
                    SpringAnimation.ALPHA,
                    stiffness = 100f,
                    damping = SpringForce.DAMPING_RATIO_NO_BOUNCY
                )

                listenForAllSpringsEnd(
                    { cancelled ->
                        if (!cancelled) {
                            dispatchAddFinished(holder)
                            dispatchFinishedWhenDone()
                        } else {
                            clearAnimatedValues(holder.itemView)
                        }
                    },
                    tySpring, aSpring
                )
                dispatchAddStarting(holder)
                aSpring.animateToFinalPosition(1f)
                tySpring.animateToFinalPosition(0f)
                pendingAdds.removeAt(i)
            }
        }
    }

    override fun endAnimation(holder: RecyclerView.ViewHolder) {
        holder.itemView.spring(SpringAnimation.TRANSLATION_Y).cancel()
        holder.itemView.spring(SpringAnimation.ALPHA).cancel()
        if (pendingAdds.remove(holder)) {
            dispatchAddFinished(holder)
            clearAnimatedValues(holder.itemView)
        }
        super.endAnimation(holder)
    }

    override fun endAnimations() {
        for (i in pendingAdds.indices.reversed()) {
            val holder = pendingAdds[i]
            clearAnimatedValues(holder.itemView)
            dispatchAddFinished(holder)
            pendingAdds.removeAt(i)
        }
        super.endAnimations()
    }

    override fun isRunning(): Boolean {
        return pendingAdds.isNotEmpty() || super.isRunning()
    }

    private fun dispatchFinishedWhenDone() {
        if (!isRunning) {
            dispatchAnimationsFinished()
        }
    }

    private fun clearAnimatedValues(view: View) {
        view.alpha = 1f
        view.translationY = 0f
    }

    /**
     * A class which adds [DynamicAnimation.OnAnimationEndListener]s to the given `springs` and invokes
     * `onEnd` when all have finished.
     */
    class MultiSpringEndListener(
        onEnd: (Boolean) -> Unit,
        vararg springs: SpringAnimation
    ) {
        private val listeners = ArrayList<DynamicAnimation.OnAnimationEndListener>(springs.size)

        private var wasCancelled = false

        init {
            springs.forEach {
                val listener = object : DynamicAnimation.OnAnimationEndListener {
                    override fun onAnimationEnd(
                        animation: DynamicAnimation<out DynamicAnimation<*>>?,
                        canceled: Boolean,
                        value: Float,
                        velocity: Float
                    ) {
                        animation?.removeEndListener(this)
                        wasCancelled = wasCancelled or canceled
                        listeners.remove(this)
                        if (listeners.isEmpty()) {
                            onEnd(wasCancelled)
                        }
                    }
                }
                it.addEndListener(listener)
                listeners.add(listener)
            }
        }
    }

    private fun listenForAllSpringsEnd(
        onEnd: (Boolean) -> Unit,
        vararg springs: SpringAnimation
    ) = MultiSpringEndListener(onEnd, *springs)
}
