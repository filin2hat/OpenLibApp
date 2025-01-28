package dev.filinhat.openlibapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.filinhat.openlibapp.book.data.network.KtorRemoteBookDataSource
import dev.filinhat.openlibapp.book.data.repository.DefaultBookRepository
import dev.filinhat.openlibapp.book.presentation.bookList.BookListScreenRoot
import dev.filinhat.openlibapp.book.presentation.bookList.BookListViewModel
import dev.filinhat.openlibapp.core.data.network.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    MaterialTheme {
        BookListScreenRoot(
            viewModel =
                remember {
                    BookListViewModel(
                        bookRepository =
                            DefaultBookRepository(
                                remoteBookDataSource =
                                    KtorRemoteBookDataSource(
                                        httpClient =
                                            HttpClientFactory.create(
                                                engine = engine,
                                            ),
                                    ),
                            ),
                    )
                },
            onBookClick = { book -> },
        )
    }
}
