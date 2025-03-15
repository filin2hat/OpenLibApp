@file:Suppress("ktlint:standard:filename")

package dev.filinhat.openlibapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.filinhat.openlibapp.app.App
import dev.filinhat.openlibapp.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "OpenLibApp",
        ) {
            App()
        }
    }
}
