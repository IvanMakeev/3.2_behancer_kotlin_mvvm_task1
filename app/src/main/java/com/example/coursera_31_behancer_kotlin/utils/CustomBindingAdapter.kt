package com.example.coursera_31_behancer_kotlin.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.*
import android.widget.ImageView
import com.example.coursera_31_behancer_kotlin.data.model.project.Project
import com.example.coursera_31_behancer_kotlin.data.model.project.RichProject
import com.example.coursera_31_behancer_kotlin.ui.projects.ProjectsAdapter
import com.squareup.picasso.Picasso

//если использовать extension function то определять imageView в сигнатуре метода не нужно, вместо этого используется this
//если определять как статические метод вне класс, то нужно определять imageView в сигнатуре метода

@BindingAdapter("bind:imageUrl")
fun ImageView.loadImage(urlImage: String) {
    Picasso.with(this.context).load(urlImage).into(this)
}

@BindingAdapter("bind:data", "bind:clickHandler")
fun configureRecyclerView(
    recyclerView: RecyclerView,
    project: LiveData<List<RichProject>>,
    listener: ProjectsAdapter.OnItemClickListener
) {
    val adapter = ProjectsAdapter(project.value ?: ArrayList<RichProject>(), listener)
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = adapter
}

@BindingAdapter("bind:refreshState", "bind:onRefresh")
fun configureSwipeRefreshLayout(
    layout: SwipeRefreshLayout,
    isLoading: Boolean,
    listener: SwipeRefreshLayout.OnRefreshListener
) {
    layout.setOnRefreshListener(listener)
    layout.post { layout.isRefreshing = isLoading }
}

@BindingAdapter("bind:visibleOrGone")
fun View.setVisibleOrGone(hide: Boolean) {
    visibility = if (hide) GONE else VISIBLE
}

@BindingAdapter("visible")
fun View.setVisible(show: Boolean) {
    visibility = if (show) VISIBLE else INVISIBLE
}
