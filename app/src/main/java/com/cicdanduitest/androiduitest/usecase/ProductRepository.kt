package com.cicdanduitest.androiduitest.usecase

import com.cicdanduitest.androiduitest.models.ApiResponse
import com.cicdanduitest.androiduitest.models.ProductsItem
import com.cicdanduitest.androiduitest.service.ProductApiService
import com.cicdanduitest.androiduitest.utils.safeApiCall
import javax.inject.Inject

class ProductRepository
@Inject
constructor(
    private val productApiService: ProductApiService,
) {
    suspend fun getAllProducts(): ApiResponse<List<ProductsItem>> = safeApiCall {
        productApiService.getAllProducts()
    }

    suspend fun getProductById(): ApiResponse<ProductsItem> = safeApiCall {
        productApiService.getProductById()
    }
}
