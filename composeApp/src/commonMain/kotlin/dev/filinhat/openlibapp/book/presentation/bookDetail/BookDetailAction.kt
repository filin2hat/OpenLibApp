package dev.filinhat.openlibapp.book.presentation.bookDetail

import dev.filinhat.openlibapp.book.domain.Book

/**
 * Действия, которые могут происходить на экране с детальной информацией о книге.
 */
sealed interface BookDetailAction {
    /**
     * Действие, возникающее при нажатии кнопки "Назад".
     */
    data object OnBackClick : BookDetailAction

    /**
     * Действие, возникающее при нажатии кнопки "Избранное".
     */
    data object OnFavoriteClick : BookDetailAction

    /**
     * Действие, возникающее при изменении выбранной книги.
     *
     * @property book Новая выбранная книга.
     */
    data class OnSelectedBookChange(
        val book: Book,
    ) : BookDetailAction
}
