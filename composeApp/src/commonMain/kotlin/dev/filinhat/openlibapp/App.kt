package dev.filinhat.openlibapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.filinhat.openlibapp.book.presentation.bookList.BookListScreenRoot
import dev.filinhat.openlibapp.book.presentation.bookList.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = koinViewModel<BookListViewModel>(),
            onBookClick = { book -> },
        )
    }
}
