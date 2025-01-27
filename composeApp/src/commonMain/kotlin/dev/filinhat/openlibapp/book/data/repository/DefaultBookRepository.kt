package dev.filinhat.openlibapp.book.data.repository

import dev.filinhat.openlibapp.book.data.mappers.toBook
import dev.filinhat.openlibapp.book.data.network.RemoteBookDataSource
import dev.filinhat.openlibapp.book.domain.Book
import dev.filinhat.openlibapp.book.domain.BookRepository
import dev.filinhat.openlibapp.core.domain.DataError
import dev.filinhat.openlibapp.core.domain.Result
import dev.filinhat.openlibapp.core.domain.map

/**
 * Реализация репозитория для работы с книгами.
 *
 * Класс предоставляет методы для получения данных о книгах из удаленного источника
 * и преобразования их в объекты домена.
 *
 * @property remoteBookDataSource Источник данных для получения информации о книгах из удаленного источника.
 */
class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
) : BookRepository {
    /**
     * Выполняет поиск книг по заданному запросу.
     *
     * @param query Поисковый запрос.
     * @return `Result` со списком объектов [Book], представляющих найденные книги,
     *         или с ошибкой типа [DataError.Remote] в случае неудачи.
     */
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> =
        remoteBookDataSource
            .searchBooks(query)
            .map { searchResponseDto ->
                searchResponseDto.results.map { it.toBook() }
            }
}
