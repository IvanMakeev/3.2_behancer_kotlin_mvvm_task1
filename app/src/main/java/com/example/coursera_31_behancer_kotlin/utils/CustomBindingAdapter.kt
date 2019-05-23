package com.example.coursera_31_behancer_kotlin.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("bind:imageUrl")
fun loadImage(imageView: ImageView, urlImage: String) {
    Picasso.with(imageView.context).load(urlImage).into(imageView)
}
