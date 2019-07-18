package com.example.coursera_31_behancer_kotlin.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.databinding.ProfileBinding
import com.example.coursera_31_behancer_kotlin.ui.projects.user_projects.UserProjectsActivity
import com.example.coursera_31_behancer_kotlin.ui.projects.user_projects.UserProjectsFragment

class ProfileFragment : Fragment() {

    companion object {
        const val PROFILE_KEY = "PROFILE_KEY"

        fun newInstance(args: Bundle): ProfileFragment {
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var username: String

//    val onProjectsClickListener = View.OnClickListener {
//        val intent = Intent(activity, UserProjectsActivity::class.java)
//        val args = Bundle()
//        args.putString(UserProjectsFragment.USER_ID, username)
//        intent.putExtra(ProfileActivity.USERNAME_KEY, args)
//        startActivity(intent)
//    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Storage.StorageOwner) {
            val storage = context.obtainStorage()
            if (arguments != null) {
                username = arguments!!.getString(PROFILE_KEY)!!
            }
            profileViewModel = ProfileViewModel(storage, username)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = ProfileBinding.inflate(inflater, container, false)
        binding.profile = profileViewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            activity!!.title = username
        }
        profileViewModel.loadProfile()
    }

    override fun onDetach() {
        profileViewModel.dispatchDetach()
        super.onDetach()
    }
}