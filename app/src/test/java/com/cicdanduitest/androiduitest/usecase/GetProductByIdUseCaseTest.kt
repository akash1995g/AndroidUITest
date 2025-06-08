package com.cicdanduitest.androiduitest.usecase

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetProductByIdUseCaseTest {
    private val productRepository = mockk<ProductRepository>(relaxed = true)
    private val getProductByIdUseCase = GetProductByIdUseCase(productRepository)

    @Test
    fun testGetProductById() = runTest {
        getProductByIdUseCase()
        coVerify { productRepository.getProductById() }
    }
}
