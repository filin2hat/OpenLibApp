package dev.filinhat.openlibapp.book.domain

import dev.filinhat.openlibapp.core.domain.DataError
import dev.filinhat.openlibapp.core.domain.EmptyResult
import dev.filinhat.openlibapp.core.domain.Result
import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий для доступа к данным о книгах.
 *
 * Этот интерфейс определяет методы для получения информации о книгах,
 * таких как поиск книг и получение описания книги.
 */
interface BookRepository {
    /**
     * Выполняет поиск книг по заданному запросу.
     *
     * @param query Поисковый запрос.
     * @return `Result` со списком объектов [Book], соответствующих запросу,
     *         или с ошибкой типа [DataError.Remote] в случае неудачи.
     * @see Book
     * @see DataError.Remote
     */
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>

    /**
     * Получает описание книги по её идентификатору.
     *
     * @param bookId Идентификатор книги.
     * @return `Result` со строкой, содержащей описание книги, или `null`, если описание отсутствует,
     *         или с ошибкой типа [DataError] в случае неудачи.
     * @see DataError
     */
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>

    /**
     * Получает список избранных книг.
     *
     * @return `Flow` со списком объектов [Book], представляющим избранные книги.
     */
    fun getFavoriteBooks(): Flow<List<Book>>

    /**
     * Проверяет, является ли книга избранной.
     *
     * @param id Идентификатор книги.
     * @return `Flow` с булевым значением, указывающим, является ли книга избранной.
     */
    fun isBookFavorite(id: String): Flow<Boolean>

    /**
     * Добавляет книгу в избранное.
     *
     * @param book Книга, которую нужно добавить в избранное.
     * @return `EmptyResult` с ошибкой типа [DataError.Local] в случае неудачи.
     */
    suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local>

    /**
     * Удаляет книгу из избранного.
     *
     * @param id Идентификатор книги, которую нужно удалить из избранного.
     */
    suspend fun deleteFromFavorites(id: String)
}
