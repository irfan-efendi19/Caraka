package com.bangkit.caraka.data.networking.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(token: String): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val authInterceptor = Interceptor { user ->
                val requestUser = user.request()
                val requestHeaders = requestUser.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                user.proceed(requestHeaders)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://backend-main-xujidinxka-ts.a.run.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            val apiService: ApiService = retrofit.create(ApiService::class.java)
            return retrofit.create(ApiService::class.java)
        }
    }
}