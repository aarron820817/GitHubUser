package com.aarron.gitsample.retrofit

import com.aarron.gitsample.bean.UserBean
import com.aarron.gitsample.bean.UserDetail
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GitApiService {


    @GET("users")
     fun getUsers(
        @Query("since") since: Int?,
        @Query("per_page") per_page: Int
    ): Call<ArrayList<UserBean>>


     @GET("users/{user}")
     fun getDetailUser(
         @Path("user") user : String
     ):Call<UserDetail>




}