package com.cicdanduitest.androiduitest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cicdanduitest.androiduitest.models.ApiResponse
import com.cicdanduitest.androiduitest.usecase.GetProductByIdUseCase
import com.cicdanduitest.androiduitest.usecase.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val getProductList: GetProductListUseCase,
        private val getProductById: GetProductByIdUseCase,
    ) : ViewModel() {
        fun add(
            a: Int,
            b: Int,
        ): Int = a + b

        fun subtract(
            a: Int,
            b: Int,
        ): Int = a - b

        fun div(
            a: Int,
            b: Int,
        ): Int = a / b

        fun multi(
            a: Int,
            b: Int,
        ): Int = a * b

        init {
            viewModelScope.launch {
                with(getProductList()) {
                    when (this) {
                        is ApiResponse.ApiError -> {
                            println("ApiError $this")
                        }

                        is ApiResponse.NetworkError -> {
                            println("ApiError $this")
                        }

                        is ApiResponse.Success<*> -> {
                            println("ApiError $this")
                        }

                        is ApiResponse.UnknownError -> {
                            println("ApiError $this")
                        }
                    }
                }
            }
        }
    }
