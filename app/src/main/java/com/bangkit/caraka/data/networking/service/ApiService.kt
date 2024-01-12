package com.bangkit.caraka.data.networking.service


import com.bangkit.caraka.data.networking.response.LoginResponse
import com.bangkit.caraka.data.networking.response.RegisterResponse
import com.bangkit.caraka.data.networking.response.UploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiService {
    @FormUrlEncoded
    @POST("/auth/register")
    suspend fun register(
        @Field("nama") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("/auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @Multipart
    @POST("predict/{daerah}")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
        @retrofit2.http.Path("daerah") daerah: String
    ): UploadResponse
}