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


    private var onItemClickListener: ProjectsAdapter.OnItemClickListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onItemClickListener = object : ProjectsAdapter.OnItemClickListener {
            override fun onItemClick(username: String) {
                val intent = Intent(activity, ProfileActivity::class.java)
                val args = Bundle()
                args.putString(ProfileFragment.PROFILE_KEY, username)
                intent.putExtra(ProfileActivity.USERNAME_KEY, args)
                startActivity(intent)
            }
        }
        val factory = ProjectsViewModelFactory(storage)
        baseProjectsViewModel = ViewModelProviders.of(this, factory).get(ProjectsViewModel::class.java)
        (baseProjectsViewModel as ProjectsViewModel).onItemClickListener = onItemClickListener
    }

    override fun onDetach() {
        super.onDetach()
        onItemClickListener = null
    }
}