package com.bangkit.caraka.data.di

import android.content.Context
import com.bangkit.caraka.data.networking.repository.AppRepository
import com.bangkit.caraka.data.networking.service.ApiConfig
import com.bangkit.caraka.data.networking.userPreference.UserPreference
import com.bangkit.caraka.data.networking.userPreference.dataStore

object Injection {
    fun provideRepository(context: Context): AppRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return AppRepository.getInstance(apiService, pref)
    }
}