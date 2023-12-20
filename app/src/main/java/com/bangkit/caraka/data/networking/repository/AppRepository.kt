package com.bangkit.caraka.data.networking.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.bangkit.caraka.data.database.Artikel
import com.bangkit.caraka.data.database.CarakaDao
import com.bangkit.caraka.data.database.Kamus
import com.bangkit.caraka.data.dummydata.DummyDataAksara
import com.bangkit.caraka.data.networking.response.LoginResponse
import com.bangkit.caraka.data.networking.response.RegisterResponse
import com.bangkit.caraka.data.networking.response.UploadResponse
import com.bangkit.caraka.data.networking.service.ApiService
import com.bangkit.caraka.data.networking.userPreference.UserModel
import com.bangkit.caraka.data.networking.userPreference.UserPreference
import com.bangkit.caraka.utill.Event
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.HttpException

class AppRepository private constructor(
    private val carakaDao: CarakaDao,
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {

    private val _uploadResponse = MutableLiveData<UploadResponse>()
    val uploadResponse: LiveData<UploadResponse> = _uploadResponse

    private val _responseMessage = MutableLiveData<Event<String>>()
    val responseMessage: LiveData<Event<String>> = _responseMessage

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
            _responseMessage.value = Event(successResponse.message)
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, UploadResponse::class.java)
            _uploadResponse.value = errorResponse
            _responseMessage.value = Event(errorResponse.message)
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
//    fun getLangganan(langgananId: Int): LiveData<List<Langganan>> = carakaDao.getLangganan(langgananId)

    suspend fun insertAllData() {
        carakaDao.insertKamus(DummyDataAksara.getAksaraKamus())
        carakaDao.insertArtikel(DummyDataAksara.getArtikel())
//        carakaDao.insertLangganan(DummyDataAksara.getLangganan())
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
