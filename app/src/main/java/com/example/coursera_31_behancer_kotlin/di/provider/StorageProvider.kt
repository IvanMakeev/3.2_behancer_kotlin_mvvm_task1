package com.example.coursera_31_behancer_kotlin.di.provider

import android.arch.persistence.room.Room
import com.example.coursera_31_behancer_kotlin.AppDelegate
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.data.database.BehanceDatabase
import javax.inject.Inject
import javax.inject.Provider

class StorageProvider : Provider<Storage> {

    @Inject
    lateinit var appDelegate: AppDelegate

    override fun get(): Storage {
        val database = Room.databaseBuilder(
            appDelegate,
            BehanceDatabase::class.java,
            "behance_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        return Storage(database.behanceDao)
    }
}