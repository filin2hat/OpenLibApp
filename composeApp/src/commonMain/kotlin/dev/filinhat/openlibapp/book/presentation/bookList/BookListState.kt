package dev.filinhat.openlibapp.book.presentation.bookList

import dev.filinhat.openlibapp.book.domain.Book
import dev.filinhat.openlibapp.core.presentation.UiText
import kotlin.random.Random

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
    val searchResults: List<Book> = books,
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null,
)

val books =
    (1..100).map {
        Book(
            id = it.toString(),
            title = "Book $it",
            imageUrl = "https://picsum.photos/200/300?random=$it",
            authors = listOf("Philip K. Dick, Jonh Smith"),
            description = "Description of book $it",
            languages = listOf("English", "Russian"),
            averageRating = 4.4665465465,
            raitingCount = 56456465,
            numPages = Random(555).nextInt(100, 1000),
            firstPublishYear = Random(8).nextInt(1800, 2025).toString(),
            numEdition = Random(3).nextInt(1, 15),
        )
    }
