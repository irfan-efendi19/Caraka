package com.bangkit.caraka.data.networking.service

import com.bangkit.caraka.data.di.RegisterResponse
import com.bangkit.caraka.data.networking.response.DetailHistory
import com.bangkit.caraka.data.networking.response.HistoryResponse
import com.bangkit.caraka.data.networking.response.LoginResponse
import com.bangkit.caraka.data.networking.response.SignupResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): SignupResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @GET("stories")
    suspend fun getStories(): HistoryResponse

    @GET("stories/{id}")
    suspend fun getDetailStory(
        @Path("id") id : String
    ): DetailHistory
}