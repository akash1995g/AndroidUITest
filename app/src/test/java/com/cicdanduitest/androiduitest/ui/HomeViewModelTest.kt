package com.cicdanduitest.androiduitest.ui

import org.junit.Test

class HomeViewModelTest {
    private val viewModel = HomeViewModel()

    @Test
    fun testAdd() {
        val result = viewModel.add(1, 1)
        assert(result == 2)
    }

    @Test
    fun testSub() {
        val result = viewModel.subtract(1, 1)
        assert(result == 0)
    }

    @Test
    fun testDiv() {
        val result = viewModel.div(1, 1)
        assert(result == 1)
    }

    @Test
    fun testMul() {
        val result = viewModel.multi(1, 1)
        assert(result == 1)
    }
}
