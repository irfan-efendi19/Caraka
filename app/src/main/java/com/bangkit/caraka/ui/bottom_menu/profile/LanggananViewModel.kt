package com.bangkit.caraka.ui.bottom_menu.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.caraka.data.database.Kamus
import com.bangkit.caraka.data.database.Langganan
import com.bangkit.caraka.data.networking.repository.AppRepository

class LanggananViewModel(private val appRepository: AppRepository) : ViewModel() {
    fun getLangganan(langgananId: Int): LiveData<List<Langganan>> =
        appRepository.getLangganan(langgananId)
}