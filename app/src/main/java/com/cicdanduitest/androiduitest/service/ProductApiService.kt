package com.cicdanduitest.androiduitest.service

import com.cicdanduitest.androiduitest.models.Products
import com.cicdanduitest.androiduitest.models.ProductsItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getAllProducts(): Response<Products>

    @GET("products/1")
    suspend fun getProductById(): Response<ProductsItem>

}