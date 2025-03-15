package dev.filinhat.openlibapp.app.navigation

import kotlinx.serialization.Serializable

/**
 * Список маршрутов приложения Open Library.
 */
sealed interface OpenLibRoute {
    @Serializable
    data object BookGraph : OpenLibRoute

    @Serializable
    data object BookList : OpenLibRoute

    @Serializable
    data class BookDetail(
        val id: String,
    ) : OpenLibRoute
}
