package com.example.coursera_31_behancer_kotlin.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import com.example.coursera_31_behancer_kotlin.AppDelegate
import com.example.coursera_31_behancer_kotlin.R
import com.example.coursera_31_behancer_kotlin.data.Storage

abstract class SingleFragmentActivity : AppCompatActivity(), Storage.StorageOwner, SwipeRefreshLayout.OnRefreshListener,
    RefreshOwner {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    protected abstract fun getFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_swipe_container)
        swipeRefreshLayout = findViewById(R.id.refresher)
        swipeRefreshLayout.setOnRefreshListener(this)

        if (savedInstanceState == null) {
            changeFragment(getFragment())
        }

    }

    override fun obtainStorage(): Storage {
        return (applicationContext as AppDelegate).storage!!
    }

    private fun changeFragment(fragment: Fragment) {
        val addToBackStack = supportFragmentManager.findFragmentById(R.id.fragmentContainer) != null

        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(fragment.javaClass.simpleName)
        }

        transaction.commit()
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