package com.cicdanduitest.androiduitest.ui

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    fun add(a: Int, b: Int): Int = a + b
    fun subtract(a: Int, b: Int): Int = a - b
    fun div(a: Int, b: Int): Int = a / b
    fun multi(a: Int, b: Int): Int = a * b
}
