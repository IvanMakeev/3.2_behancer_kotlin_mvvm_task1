package com.example.coursera_31_behancer_kotlin.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.coursera_31_behancer_kotlin.data.model.project.Cover
import com.example.coursera_31_behancer_kotlin.data.model.project.Owner
import com.example.coursera_31_behancer_kotlin.data.model.project.Project
import com.example.coursera_31_behancer_kotlin.data.model.project.RichProject
import com.example.coursera_31_behancer_kotlin.data.model.user.Image
import com.example.coursera_31_behancer_kotlin.data.model.user.User

@Dao
interface BehanceDao {

    @get:Query("select * from project")
    val projects: List<Project>

    @get:Query("select * from user")
    val users: List<User>

    @get:Query("select * from image")
    val images: List<Image>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProjects(projects: List<Project>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCovers(covers: List<Cover>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOwners(owners: List<Owner>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(image: Image)

    @Query("select * from project order by published_on desc")
    fun getProjectsLive(): LiveData<List<RichProject>>

    @Query("select * from owner where project_id = :projectId")
    fun getOwnersFromProject(projectId: Int): List<Owner>

    @Query("select * from user where username = :userName")
    fun getUserByName(userName: String): User

    @Query("select * from image where user_id = :userId")
    fun getImageFromUser(userId: Int): Image

    @Query("delete from owner")
    fun clearOwnerTable()

    @Query("delete from cover")
    fun clearCoverTable()

    @Query("delete from image")
    fun clearImageTable()
}