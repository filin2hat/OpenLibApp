package dev.filinhat.openlibapp.book.data.dto

import kotlinx.serialization.Serializable

/**
 * DTO (Data Transfer Object) для описания.
 *
 * Используется для передачи данных об описании между слоями приложения.
 *
 * @property value Текстовое значение описания.
 */
@Serializable
data class DescriptionDto(
    val value: String,
)
