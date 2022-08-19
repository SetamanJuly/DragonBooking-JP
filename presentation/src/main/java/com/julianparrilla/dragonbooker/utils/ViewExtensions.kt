package com.julianparrilla.dragonbooker.utils

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visibleOrGone(visible: Boolean) {
    if (visible) visible() else gone()
}

infix fun <T : View> T.onClick(f: (T) -> Unit) =
    setOnClickListener(
        SafeClickListener { f(this) }
    )