package com.example.coursera_31_behancer_kotlin.data.api

import com.example.coursera_31_behancer_kotlin.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class ApiKeyInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url().newBuilder()
            .addQueryParameter("client_id", BuildConfig.API_KEY)
            .build()
        request = request.newBuilder().url(httpUrl).build()

        return chain.proceed(request)
    }
}