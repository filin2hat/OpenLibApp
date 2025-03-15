package dev.filinhat.openlibapp.book.domain

/**
 * Класс, представляющий информацию о книге.
 *
 * @property id Уникальный идентификатор книги.
 * @property title Название книги.
 * @property imageUrl URL-адрес обложки книги.
 * @property authors Список авторов книги.
 * @property description Краткое описание содержания книги.
 * @property languages Список языков, на которых доступна книга.
 * @property firstPublishYear Год первого издания книги.
 * @property averageRating Средний рейтинг книги.
 * @property raitingCount Количество оценок книги.
 * @property numPages Количество страниц в книге.
 * @property numEdition Номер издания книги.
 */
data class Book(
    val id: String,
    val title: String,
    val imageUrl: String,
    val authors: List<String>,
    val description: String,
    val languages: List<String>,
    val firstPublishYear: String?,
    val averageRating: Double?,
    val raitingCount: Int?,
    val numPages: Int?,
    val numEdition: Int,
)
