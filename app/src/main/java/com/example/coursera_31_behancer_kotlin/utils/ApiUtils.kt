package com.example.coursera_31_behancer_kotlin.utils

import com.example.coursera_31_behancer_kotlin.BuildConfig
import com.example.coursera_31_behancer_kotlin.data.api.ApiKeyInterceptor
import com.example.coursera_31_behancer_kotlin.data.api.BehanceApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

object ApiUtils {

    val NETWORK_EXCEPTIONS: List<Class<*>> = Arrays.asList<Class<out IOException>>(
        UnknownHostException::class.java,
        SocketTimeoutException::class.java,
        ConnectException::class.java
    )

    private var client: OkHttpClient? = null
    private var retrofit: Retrofit? = null
    private var gson: Gson? = null
    private var api: BehanceApi? = null

    private fun getClient(): OkHttpClient {
        if (client == null) {
            val builder = OkHttpClient().newBuilder()
            builder.addInterceptor(ApiKeyInterceptor())
            if (!BuildConfig.BUILD_TYPE.contains("release")) {
                builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            client = builder.build()
        }
        return client!!
    }

    private fun getRetrofit(): Retrofit {
        if (gson == null) {
            gson = Gson()
        }
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                // need for interceptors
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson!!))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun getApiService(): BehanceApi {
        if (api == null) {
            api = getRetrofit().create<BehanceApi>(BehanceApi::class.java)
        }
        return api!!
    }
}