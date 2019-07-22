package com.example.coursera_31_behancer_kotlin.ui.projects

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.databinding.ProjectsBinding

abstract class BaseProjectsFragment : Fragment() {

    protected var baseProjectsViewModel: BaseProjectsViewModel? = null
    protected lateinit var storage: Storage

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Storage.StorageOwner) {
            storage = context.obtainStorage()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = ProjectsBinding.inflate(inflater, container, false)
        binding.vm = baseProjectsViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}