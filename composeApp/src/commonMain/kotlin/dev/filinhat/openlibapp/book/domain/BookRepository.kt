package dev.filinhat.openlibapp.book.domain

import dev.filinhat.openlibapp.core.domain.DataError
import dev.filinhat.openlibapp.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}
