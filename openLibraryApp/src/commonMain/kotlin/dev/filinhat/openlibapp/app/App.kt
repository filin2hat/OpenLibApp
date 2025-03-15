package dev.filinhat.openlibapp.app

import androidx.compose.runtime.Composable
import dev.filinhat.openlibapp.app.navigation.OpenLibNavigation
import dev.filinhat.openlibapp.book.presentation.theme.OpenLibAppTheme

/**
 * Компонент приложения
 */
@Composable
fun App() {
    OpenLibAppTheme {
        OpenLibNavigation()
    }
}
