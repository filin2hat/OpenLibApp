package dev.filinhat.openlibapp.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object BookGraph : Route

    @Serializable
    data object BookList : Route

    @Serializable
    data class BookDetails(
        val bookId: String,
    ) : Route
}
