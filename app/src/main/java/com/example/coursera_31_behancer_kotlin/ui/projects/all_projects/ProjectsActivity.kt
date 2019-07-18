package com.example.coursera_31_behancer_kotlin.ui.projects.all_projects

import android.support.v4.app.Fragment
import com.example.coursera_31_behancer_kotlin.AppDelegate
import com.example.coursera_31_behancer_kotlin.common.SingleFragmentActivity
import com.example.coursera_31_behancer_kotlin.data.Storage

class ProjectsActivity : SingleFragmentActivity(), Storage.StorageOwner {

    override fun getFragment(): Fragment {
        return ProjectsFragment.newInstance()
    }

    override fun obtainStorage(): Storage {
        return (applicationContext as AppDelegate).storage!!
    }
}