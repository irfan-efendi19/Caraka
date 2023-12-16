package com.bangkit.caraka.data

sealed class ResultData<out R> private constructor() {
    data class Success<out T>(val data: T) : ResultData<T>()
    data class Error(val error: String) : ResultData<Nothing>()
    data object Loading : ResultData<Nothing>()
}