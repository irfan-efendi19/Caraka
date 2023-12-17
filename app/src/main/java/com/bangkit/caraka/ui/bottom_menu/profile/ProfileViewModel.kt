package com.bangkit.caraka.ui.bottom_menu.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.caraka.data.networking.repository.AppRepository
import com.bangkit.caraka.data.preference.UserModel

class ProfileViewModel(private val repository: AppRepository) : ViewModel() {
    suspend fun logout() {
        repository.logout()
    }
}