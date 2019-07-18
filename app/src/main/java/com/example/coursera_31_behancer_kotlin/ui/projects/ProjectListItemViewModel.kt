package com.example.coursera_31_behancer_kotlin.ui.projects

import com.example.coursera_31_behancer_kotlin.data.model.project.RichProject
import com.example.coursera_31_behancer_kotlin.utils.DateUtils

class ProjectListItemViewModel(item: RichProject) {

    companion object {
        private const val FIRST_OWNER_INDEX = 0
    }

    val imageUrl: String = item.project!!.cover!!.photoUrl
    val name: String = item.project!!.name
    val publishedOn: String = DateUtils.format(item.project!!.publishedOn)
    val username: String

    init {
        username = if (item.owners.isNotEmpty()) {
            item.owners[FIRST_OWNER_INDEX].username
        } else {
            ""
        }
    }
}