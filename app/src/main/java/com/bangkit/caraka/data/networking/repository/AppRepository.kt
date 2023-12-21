package com.bangkit.caraka.data.networking.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.bangkit.caraka.data.database.Artikel
import com.bangkit.caraka.data.database.CarakaDao
import com.bangkit.caraka.data.database.Kamus
import com.bangkit.caraka.data.database.Langganan
import com.bangkit.caraka.data.dummydata.DummyDataAksara
import com.bangkit.caraka.data.networking.response.LoginResponse
import com.bangkit.caraka.data.networking.response.ScanResponse
import com.bangkit.caraka.data.networking.response.RegisterResponse
import com.bangkit.caraka.data.networking.response.UploadResponse
import com.bangkit.caraka.data.networking.service.ApiService
import com.bangkit.caraka.data.networking.userPreference.UserModel
import com.bangkit.caraka.data.networking.userPreference.UserPreference
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File

class AppRepository private constructor(
    private val carakaDao: CarakaDao,
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {

    val _uploadResponse = MutableLiveData<UploadResponse>()
    val uploadResponse : LiveData<UploadResponse> = _uploadResponse

//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): LiveData<ResultData<RegisterResponse>> = liveData {
        emit(ResultData.Loading)
        val response = apiService.register(name, email, password)
        if (!response.error) {
            emit(ResultData.Success(response))
        } else {
            emit(ResultData.Error(response.message))
        }
    }

    suspend fun login(email: String, password: String): LiveData<ResultData<LoginResponse>> =
        liveData {
            emit(ResultData.Loading)
            try {
                val response = apiService.login(email, password)
                emit(ResultData.Success(response))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
                emit(ResultData.Error(errorBody.message.toString()))
            }
        }


    suspend fun uploadFile(file: MultipartBody.Part) {
        try {
            val successResponse = apiService.uploadImage(file)
            _uploadResponse.value = successResponse
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, UploadResponse::class.java)
            _uploadResponse.value = errorResponse
        }
    }

//    }
//
//    private val maxRetries = 2
//    private var retryCount = 0
//    suspend fun uploadFile(file: MultipartBody.Part, context: Context){
//        _isLoading.value = true
//        try {
//            val successResponse = apiService.uploadImage(file)
//            _uploadResponse.value = successResponse
//            _isLoading.value = false
//        } catch (e: SocketTimeoutException) {
//            if (retryCount < maxRetries) {
//                retryCount++
//                delay(retryCount * 1000L) // Increase delay between retries
//                uploadFile(file, context) // Retry the network request
//            } else {
//                // Maximum retries reached, handle accordingly
//                Log.e("Network Retry", "Maximum retries reached.")
//                _isLoading.value = false
//                showToast(context,"Maximum retries reached. try again")
//            }
//        } catch (e: Exception) {
//            _isLoading.value = false
//            showToast(context, "Gagal Terdeteksi Coba Lagi")
//
//        }
//    }


    suspend fun uploadFile1(file: MultipartBody.Part){
        try {
            val successResponse = apiService.uploadImage1(file)
            _uploadResponse.value = successResponse
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse =  Gson().fromJson(errorBody, UploadResponse::class.java)
            _uploadResponse.value = errorResponse
        }
    }


    suspend fun uploadFile2(file: MultipartBody.Part){
        try {
            val successResponse = apiService.uploadImage2(file)
            _uploadResponse.value = successResponse
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse =  Gson().fromJson(errorBody, UploadResponse::class.java)
            _uploadResponse.value = errorResponse
        }
    }

    //fungsi menyimpan preference key
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    //mendapatkan sesi
    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    //fungsi logout
    suspend fun logout() {
        userPreference.logOut()
    }

    //fungsi mendapatkan detail
    fun getKamus(aksaraId: Int): LiveData<List<Kamus>> = carakaDao.getAllKamus(aksaraId)
    fun getArtikel(artikelId: Int): LiveData<List<Artikel>> = carakaDao.getAllArtikel(artikelId)
    fun getLangganan(langgananId: Int): LiveData<List<Langganan>> = carakaDao.getLangganan(langgananId)

    suspend fun getScan(
        img: File,
    ): Flow<Result<UploadResponse>> = flow{
        val requestImageFile = img.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData("image", img.name, requestImageFile)
        val response = apiService.uploadImage(imageMultipart)

        emit(Result.success(response))
    }.catch{ e->
        e.printStackTrace()
        emit(Result.failure(e))
    }

    suspend fun insertAllData() {
        carakaDao.insertKamus(DummyDataAksara.getAksaraKamus())
        carakaDao.insertArtikel(DummyDataAksara.getArtikel())
        carakaDao.insertLangganan(DummyDataAksara.getLangganan())
    }


    companion object {
        fun clearInstance() {
            instance = null
        }

        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(
            carakaDao: CarakaDao,
            service: ApiService,
            userPreference: UserPreference
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(carakaDao, service, userPreference)
            }.also { instance = it }
    }
}
