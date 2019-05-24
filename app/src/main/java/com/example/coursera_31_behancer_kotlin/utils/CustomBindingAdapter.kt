package com.example.coursera_31_behancer_kotlin.utils

import android.databinding.BindingAdapter
import android.view.View
import android.view.View.*
import android.widget.ImageView
import com.squareup.picasso.Picasso

//если использовать extension function то определять imageView в сигнатуре метода не нужно, вместо этого используется this
//если определять как статические метод вне класс, то нужно определять imageView в сигнатуре метода

@BindingAdapter("bind:imageUrl")
fun ImageView.loadImage(urlImage: String) {
    Picasso.with(this.context).load(urlImage).into(this)
}

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) VISIBLE else GONE
}

@BindingAdapter("visible")
fun View.setVisible(show: Boolean) {
    visibility = if (show) VISIBLE else INVISIBLE
}
