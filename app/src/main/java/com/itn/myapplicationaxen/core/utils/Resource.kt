package com.itn.myapplicationaxen.core.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    sealed class Error<T>(data: T? = null) : Resource<T>(data) {
        class ServerError<T>(data: T? = null) : Error<T>(data)
        class ConnectionError<T>(data: T? = null) : Error<T>(data)
        class UnknownError<T>(data: T? = null) : Error<T>(data)
    }
}