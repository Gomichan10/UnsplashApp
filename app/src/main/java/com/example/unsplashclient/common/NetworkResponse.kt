package com.example.unsplashclient.common

sealed class NetworkResponse<T>(
    val date: T? = null,
    val error: String? = null,
){
    class Success<T>(date: T) : NetworkResponse<T>(date = date)
    class Failure<T>(error: String) : NetworkResponse<T>(error = error)
    class Loading<T> : NetworkResponse<T>()
}
