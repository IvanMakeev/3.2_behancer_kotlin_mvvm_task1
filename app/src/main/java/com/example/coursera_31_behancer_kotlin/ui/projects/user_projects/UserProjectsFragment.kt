package com.example.coursera_31_behancer_kotlin.ui.projects.user_projects

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import com.example.coursera_31_behancer_kotlin.R
import com.example.coursera_31_behancer_kotlin.ui.projects.BaseProjectsFragment
import com.example.coursera_31_behancer_kotlin.ui.projects.ProjectsAdapter
import com.example.coursera_31_behancer_kotlin.utils.UserProjectsViewModelFactory

class UserProjectsFragment : BaseProjectsFragment() {

    companion object {
        const val USER_ID = "USER_ID"

        fun newInstance(args: Bundle): UserProjectsFragment {
            val fragment = UserProjectsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var username: String
    private val onItemClickListener = object : ProjectsAdapter.OnItemClickListener {
        override fun onItemClick(username: String) {
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.let {
            username = it.getString(USER_ID)!!
        }
        activity?.let {
            it.title = getString(R.string.projects) + " $username"
        }
            val factory = UserProjectsViewModelFactory(storage, onItemClickListener, username)
            baseProjectsViewModel = ViewModelProviders.of(this, factory).get(UserProjectsViewModel::class.java)
    }
}