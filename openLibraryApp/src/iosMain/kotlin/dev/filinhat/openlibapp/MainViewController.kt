package dev.filinhat.openlibapp

import androidx.compose.ui.window.ComposeUIViewController
import dev.filinhat.openlibapp.app.App
import dev.filinhat.openlibapp.di.initKoin

fun MainViewController() {
    ComposeUIViewController(
        configure = { initKoin() },
    ) {
        App()
    }
}
