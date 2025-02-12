package dev.filinhat.openlibapp.app

import androidx.compose.runtime.Composable
import dev.filinhat.openlibapp.book.presentation.theme.OpenLibAppTheme
import dev.filinhat.openlibapp.navigation.OpenLibNavigation

/**
 * Компонент приложения
 */
@Composable
fun App() {
    OpenLibAppTheme {
        OpenLibNavigation()
    }
}
