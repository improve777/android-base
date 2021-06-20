package com.improve777.common.data

sealed class ResultData<out T> {
    data class Success<T>(val data: T?) : ResultData<T>()
    data class Failure(val error: Throwable) : ResultData<Nothing>()
    object Loading : ResultData<Nothing>()
}