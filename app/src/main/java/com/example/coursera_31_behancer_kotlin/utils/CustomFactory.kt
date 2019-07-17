package com.example.coursera_31_behancer_kotlin.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.ui.projects.ProjectsAdapter
import com.example.coursera_31_behancer_kotlin.ui.projects.ProjectsViewModel

@Suppress("UNCHECKED_CAST")
class CustomFactory(
    private val storage: Storage,
    private val onItemClickListener: ProjectsAdapter.OnItemClickListener
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectsViewModel(storage, onItemClickListener) as T
    }
}