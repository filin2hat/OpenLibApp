package dev.filinhat.openlibapp.book.presentation.bookList

import dev.filinhat.openlibapp.book.domain.Book

/**
 * Интерфейс действий для списка книг.
 *
 * Интерфейс определяет возможные действия, которые могут быть выполнены
 * в контексте списка книг.
 */
sealed interface BookListAction {

    /**
     * Изменение поискового запроса.
     *
     * @property query Новый поисковый запрос.
     */
    data class OnSearchQueryChange(val query: String) : BookListAction

    /**
     * Нажатие на книгу.
     *
     * @property book Книга, на которую нажали.
     */
    data class OnBookClick(val book: Book) : BookListAction

    /**
     * Выбор вкладки.
     *
     * @property index Индекс выбранной вкладки.
     */
    data class OnTabSelected(val index: Int) : BookListAction

}