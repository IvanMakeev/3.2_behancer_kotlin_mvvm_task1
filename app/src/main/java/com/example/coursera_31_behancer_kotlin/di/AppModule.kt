package com.example.coursera_31_behancer_kotlin.di

import com.example.coursera_31_behancer_kotlin.AppDelegate
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.di.provider.StorageProvider
import toothpick.config.Module
import javax.inject.Inject


class AppModule @Inject constructor(appDelegate: AppDelegate) : Module() {

    init {
        bind(AppDelegate::class.java).toInstance(appDelegate)
        bind(Storage::class.java).toProvider(StorageProvider::class.java).singleton()
    }
}