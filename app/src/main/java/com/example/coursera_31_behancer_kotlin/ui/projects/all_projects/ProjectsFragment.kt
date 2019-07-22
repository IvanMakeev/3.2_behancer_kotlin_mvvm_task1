package com.example.coursera_31_behancer_kotlin.ui.projects.all_projects

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.coursera_31_behancer_kotlin.ui.profile.ProfileActivity
import com.example.coursera_31_behancer_kotlin.ui.profile.ProfileFragment
import com.example.coursera_31_behancer_kotlin.ui.projects.BaseProjectsFragment
import com.example.coursera_31_behancer_kotlin.ui.projects.ProjectsAdapter
import com.example.coursera_31_behancer_kotlin.utils.ProjectsViewModelFactory

class ProjectsFragment : BaseProjectsFragment() {

    companion object {
        fun newInstance(): ProjectsFragment {
            return ProjectsFragment()
        }
    }

    private val onItemClickListener = object :
        ProjectsAdapter.OnItemClickListener {
        override fun onItemClick(username: String) {
            val intent = Intent(activity, ProfileActivity::class.java)
            val args = Bundle()
            args.putString(ProfileFragment.PROFILE_KEY, username)
            intent.putExtra(ProfileActivity.USERNAME_KEY, args)
            startActivity(intent)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val factory = ProjectsViewModelFactory(storage, onItemClickListener)
        baseProjectsViewModel = ViewModelProviders.of(this, factory).get(ProjectsViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        baseProjectsViewModel?.updateProjects()
    }
}