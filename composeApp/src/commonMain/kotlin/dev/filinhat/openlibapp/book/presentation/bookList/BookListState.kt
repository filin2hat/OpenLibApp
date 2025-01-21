package dev.filinhat.openlibapp.book.presentation.bookList

import dev.filinhat.openlibapp.book.domain.Book
import dev.filinhat.openlibapp.core.presentation.UiText

/**
 * Состояние списка книг.
 *
 * @property searchQuery Текст поискового запроса.
 * @property searchResults Результаты поиска книг.
 * @property favoriteBooks Список избранных книг.
 * @property isLoading Индикатор загрузки данных.
 * @property selectedTabIndex Индекс выбранной вкладки.
 * @property erroeMessage Текст сообщения об ошибке.
 */
data class BookListState(
    val searchQuery: String = "",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null,
)
