package com.example.coursera_31_behancer_kotlin.ui.profile

import android.support.v4.app.Fragment
import com.example.coursera_31_behancer_kotlin.common.SingleFragmentActivity

class ProfileActivity : SingleFragmentActivity() {

    override fun getFragment(): Fragment {
        if (intent != null) {
            return ProfileFragment.newInstance(intent.getBundleExtra(USERNAME_KEY))
        }
        throw IllegalStateException("getIntent cannot be null")
    }

    companion object {
        const val USERNAME_KEY = "USERNAME_KEY"
    }
}