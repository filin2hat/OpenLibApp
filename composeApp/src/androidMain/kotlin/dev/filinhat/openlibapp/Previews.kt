package dev.filinhat.openlibapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.filinhat.openlibapp.book.presentation.bookList.components.BookSearchBar

@Preview(showBackground = true)
@Composable
private fun BookSearchBarEmptyPreview(modifier: Modifier = Modifier) {
    MaterialTheme {
        BookSearchBar(
            searchQuery = "",
            onSearchQueryChange = {},
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
            onImeSearch = {},
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}
