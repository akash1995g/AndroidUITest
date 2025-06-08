package com.cicdanduitest.androiduitest.service

import com.cicdanduitest.androiduitest.models.ProductsItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductsItem>>

    @GET("products/1")
    suspend fun getProductById(): Response<ProductsItem>
}
