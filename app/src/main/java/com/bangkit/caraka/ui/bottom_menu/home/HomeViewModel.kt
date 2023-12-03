package com.bangkit.caraka.ui.bottom_menu.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ini Home"
    }
    val text: LiveData<String> = _text
}