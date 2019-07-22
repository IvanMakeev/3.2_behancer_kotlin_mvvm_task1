package com.example.coursera_31_behancer_kotlin.data

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.util.Log
import com.example.coursera_31_behancer_kotlin.data.database.BehanceDao
import com.example.coursera_31_behancer_kotlin.data.model.project.*
import com.example.coursera_31_behancer_kotlin.data.model.user.UserResponse
import java.util.*

class Storage(private val behanceDao: BehanceDao) {

    companion object {
        private const val PAGE_SIZE = 5
    }

    fun insertProjects(response: ProjectResponse) {
        insertProjects(response.projects)
    }

    fun insertProjects(projects: List<Project>) {
        behanceDao.insertProjects(projects)
        val owners = getOwners(projects)
        behanceDao.insertOwners(owners)
    }

    private fun getOwners(projects: List<Project>): List<Owner> {
        val owners = ArrayList<Owner>()
        for (i in projects.indices) {

            val owner = projects[i].owners!![0]
            owner.id = projects[i].id
            owner.projectId = projects[i].id
            owners.add(owner)
        }
        return owners
    }

    fun getProjectsLive(): LiveData<List<RichProject>> {
        return behanceDao.getProjectsLive()
    }

    fun getProjectsPaged(): LiveData<PagedList<RichProject>> {
        return LivePagedListBuilder(behanceDao.getProjectsPaged(), PAGE_SIZE).build()
    }

    fun getProjectsPagedByName(username: String): LiveData<PagedList<RichProject>> {
        return LivePagedListBuilder(behanceDao.getProjectsPagedByName(username), PAGE_SIZE).build()
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
