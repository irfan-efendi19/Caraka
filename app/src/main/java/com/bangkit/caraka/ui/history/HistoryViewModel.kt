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

//        fun getSession(): LiveData<UserModel> {
//            return appRepository.getSession()
//        }

        fun logout() {
            viewModelScope.launch {
                appRepository.logout()
            }
        }

    private val _historyLiveData = MutableLiveData<HistoryResponse>()
    val historyLiveData: LiveData<HistoryResponse> = _historyLiveData

    fun fetchHistory() {
        viewModelScope.launch {
            try {
                val historyResponse: HistoryResponse = appRepository.getStories()
                _historyLiveData.value = historyResponse
            } catch (e: Exception) {
                Log.d("ViewModel", e.message.toString())
            }
        }
    }
}