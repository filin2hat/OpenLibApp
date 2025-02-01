package dev.filinhat.openlibapp.book.data.network

import dev.filinhat.openlibapp.book.data.dto.BookWorkDto
import dev.filinhat.openlibapp.book.data.dto.SearchResponseDto
import dev.filinhat.openlibapp.book.data.dto.SearchedBookDto
import dev.filinhat.openlibapp.core.domain.DataError
import dev.filinhat.openlibapp.core.domain.Result

/**
 * Интерфейс определяет методы для получения данных о книгах из удаленного источника,
 * такого как API Open Library.
 */
interface RemoteBookDataSource {
    /**
     * Выполняет поиск книг по заданному запросу.
     *
     * @param query Поисковый запрос.
     * @param resultLimit Максимальное количество результатов, которые нужно вернуть.
     *                    Если `null`, то будет возвращено максимальное количество результатов,
     *                    поддерживаемое API.
     * @return `Result` с объектом [SearchedBookDto], содержащим результаты поиска,
     *         или с ошибкой типа [DataError.Remote] в случае неудачи.
     */
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null,
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}
