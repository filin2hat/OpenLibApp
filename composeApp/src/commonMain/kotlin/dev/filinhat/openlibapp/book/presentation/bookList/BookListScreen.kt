package dev.filinhat.openlibapp.book.presentation.bookList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.filinhat.openlibapp.book.domain.Book
import org.koin.compose.viewmodel.koinViewModel

/**
 * Корневой экран списка книг.
 *
 * @param viewModel Модель представления для экрана списка книг.
 * @param onBookClick Обработчик нажатия на книгу.
 */
@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel = koinViewModel(),
    onBookClick: (Book) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is BookListAction.OnBookClick -> onBookClick(action.book)
                else -> Unit
            }
            viewModel.onAction(action)
        },
    )
}

@Composable
private fun BookListScreen(
    state: BookListState,
    onAction: (BookListAction) -> Unit,
    modifier: Modifier = Modifier,
) {
}
