package dev.filinhat.openlibapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.filinhat.openlibapp.book.presentation.bookList.components.BookSearchBar

@Preview(showBackground = true)
@Composable
private fun BookSearchBarPreview(modifier: Modifier = Modifier) {
    MaterialTheme {
        BookSearchBar(
            searchQuery = "Search Query",
            onSearchQueryChange = {},
            onImeSearch = {},
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}
