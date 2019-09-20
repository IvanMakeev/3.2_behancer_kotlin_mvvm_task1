package com.example.coursera_31_behancer_kotlin.data

import android.support.v4.util.Pair
import com.example.coursera_31_behancer_kotlin.data.database.BehanceDao
import com.example.coursera_31_behancer_kotlin.data.model.project.Cover
import com.example.coursera_31_behancer_kotlin.data.model.project.Owner
import com.example.coursera_31_behancer_kotlin.data.model.project.Project
import com.example.coursera_31_behancer_kotlin.data.model.project.ProjectResponse
import com.example.coursera_31_behancer_kotlin.data.model.user.UserResponse
import java.util.*

class Storage(private val behanceDao: BehanceDao) {

    fun insertProjects(response: ProjectResponse) {
        val projects = response.projects
        behanceDao.insertProjects(projects)

        val assembled = assemble(projects)

        behanceDao.clearCoverTable()
        behanceDao.insertCovers(assembled.first!!)
        behanceDao.clearOwnerTable()
        behanceDao.insertOwners(assembled.second!!)
    }

    private fun assemble(projects: List<Project>): Pair<List<Cover>, List<Owner>> {

        val covers = ArrayList<Cover>()
        val owners = ArrayList<Owner>()
        for (i in projects.indices) {
            val cover = projects[i].cover
            cover!!.id = i
            cover.projectId = projects[i].id
            covers.add(cover)

            val owner = projects[i].owners!![0]
            owner.id = i
            owner.projectId = projects[i].id
            owners.add(owner)
        }
        return Pair(covers, owners)
    }

    fun getProjects(): ProjectResponse {
        val projects = behanceDao.projects
        for (project in projects) {
            project.cover = behanceDao.getCoverFromProject(project.id)
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
}
