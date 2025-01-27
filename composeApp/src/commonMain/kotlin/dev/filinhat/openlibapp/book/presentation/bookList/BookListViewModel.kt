package dev.filinhat.openlibapp.book.presentation.bookList

import androidx.lifecycle.ViewModel
import dev.filinhat.openlibapp.book.domain.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel для экрана списка книг.
 */
class BookListViewModel(
    private val bookRepository: BookRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()

    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnBookClick -> {
                // todo
            }

            is BookListAction.OnSearchQueryChange -> {
                _state.update { bookListState ->
                    bookListState.copy(searchQuery = action.query)
                }
            }

            is BookListAction.OnTabSelected -> {
                _state.update { bookListState ->
                    bookListState.copy(selectedTabIndex = action.index)
                }
            }
        }
    }
}
