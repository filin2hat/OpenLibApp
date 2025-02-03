package dev.filinhat.openlibapp.book.data.repository

import androidx.sqlite.SQLiteException
import dev.filinhat.openlibapp.book.data.database.FavoriteBookDao
import dev.filinhat.openlibapp.book.data.mappers.toBook
import dev.filinhat.openlibapp.book.data.mappers.toBookEntity
import dev.filinhat.openlibapp.book.data.network.RemoteBookDataSource
import dev.filinhat.openlibapp.book.domain.Book
import dev.filinhat.openlibapp.book.domain.BookRepository
import dev.filinhat.openlibapp.core.domain.DataError
import dev.filinhat.openlibapp.core.domain.EmptyResult
import dev.filinhat.openlibapp.core.domain.Result
import dev.filinhat.openlibapp.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Реализация репозитория для работы с книгами.
 *
 * Класс предоставляет методы для получения данных о книгах из удаленного источника
 * и преобразования их в объекты домена.
 *
 * @property remoteBookDataSource Источник данных для получения информации о книгах из удаленного источника.
 * @property favoriteBookDao DAO для работы с избранными книгами.
 */
class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favoriteBookDao: FavoriteBookDao,
) : BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> =
        remoteBookDataSource
            .searchBooks(query)
            .map { searchResponseDto ->
                searchResponseDto.results.map { it.toBook() }
            }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        val localResult = favoriteBookDao.getFavoriteBook(bookId)
        return if (localResult == null) {
            remoteBookDataSource
                .getBookDetails(bookId)
                .map { it.description }
        } else {
            Result.Success(localResult.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> =
        favoriteBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.map { it.toBook() }
            }

    override fun isBookFavorite(id: String): Flow<Boolean> =
        favoriteBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.any { it.id == id }
            }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> =
        try {
            favoriteBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL_ERROR)
        }

    override suspend fun deleteFromFavorites(id: String) {
        favoriteBookDao.deleteFavoriteBook(id)
    }
}
