package com.aarron.gitsample.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


class RetrofitManager {
    lateinit var myAPIService: GitApiService;

    companion object {
        var mInstance: RetrofitManager = RetrofitManager()

    }

    fun getInstance(): RetrofitManager {
        return mInstance
    }

    constructor() {
        var client:OkHttpClient =OkHttpClient.Builder()
//            .addInterceptor(ApiInterceptor())
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .callbackExecutor(Executors.newSingleThreadExecutor()) //set call back is background thread
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
            .build()
        myAPIService = retrofit.create(GitApiService::class.java)
    }
}