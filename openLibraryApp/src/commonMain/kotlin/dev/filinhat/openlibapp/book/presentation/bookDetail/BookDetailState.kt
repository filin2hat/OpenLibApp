package dev.filinhat.openlibapp.book.presentation.bookDetail

import dev.filinhat.openlibapp.book.domain.Book

/**
 * Состояние экрана с детальной информацией о книге.
 *
 * @property isLoading Флаг, указывающий, загружаются ли данные в данный момент.
 * @property isFavorite Флаг, указывающий, добавлена ли книга в избранное.
 * @property book Объект [Book], содержащий детальную информацию о книге.
 */
data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null,
)
