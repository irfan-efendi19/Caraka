package com.bangkit.caraka.ui.kamus

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.caraka.data.database.Kamus
import com.bangkit.caraka.data.networking.repository.AppRepository

class KamusViewModel(private val appRepository: AppRepository) : ViewModel() {
    fun getKamus(kamusBelajarId: Int): LiveData<List<Kamus>> =
        appRepository.getKamus(kamusBelajarId)
}