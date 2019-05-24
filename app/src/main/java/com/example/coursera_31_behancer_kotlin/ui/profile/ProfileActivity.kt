package com.example.coursera_31_behancer_kotlin.ui.profile

import android.support.v4.app.Fragment
import com.example.coursera_31_behancer_kotlin.AppDelegate
import com.example.coursera_31_behancer_kotlin.common.RefreshActivity
import com.example.coursera_31_behancer_kotlin.data.Storage

class ProfileActivity : RefreshActivity(), Storage.StorageOwner{

    companion object {
        const val USERNAME_KEY = "USERNAME_KEY"
    }

    override fun getFragment(): Fragment {
        if (intent != null) {
            return ProfileFragment.newInstance(intent.getBundleExtra(USERNAME_KEY))
        }
        throw IllegalStateException("getIntent cannot be null")
    }

    override fun obtainStorage(): Storage {
        return (applicationContext as AppDelegate).storage!!
    }
}