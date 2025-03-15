package dev.filinhat.openlibapp.book.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) для представления книги, полученной в результате поиска.
 *
 * Этот класс используется для десериализации данных, полученных от API Open Library.
 *
 * @property id Уникальный идентификатор книги (ключ).
 * @property title Название книги.
 * @property languages Список языков, на которых доступна книга.
 * @property coverKey Ключ обложки книги.
 * @property coverAlternativeKey Альтернативный ключ обложки книги.
 * @property authorKeys Список ключей авторов книги.
 * @property authorNames Список имен авторов книги.
 * @property firstPublishYear Год первого издания книги.
 * @property raitingAverage Средний рейтинг книги.
 * @property raitingCount Количество оценок книги.
 * @property numPagesMedian Медианное количество страниц в книге.
 * @property editionCount Количество изданий книги.
 */
@Serializable
data class SearchedBookDto(
    @SerialName("key") val id: String,
    @SerialName("title") val title: String,
    @SerialName("language") val languages: List<String>? = null,
    @SerialName("cover_edition_key") val coverKey: String? = null,
    @SerialName("cover_i") val coverAlternativeKey: Int? = null,
    @SerialName("author_key") val authorKeys: List<String>? = null,
    @SerialName("author_name") val authorNames: List<String>? = null,
    @SerialName("first_publish_year") val firstPublishYear: Int? = null,
    @SerialName("ratings_average") val raitingAverage: Double? = null,
    @SerialName("ratings_count") val raitingCount: Int? = null,
    @SerialName("number_of_pages_median") val numPagesMedian: Int? = null,
    @SerialName("edition_count") val editionCount: Int? = null,
)
