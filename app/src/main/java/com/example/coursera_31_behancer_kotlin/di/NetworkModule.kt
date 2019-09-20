package com.example.coursera_31_behancer_kotlin.di

import com.example.coursera_31_behancer_kotlin.data.api.BehanceApi
import com.example.coursera_31_behancer_kotlin.di.provider.BehanceApiProvider
import com.example.coursera_31_behancer_kotlin.di.provider.OkHttpClientProvider
import com.example.coursera_31_behancer_kotlin.di.provider.RetrofitProvider
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import toothpick.config.Module

class NetworkModule : Module() {

    init {
        bind(Gson::class.java).toInstance(Gson())
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).singleton()
        bind(Retrofit::class.java).toProvider(RetrofitProvider::class.java).singleton()
        bind(BehanceApi::class.java).toProvider((BehanceApiProvider::class.java)).singleton()
    }
}