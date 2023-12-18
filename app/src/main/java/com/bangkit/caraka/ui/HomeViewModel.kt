package com.bangkit.caraka.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.caraka.data.networking.repository.AppRepository
import com.bangkit.caraka.data.networking.userPreference.UserModel

class HomeViewModel(private val repository: AppRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}