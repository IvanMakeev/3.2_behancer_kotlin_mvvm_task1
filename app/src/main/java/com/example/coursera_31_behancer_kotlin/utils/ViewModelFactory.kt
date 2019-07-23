package com.example.coursera_31_behancer_kotlin.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.view.View
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.ui.profile.ProfileViewModel
import com.example.coursera_31_behancer_kotlin.ui.projects.ProjectsAdapter
import com.example.coursera_31_behancer_kotlin.ui.projects.all_projects.ProjectsViewModel
import com.example.coursera_31_behancer_kotlin.ui.projects.user_projects.UserProjectsViewModel

@Suppress("UNCHECKED_CAST")
class ProjectsViewModelFactory(
    private val storage: Storage
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectsViewModel(
            storage
        ) as T
    }
}

@Suppress("UNCHECKED_CAST")
class UserProjectsViewModelFactory(
    private val storage: Storage,
    private val username: String
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserProjectsViewModel(
            storage,
            username
        ) as T
    }
}

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(
    private val storage: Storage,
    private val username: String
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(
            storage,
            username
        ) as T
    }
}