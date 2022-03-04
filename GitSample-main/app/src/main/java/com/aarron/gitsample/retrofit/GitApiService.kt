package com.aarron.gitsample.retrofit

import com.aarron.gitsample.bean.UserBean
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GitApiService {
    //    @GET("users?since={since}&per_page=20")
//    fun getinfo_product(): Call<ArrayList<UserBean?>?>?

    @GET("users")
     fun getUsersDefault(
        @Query("per_page") per_page: Int
    ): Call<List<UserBean>>

    @GET("users")
     fun getUsers(
        @Query("since") since: String,
        @Query("per_page") per_page: Int
    ): Response<ArrayList<UserBean>>
}