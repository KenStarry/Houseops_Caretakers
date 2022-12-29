package com.example.houseopscaretakers.core.domain.model

sealed class Response<out T> {

    //  Loading status
    object Loading : Response<Nothing>()

    //  success status
    data class Success<out T>(val data: T) : Response<T>()

    //  failure status
    data class Failure(val e: Exception) : Response<Nothing>()

}
