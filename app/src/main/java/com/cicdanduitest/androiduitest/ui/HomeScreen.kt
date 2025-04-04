package com.cicdanduitest.androiduitest.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel: HomeViewModel = viewModel()
}

@Preview
@Composable
private fun HomeScreenPreview(modifier: Modifier = Modifier) {

}