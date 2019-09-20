package com.example.coursera_31_behancer_kotlin.ui.projects

import android.support.v4.app.Fragment
import com.example.coursera_31_behancer_kotlin.common.SingleFragmentActivity

class ProjectsActivity : SingleFragmentActivity(){
    override fun getFragment(): Fragment {
        return ProjectsFragment.newInstance()
    }
}