package com.codingambitions.loginapp.shared

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    MaterialTheme {
        App()
    }
}