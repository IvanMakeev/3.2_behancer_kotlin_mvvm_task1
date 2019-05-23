package com.example.coursera_31_behancer_kotlin.ui.projects

import com.example.coursera_31_behancer_kotlin.data.model.project.Project
import com.example.coursera_31_behancer_kotlin.utils.DateUtils

class ProjectListItemViewModel(project: Project) {

    companion object {
        private const val FIRST_OWNER_INDEX = 0
    }

    init {
        val imageUrl: String = project.cover!!.photoUrl
        val name: String = project.name
        val username: String = project.owners!![FIRST_OWNER_INDEX].username
        val publishedOn: String = DateUtils.format(project.publishedOn)
    }
}