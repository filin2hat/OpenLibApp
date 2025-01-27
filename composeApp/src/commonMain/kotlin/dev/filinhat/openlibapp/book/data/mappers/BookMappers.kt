package dev.filinhat.openlibapp.book.data.mappers

import dev.filinhat.openlibapp.book.data.dto.SearchedBookDto
import dev.filinhat.openlibapp.book.domain.Book

/**
 * Преобразует объект [SearchedBookDto] в объект [Book].
 *
 * Функция расширения преобразует данные из DTO, полученного от API Open Library,
 * в объект домена [Book], который используется в приложении.
 *
 * @receiver Объект [SearchedBookDto], который нужно преобразовать.
 * @return Объект [Book], созданный на основе данных из DTO.
 */
fun SearchedBookDto.toBook(): Book =
    Book(
        id = id,
        title = title,
        imageUrl =
            if (coverKey != null) {
                "https://covers.openlibrary.org/b/olid/$coverKey-L.jpg"
            } else {
                "https://covers.openlibrary.org/b/id/$coverAlternativeKey-L.jpg"
            },
        authors = authorNames ?: emptyList(),
        description = "",
        languages = languages ?: emptyList(),
        firstPublishYear = firstPublishYear.toString(),
        averageRating = raitingAverage,
        raitingCount = raitingCount,
        numPages = numPagesMedian ?: 0,
        numEdition = editionCount ?: 0,
    )
