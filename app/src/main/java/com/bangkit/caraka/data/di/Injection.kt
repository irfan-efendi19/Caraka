package com.bangkit.caraka.data.di

import android.content.Context
import com.bangkit.caraka.data.database.CarakaDatabase
import com.bangkit.caraka.data.networking.repository.AppRepository
import com.bangkit.caraka.data.networking.service.ApiConfig
import com.bangkit.caraka.data.networking.userPreference.UserPreference
import com.bangkit.caraka.data.networking.userPreference.dataStore
import com.bangkit.caraka.ui.bottom_menu.profile.ProfileFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): AppRepository = runBlocking{
        val carakaDatabase = CarakaDatabase.getDatabase(context)
        val pref = UserPreference.getInstance(context.dataStore)
        val user = pref.getSession().first()
        val apiService = ApiConfig.getApiService(user.token)
        AppRepository.getInstance(carakaDatabase.CarakaDao(), apiService, pref)
    }
}