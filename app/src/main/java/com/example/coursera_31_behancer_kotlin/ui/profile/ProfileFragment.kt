package com.example.coursera_31_behancer_kotlin.ui.profile

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coursera_31_behancer_kotlin.AppDelegate
import com.example.coursera_31_behancer_kotlin.databinding.ProfileBinding
import com.example.coursera_31_behancer_kotlin.di.VMModule
import toothpick.Toothpick
import javax.inject.Inject

class ProfileFragment : Fragment() {

    companion object {
        const val PROFILE_KEY = "PROFILE_KEY"

        fun newInstance(args: Bundle): ProfileFragment {
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var profileViewModel: ProfileViewModel
    private lateinit var username: String

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (arguments != null) {
            username = arguments!!.getString(PROFILE_KEY)!!
        }
        val vmScope = Toothpick.openScopes(AppDelegate::class.java, ProfileViewModel::class.java)
        vmScope.installModules(VMModule())
        Toothpick.inject(this, vmScope)
        profileViewModel.setUsername(username)
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
        if (savedInstanceState == null) {
            profileViewModel.loadProfile()
        }
    }

    override fun onDetach() {
        profileViewModel.dispatchDetach()
        super.onDetach()
    }
}