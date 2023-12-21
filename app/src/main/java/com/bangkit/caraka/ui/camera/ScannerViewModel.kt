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


class ScannerViewModel(private val appRepository: AppRepository): ViewModel() {
//    val liveDataResponseScanner: MutableLiveData<Result<ScannerResponse>> = MutableLiveData()
//    val liveDataTranslatorResponse: MutableLiveData<Result<Translatorv2Response>> = MutableLiveData()
//    private val _liveDataIsLoading: MutableLiveData<Boolean> = MutableLiveData()
//    val liveDataIsLoading: LiveData<Boolean> = _liveDataIsLoading
//
//    fun fetchUserResponse(newJumlahScan: Int, userId: Int){
//        viewModelScope.launch {
//            updateUserJumlahScan(newJumlahScan, userId).collect{ response ->
//                liveDataResponseUpdateUser.value = response
//            }
//        }
//    }
//
//    fun fetchScannerResponse(img: File){
//        _liveDataIsLoading.value = true
//        viewModelScope.launch {
//            performScanner(img).collect{ response->
//                liveDataResponseScanner.value = response
//                _liveDataIsLoading.value = false
//            }
//        }
//    }
//
//    fun fetchTranslatorResult(text: String){
//        viewModelScope.launch {
//            getTranslator(text).collect{ response ->
//                liveDataTranslatorResponse.value = response
//            }
//        }
//    }
//
//    suspend fun updateUserJumlahScan(newJumlahScan: Int, userId: Int): Flow<Result<GraphQLResponse>> =
//        baksaraRepository.updateUserScan(newJumlahScan, userId)
//
//    suspend fun performScanner(img: File): Flow<Result<ScannerResponse>> =
//        baksaraRepository.scannerResult(img)
//
//    suspend fun getTranslator(text:String): Flow<Result<Translatorv2Response>> =
//        baksaraRepository.translator2(text)


    val uploadResponse : LiveData<UploadResponse> = appRepository.uploadResponse

    fun uploadFile(file: MultipartBody.Part) {
        viewModelScope.launch {
            appRepository.uploadFile(file)
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