package dev.filinhat.openlibapp.book.data.dto

import kotlinx.serialization.Serializable

/**
 * DTO (Data Transfer Object) для представления информации о произведении (книге).
 *
 * @property description Описание произведения. Может быть `null`, если описание отсутствует.
 * @see BookWorkDtoSerializer
 */
@Serializable(with = BookWorkDtoSerializer::class)
data class BookWorkDto(
    val description: String? = null,
)
