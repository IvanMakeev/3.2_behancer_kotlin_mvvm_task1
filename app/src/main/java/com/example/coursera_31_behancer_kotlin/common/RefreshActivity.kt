package com.example.coursera_31_behancer_kotlin.common

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import com.example.coursera_31_behancer_kotlin.R

abstract class RefreshActivity : SingleFragmentActivity(), SwipeRefreshLayout.OnRefreshListener, RefreshOwner {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        swipeRefreshLayout = findViewById(R.id.refresher)
        swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun getLayout(): Int {
        return R.layout.ac_swipe_container
    }

    override fun onRefresh() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment is Refreshable) {
            fragment.onRefreshData()
        } else {
            setRefreshState(false)
        }
    }

    override fun setRefreshState(refreshing: Boolean) {
        swipeRefreshLayout.post { swipeRefreshLayout.isRefreshing = refreshing }
    }
}