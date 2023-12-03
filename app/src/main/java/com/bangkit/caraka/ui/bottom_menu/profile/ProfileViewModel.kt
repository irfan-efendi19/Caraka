package com.bangkit.caraka.ui.bottom_menu.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ini Profile"
    }
    val text: LiveData<String> = _text
}