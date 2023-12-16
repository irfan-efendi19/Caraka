package com.bangkit.caraka.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.caraka.data.ResultData
import com.bangkit.caraka.data.di.RegisterResponse
import com.bangkit.caraka.data.networking.repository.AppRepository
import com.bangkit.caraka.data.networking.response.SignupResponse

class SignUpViewModel (private var repository: AppRepository) : ViewModel(){
    suspend fun register(name: String, email: String, password: String): LiveData<ResultData<SignupResponse>> {
        return repository.register(name, email, password)
    }
}