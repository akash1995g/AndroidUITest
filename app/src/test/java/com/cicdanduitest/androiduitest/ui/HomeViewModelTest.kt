package com.cicdanduitest.androiduitest.ui

import org.junit.Test

class HomeViewModelTest {
    val viewModel = HomeViewModel()

    @Test
    fun testAdd() {
        val result = viewModel.add(1, 1)
        assert(result == 2)
    }
}
