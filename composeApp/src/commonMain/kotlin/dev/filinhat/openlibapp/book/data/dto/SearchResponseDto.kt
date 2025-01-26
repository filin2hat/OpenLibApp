package dev.filinhat.openlibapp.book.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) для представления ответа от API Open Library на запрос поиска.
 *
 * Этот класс используется для десериализации данных, полученных от API.
 * Он содержит список найденных книг, представленных в виде объектов [SearchedBookDto].
 *
 * @property results Список найденных книг.
 */
@Serializable
data class SearchResponseDto(
    @SerialName("docs")val results: List<SearchedBookDto>,
)
