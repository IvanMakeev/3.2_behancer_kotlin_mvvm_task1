package com.example.coursera_31_behancer_kotlin.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.coursera_31_behancer_kotlin.R

abstract class SingleFragmentActivity : AppCompatActivity() {

    protected abstract fun getFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        if (savedInstanceState == null) {
            changeFragment(getFragment())
        }

    }

    protected open fun getLayout(): Int {
        return R.layout.ac_container
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
}