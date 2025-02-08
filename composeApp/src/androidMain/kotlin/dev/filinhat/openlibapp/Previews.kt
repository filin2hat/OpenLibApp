package dev.filinhat.openlibapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.filinhat.openlibapp.book.domain.Book
import dev.filinhat.openlibapp.book.presentation.bookList.BookListScreen
import dev.filinhat.openlibapp.book.presentation.bookList.BookListState
import dev.filinhat.openlibapp.book.presentation.bookList.components.BookSearchBar
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
private fun BookSearchBarEmptyPreview(modifier: Modifier = Modifier) {
    MaterialTheme {
        BookSearchBar(
            searchQuery = "",
            onSearchQueryChange = {},
            onClearSearchResults = {},
            onImeSearch = {},
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BookSearchBarNotEmptyPreview(modifier: Modifier = Modifier) {
    MaterialTheme {
        BookSearchBar(
            searchQuery = "Search query",
            onSearchQueryChange = {},
            onClearSearchResults = {},
            onImeSearch = {},
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun BookListScreenPreview() {
    MaterialTheme {
        BookListScreen(
            state = BookListState(searchResults = books),
            onAction = {},
        )
    }
}

private val books =
    (1..100).map {
        Book(
            id = it.toString(),
            title = "Book $it",
            imageUrl = "https://picsum.photos/200/300?random=$it",
            authors = listOf("Philip K. Dick, Jonh Smith"),
            description = "Description of book $it",
            languages = listOf("English", "Russian"),
            averageRating = 4.4665465465,
            raitingCount = 56456465,
            numPages = Random(555).nextInt(100, 1000),
            firstPublishYear = Random(8).nextInt(1800, 2025).toString(),
            numEdition = Random(3).nextInt(1, 15),
        )
    }
