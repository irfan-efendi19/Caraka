package com.bangkit.caraka.utill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.caraka.data.networking.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private val appRepository: AppRepository) : ViewModel() {
    init {
        insertAllData()
    }

    private fun insertAllData() = viewModelScope.launch {
        appRepository.insertAllData()
    }

    suspend fun register(email: String, name: String, password: String) =
        appRepository.register(email, name, password)

    suspend fun login(email: String, password: String) =
        appRepository.login(email, password)
}