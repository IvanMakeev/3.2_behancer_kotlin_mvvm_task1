package com.example.coursera_31_behancer_kotlin.data

import android.arch.lifecycle.LiveData
import com.example.coursera_31_behancer_kotlin.data.database.BehanceDao
import com.example.coursera_31_behancer_kotlin.data.model.project.*
import com.example.coursera_31_behancer_kotlin.data.model.user.UserResponse
import java.util.*

class Storage(private val behanceDao: BehanceDao) {

    fun insertProjects(response: ProjectResponse) {
        val projects = response.projects
        behanceDao.insertProjects(projects)

        val owners = getOwners(projects)
        behanceDao.clearOwnerTable()
        behanceDao.insertOwners(owners)
    }

    private fun getOwners(projects: List<Project>): List<Owner> {
        val owners = ArrayList<Owner>()
        for (i in projects.indices) {

            val owner = projects[i].owners!![0]
            owner.id = i
            owner.projectId = projects[i].id
            owners.add(owner)
        }
        return owners
    }

    fun getProjectsLive(): LiveData<List<RichProject>> {
        return behanceDao.getProjectsLive()
    }

    fun getProjects(): ProjectResponse {
        val projects = behanceDao.projects
        for (project in projects) {
            project.owners = behanceDao.getOwnersFromProject(project.id)
        }

        return ProjectResponse(projects)
    }

    fun insertUser(response: UserResponse) {
        val user = response.user
        val image = user.image
        image!!.id = user.id
        image.userId = user.id

        behanceDao.insertUser(user)
        behanceDao.insertImage(image)
    }

    fun getUser(username: String): UserResponse {
        val user = behanceDao.getUserByName(username)
        val image = behanceDao.getImageFromUser(user.id)
        user.image = image

        return UserResponse(user)

    }

    interface StorageOwner {
        fun obtainStorage(): Storage
    }

}
