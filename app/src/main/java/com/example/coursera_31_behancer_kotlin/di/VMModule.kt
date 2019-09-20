package com.example.coursera_31_behancer_kotlin.di

import com.example.coursera_31_behancer_kotlin.ui.profile.ProfileViewModel
import com.example.coursera_31_behancer_kotlin.ui.projects.ProjectsViewModel
import toothpick.config.Module

class VMModule : Module(){

    init {
        bind(ProjectsViewModel::class.java).singleton()
        bind(ProfileViewModel::class.java).singleton()
    }
}