package com.julianparrilla.dragonbooker.utils

import android.R
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


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

fun ImageView.loadCrop(url: String) {
    val options: RequestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.mipmap.sym_def_app_icon)
        .error(R.mipmap.sym_def_app_icon)

    Glide.with(this.context).load(url).apply(options).into(this)
}

fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)