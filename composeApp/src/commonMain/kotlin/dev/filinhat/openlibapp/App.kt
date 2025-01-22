package dev.filinhat.openlibapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.filinhat.openlibapp.book.presentation.bookList.BookListScreenRoot
import dev.filinhat.openlibapp.book.presentation.bookList.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = remember { BookListViewModel() },
            onBookClick = { book -> },
        )
    }
}
