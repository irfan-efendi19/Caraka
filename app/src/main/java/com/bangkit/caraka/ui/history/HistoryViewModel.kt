package com.bangkit.caraka.ui.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.caraka.data.networking.userPreference.User
import com.bangkit.caraka.data.networking.repository.AppRepository
import com.bangkit.caraka.data.networking.response.HistoryResponse
import com.bangkit.caraka.data.preference.UserModel
import kotlinx.coroutines.launch

class HistoryViewModel(private val appRepository: AppRepository): ViewModel() {

}