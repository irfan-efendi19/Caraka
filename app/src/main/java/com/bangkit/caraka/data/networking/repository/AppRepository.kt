package com.bangkit.caraka.data.networking.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.bangkit.caraka.data.ResultData
import com.bangkit.caraka.data.dummydata.DummyDataAksara
import com.bangkit.caraka.data.database.CarakaDao
import com.bangkit.caraka.data.database.Kamus
import com.bangkit.caraka.data.di.RegisterResponse
import com.bangkit.caraka.data.networking.userPreference.User
import com.bangkit.caraka.data.networking.userPreference.UserPreference
import com.bangkit.caraka.data.networking.response.HistoryResponse
import com.bangkit.caraka.data.networking.response.SignupResponse
import com.bangkit.caraka.data.networking.service.ApiConfig
import com.bangkit.caraka.data.networking.service.ApiService

class AppRepository private constructor(
    private val carakaDao: CarakaDao,
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): LiveData<ResultData<SignupResponse>> = liveData {
        emit(ResultData.Loading)
        val response = apiService.register(name, email, password)
        if (!response.error) {
            emit(ResultData.Success(response))
        } else {
            emit(ResultData.Error(response.message))
        }
    }


    //fungsi login
    suspend fun login(email: String, password: String) =
        apiService.login(email, password)

    //mendapatkan stories
    suspend fun getStories(): HistoryResponse =
        apiService.getStories()


    //fungsi menyimpan preference key
    suspend fun saveSession(user: User) =
        userPreference.saveSession(user)

    //mendapatkan sesi
    fun getSession() = userPreference.getSession().asLiveData()

    //fungsi logout
    suspend fun logout() {
        userPreference.logout()
    }

    //fungsi mendapatkan detail
    suspend fun getDetailStory(id: String) = apiService.getDetailStory(id)

    fun getKamus(aksaraId: Int): LiveData<List<Kamus>> = carakaDao.getAllKamus(aksaraId)

    suspend fun insertAllData() {
        carakaDao.insertKamus(DummyDataAksara.getAksaraKamus())
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
