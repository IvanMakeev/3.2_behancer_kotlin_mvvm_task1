package com.example.coursera_31_behancer_kotlin.ui.projects

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coursera_31_behancer_kotlin.R
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.databinding.ProjectsBinding
import com.example.coursera_31_behancer_kotlin.ui.profile.ProfileActivity
import com.example.coursera_31_behancer_kotlin.ui.profile.ProfileFragment
import com.example.coursera_31_behancer_kotlin.utils.CustomFactory

class ProjectsFragment : Fragment() {

    companion object {
        fun newInstance(): ProjectsFragment {
            return ProjectsFragment()
        }
    }

    private lateinit var projectsViewModel: ProjectsViewModel
    private val onItemClickListener = object : ProjectsAdapter.OnItemClickListener {
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
        if (context is Storage.StorageOwner) {
            val storage = context.obtainStorage()
            val factory = CustomFactory(storage, onItemClickListener)
            projectsViewModel = ViewModelProviders.of(this, factory).get(ProjectsViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = ProjectsBinding.inflate(inflater, container, false)
        binding.vm = projectsViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}