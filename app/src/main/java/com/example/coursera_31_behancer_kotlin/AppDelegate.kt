package com.example.coursera_31_behancer_kotlin

import android.app.Application
import android.arch.persistence.room.Room
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.data.database.BehanceDatabase
import com.example.coursera_31_behancer_kotlin.di.AppModule
import com.example.coursera_31_behancer_kotlin.di.NetworkModule
import toothpick.Scope
import toothpick.Toothpick


/**
 * Created by Vladislav Falzan.
 */

class AppDelegate : Application() {

    companion object {
        private lateinit var appScope: Scope
    }

    override fun onCreate() {
        super.onCreate()
        appScope = Toothpick.openScope(AppDelegate::class.java)
        appScope.installModules(AppModule(this), NetworkModule())
    }
}
