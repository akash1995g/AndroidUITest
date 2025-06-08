package com.cicdanduitest.androiduitest.service

import com.cicdanduitest.androiduitest.models.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getAllProducts(): Response<Products>
}