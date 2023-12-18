package com.bangkit.caraka.ui.signin

import androidx.lifecycle.ViewModel
import com.bangkit.caraka.data.networking.repository.AppRepository
import com.bangkit.caraka.data.networking.userPreference.UserModel

class SignInViewModel(private val repository: AppRepository) : ViewModel() {
    suspend fun login(email: String, password: String) = repository.login(email, password)
    suspend fun saveSession(user: UserModel) {
        repository.saveSession(user)
    }
}