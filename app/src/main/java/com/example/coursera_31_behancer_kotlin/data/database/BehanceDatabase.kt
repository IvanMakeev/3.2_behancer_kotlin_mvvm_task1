package com.example.coursera_31_behancer_kotlin.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.coursera_31_behancer_kotlin.data.model.project.Cover
import com.example.coursera_31_behancer_kotlin.data.model.project.Owner
import com.example.coursera_31_behancer_kotlin.data.model.project.Project
import com.example.coursera_31_behancer_kotlin.data.model.user.Image
import com.example.coursera_31_behancer_kotlin.data.model.user.User

@Database(entities = [Project::class, Cover::class, Owner::class, User::class, Image::class], version = 1)
abstract class BehanceDatabase : RoomDatabase() {

    abstract val behanceDao: BehanceDao
}