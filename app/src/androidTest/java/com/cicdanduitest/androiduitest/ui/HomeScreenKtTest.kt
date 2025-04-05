package com.cicdanduitest.androiduitest.ui

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class HomeScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun incrementButton_increasesCounter() {
        composeTestRule.setContent {
            CounterApp()
        }

        // Check initial state
        composeTestRule
            .onNodeWithTag("counterText")
            .assertTextEquals("Count: 0")

        // Perform click
        composeTestRule
            .onNodeWithTag("incrementButton")
            .performClick()

        // Check updated state
        composeTestRule
            .onNodeWithTag("counterText")
            .assertTextEquals("Count: 1")
    }
}
