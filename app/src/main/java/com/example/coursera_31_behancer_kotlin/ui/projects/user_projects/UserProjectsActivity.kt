package com.example.coursera_31_behancer_kotlin.ui.projects.user_projects

import android.support.v4.app.Fragment
import com.example.coursera_31_behancer_kotlin.AppDelegate
import com.example.coursera_31_behancer_kotlin.common.SingleFragmentActivity
import com.example.coursera_31_behancer_kotlin.data.Storage

class UserProjectsActivity : SingleFragmentActivity(), Storage.StorageOwner {

    override fun getFragment(): Fragment {
        return UserProjectsFragment.newInstance()
    }

    override fun obtainStorage(): Storage {
        return (applicationContext as AppDelegate).storage!!
    }
}