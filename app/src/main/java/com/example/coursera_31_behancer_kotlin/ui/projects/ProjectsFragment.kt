package com.example.coursera_31_behancer_kotlin.ui.projects

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coursera_31_behancer_kotlin.AppDelegate
import com.example.coursera_31_behancer_kotlin.R
import com.example.coursera_31_behancer_kotlin.databinding.ProjectsBinding
import com.example.coursera_31_behancer_kotlin.di.VMModule
import com.example.coursera_31_behancer_kotlin.ui.profile.ProfileActivity
import com.example.coursera_31_behancer_kotlin.ui.profile.ProfileFragment
import toothpick.Toothpick
import javax.inject.Inject

class ProjectsFragment : Fragment() {

    companion object {
        fun newInstance(): ProjectsFragment {
            return ProjectsFragment()
        }
    }

    @Inject
    lateinit var projectsViewModel: ProjectsViewModel
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
        val vmScope = Toothpick.openScopes(AppDelegate::class.java, ProjectsViewModel::class.java)
        vmScope.installModules(VMModule())
        Toothpick.inject(this, vmScope)
        projectsViewModel.onItemClickListener = onItemClickListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = ProjectsBinding.inflate(inflater, container, false)
        binding.vm = projectsViewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            activity!!.setTitle(R.string.projects)
        }
        if (savedInstanceState == null) {
            projectsViewModel.loadProjects()
        }
    }

    override fun onDetach() {
        projectsViewModel.dispatchDetach()
        super.onDetach()
    }

}