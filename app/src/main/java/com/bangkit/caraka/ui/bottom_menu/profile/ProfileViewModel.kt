package com.bangkit.caraka.ui.bottom_menu.profile

import androidx.lifecycle.ViewModel
import com.bangkit.caraka.data.networking.repository.AppRepository

class ProfileViewModel(private val repository: AppRepository) : ViewModel() {
    suspend fun logout() {
        repository.logout()
    }
}