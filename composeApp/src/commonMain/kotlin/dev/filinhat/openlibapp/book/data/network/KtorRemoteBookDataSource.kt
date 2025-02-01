package dev.filinhat.openlibapp.book.data.network

import dev.filinhat.openlibapp.book.data.dto.BookWorkDto
import dev.filinhat.openlibapp.book.data.dto.SearchResponseDto
import dev.filinhat.openlibapp.core.data.network.safeCall
import dev.filinhat.openlibapp.core.domain.DataError
import dev.filinhat.openlibapp.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://openlibrary.org"

/**
 * Реализация [RemoteBookDataSource] с использованием Ktor для сетевых запросов.
 *
 * Этот класс предоставляет доступ к данным о книгах из API Open Library
 * с помощью HTTP-клиента Ktor.
 *
 * @property httpClient HTTP-клиент Ktor, используемый для выполнения запросов.
 */
class KtorRemoteBookDataSource(
    private val httpClient: HttpClient,
) : RemoteBookDataSource {
    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?,
    ): Result<SearchResponseDto, DataError.Remote> =
        safeCall<SearchResponseDto> {
            httpClient.get(
                "$BASE_URL/search.json",
            ) {
                parameter("q", query)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter(
                    "fields",
                    "key,title,language,cover_edition_key,cover_i,author_key,author_name,first_publish_year,ratings_average,ratings_count,number_of_pages_median,edition_count",
                )
            }
        }

    override suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote> =
        safeCall<BookWorkDto> {
            httpClient.get(
                "$BASE_URL/works/$bookWorkId.json",
            )
        }
}
