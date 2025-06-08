package com.cicdanduitest.androiduitest.usecase

import com.cicdanduitest.androiduitest.models.ApiResponse
import com.cicdanduitest.androiduitest.models.Products
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): ApiResponse<Products> {
        return productRepository.getAllProducts()
    }
}