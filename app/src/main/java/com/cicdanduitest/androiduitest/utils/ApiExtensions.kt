package com.cicdanduitest.androiduitest.utils

import com.cicdanduitest.androiduitest.models.ApiResponse
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResponse<T> =
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                ApiResponse.Success(body)
            } else {
                ApiResponse.ApiError(response.code(), "Empty body")
            }
        } else {
            val errorBody = response.errorBody()?.string()
            ApiResponse.ApiError(response.code(), errorBody)
        }
    } catch (e: IOException) {
        ApiResponse.NetworkError(e)
    } catch (e: Exception) {
        ApiResponse.UnknownError(e)
    }
