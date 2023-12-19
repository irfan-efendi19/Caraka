package com.bangkit.caraka.ui.bottom_menu.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.caraka.data.database.Artikel
import com.bangkit.caraka.data.networking.repository.AppRepository

class HistoryViewModel(private val repository: AppRepository) : ViewModel() {
    fun getArtikel(artikelId: Int): LiveData<List<Artikel>> =
        repository.getArtikel(artikelId)
}