package com.cicdanduitest.androiduitest.models

import java.io.IOException

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class ApiError(val code: Int, val message: String?) : ApiResponse<Nothing>()
    data class NetworkError(val exception: IOException) : ApiResponse<Nothing>()
    data class UnknownError(val throwable: Throwable) : ApiResponse<Nothing>()
}
