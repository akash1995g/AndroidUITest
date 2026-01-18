package com.cicdanduitest.androiduitest.usecase

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetProductListUseCaseTest {
    private val productRepository = mockk<ProductRepository>(relaxed = true)
    private val getProductListUseCase = GetProductListUseCase(productRepository)

    @Test
    fun testGetProductList() = runTest {
        getProductListUseCase()
        coVerify { productRepository.getAllProducts() }
    }
}
