package com.example.recycler.data.network

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<T>(val exception: Exception) : Result<T>()
    object Loading : Result<Nothing>()
}