package com.bangkit.caraka.ui.camera

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.caraka.data.networking.repository.AppRepository
import com.bangkit.caraka.data.networking.response.ScanResponse
import com.bangkit.caraka.data.networking.response.UploadResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody


class ScannerViewModel(private val appRepository: AppRepository) : ViewModel() {
    val uploadResponse: LiveData<UploadResponse> = appRepository.uploadResponse

    fun uploadFile(file: MultipartBody.Part, daerah: String) {
        viewModelScope.launch {
            appRepository.uploadFile(file, daerah)
        }
    }

    val _scanResponse = MutableLiveData<ScanResponse>()
    val scanResponse : LiveData<ScanResponse> =  _scanResponse
    fun scanImageResult() {
        viewModelScope.launch {
//            _scanResponse.value = appRepository.getScanResult()
        }
    }
}