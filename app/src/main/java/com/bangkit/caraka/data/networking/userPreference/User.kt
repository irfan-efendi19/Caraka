package com.bangkit.caraka.data.networking.userPreference

data class User (
    val userId: String,
    val name: String,
    val email: String,
    val token: String,
    val isLogin: Boolean
)