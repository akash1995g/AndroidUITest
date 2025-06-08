package com.cicdanduitest.androiduitest.service

import com.cicdanduitest.androiduitest.models.Products
import retrofit2.Call
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getAllProducts(): Call<Products>
}