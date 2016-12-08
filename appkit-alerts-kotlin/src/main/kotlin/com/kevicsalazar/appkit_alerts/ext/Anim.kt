package com.kevicsalazar.appkit_alerts.ext

import android.animation.*
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator

/**
 * alpha, rotation, rotationX, rotationY, sacaleX, scaleY, translationX, translationY, translationZ, x, y, z
 */

fun View.anim(propertyName: String, vararg values: Float, init: (ObjectAnimator.() -> Unit)?) = ObjectAnimator.ofFloat(this, propertyName, *values).apply {
    if (init != null) init()
}!!

fun playTogether(vararg animators: ObjectAnimator, startDelay: Long = 0, viewToRestore: View? = null, repeatCount: Int = 0): AnimatorSet {
    val animatorSet = AnimatorSet()
    animatorSet.playTogether(*animators)
    animatorSet.startDelay = startDelay
    if (repeatCount == ValueAnimator.INFINITE) {
        animatorSet.onAnimationEnd {
            viewToRestore!!.alpha = 1f
            viewToRestore.translationX = 0f
            viewToRestore.translationY = 0f
            viewToRestore.scaleX = 1f
            viewToRestore.scaleY = 1f
            animatorSet.start()
        }
    }
    return animatorSet
}

fun AnimatorSet.onAnimationEnd(onAnimationEnd: () -> Unit) {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            onAnimationEnd()
        }
    })
}

fun Animation.onAnimationEnd(onAnimationEnd: () -> Unit) {
    setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(p0: Animation?) {

        }

        override fun onAnimationEnd(p0: Animation?) {
            onAnimationEnd()
        }

        override fun onAnimationStart(p0: Animation?) {

        }
    })
}