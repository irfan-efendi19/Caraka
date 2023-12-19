package com.bangkit.caraka.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.caraka.data.networking.repository.AppRepository
import com.bangkit.caraka.data.networking.response.UploadResponse
import com.bangkit.caraka.utill.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

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


    val responseMessage : LiveData<Event<String>> = appRepository.responseMessage
    val uploadResponse : LiveData<UploadResponse> = appRepository.uploadResponse
    fun uploadFile(file: MultipartBody.Part) {
        viewModelScope.launch {
            appRepository.uploadFile(file)
        }
    }
}