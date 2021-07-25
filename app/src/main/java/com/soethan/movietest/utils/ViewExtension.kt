package com.soethan.movietest.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun Context.loadImg(imgUrl: Any?, view: ImageView, @DrawableRes error: Int,
    @DrawableRes placeHolder: Int) {
    Glide.with(this)
        .load(imgUrl)
        .error(error)
        .apply(RequestOptions.placeholderOf(placeHolder))
        .into(view)
}

fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.isVisible(): Boolean {
    if (visibility == View.VISIBLE) {
        return true
    }
    return false
}

fun View.invisible(): View {
    if (visibility != View.GONE) {
        visibility = View.INVISIBLE
    }
    return this
}

fun Context.toast(text: String? = "Something went wrong", duration: Int = Toast.LENGTH_LONG) {
    return this.let {
        Toast.makeText(it, text, duration).show()

    }
}

fun Toolbar.setToolbarAction(activity: AppCompatActivity) {
    (activity as? AppCompatActivity)?.setSupportActionBar(this)
    (activity as? AppCompatActivity)?.supportActionBar?.let {
        it.setDisplayShowHomeEnabled(true)
        it.setDisplayHomeAsUpEnabled(true)
    }
}


