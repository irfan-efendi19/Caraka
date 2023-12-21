package com.bangkit.caraka.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.caraka.data.networking.repository.AppRepository
import com.bangkit.caraka.data.networking.response.ScanResponse
import com.bangkit.caraka.data.networking.response.UploadResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.File


class ScannerViewModel(private val appRepository: AppRepository) : ViewModel() {

//    val uploadResponse: LiveData<UploadResponse> = appRepository.uploadResponse
//    fun uploadFile(file: MultipartBody.Part) {
//        viewModelScope.launch {
//            appRepository.uploadFile(file)
//        }
//    }
//
//    suspend fun performScanner(img: File): Flow<Result<UploadResponse>> =
//        appRepository.getScan(img)
//
//
//    val _scanResponse = MutableLiveData<ScanResponse>()
//    val scanResponse: LiveData<ScanResponse> = _scanResponse
//    fun scanImageResult() {
//        viewModelScope.launch {
//            _scanResponse.value = appRepository.getScanResult()
//        }
//    }
}