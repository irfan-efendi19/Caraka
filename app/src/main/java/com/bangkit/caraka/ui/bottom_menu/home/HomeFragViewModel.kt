package com.bangkit.caraka.ui.bottom_menu.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.caraka.data.database.Artikel
import com.bangkit.caraka.data.networking.repository.AppRepository
import com.bangkit.caraka.data.networking.userPreference.UserModel

class HomeFragViewModel(private val repository: AppRepository) : ViewModel() {
    fun getArtikel(artikelId: Int): LiveData<List<Artikel>> =
        repository.getArtikel(artikelId)
}