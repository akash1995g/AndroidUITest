package com.cicdanduitest.androiduitest.usecase

import com.cicdanduitest.androiduitest.models.ApiResponse
import com.cicdanduitest.androiduitest.models.ProductsItem
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): ApiResponse<ProductsItem> {
        return productRepository.getProductById()
    }
}
