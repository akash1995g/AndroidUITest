package com.cicdanduitest.androiduitest.usecase

import com.cicdanduitest.androiduitest.models.ApiResponse
import com.cicdanduitest.androiduitest.models.ProductsItem
import com.cicdanduitest.androiduitest.models.Rating
import com.cicdanduitest.androiduitest.service.ProductApiService
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response.success

class ProductRepositoryTest {
    private val productApiService = mockk<ProductApiService>()
    private val productRepository = ProductRepository(productApiService)
    private val productsItem =
        ProductsItem(
            category = "category",
            description = "description",
            id = 1,
            image = "image",
            price = 1.0,
            rating = Rating(count = 1, rate = 1.0),
            title = "title",
        )

    @Test
    fun testGetAllProducts() = runTest {
        coEvery { productApiService.getAllProducts() } returns
            success(
                listOf(productsItem),
            )
        val result = productRepository.getAllProducts()
        coEvery { productApiService.getAllProducts() } returns success(emptyList())
        assertThat(result).isEqualTo(ApiResponse.Success(data = listOf<ProductsItem>(productsItem)))
    }

    @Test
    fun testGetProductById() = runTest {
        coEvery { productApiService.getProductById() } returns success(productsItem)
        val result = productRepository.getProductById()
        assertThat(result).isEqualTo(ApiResponse.Success(data = productsItem))
    }
}
