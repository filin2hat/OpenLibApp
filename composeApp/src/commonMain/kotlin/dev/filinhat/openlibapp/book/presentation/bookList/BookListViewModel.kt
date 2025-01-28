package dev.filinhat.openlibapp.book.presentation.bookList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.filinhat.openlibapp.book.domain.Book
import dev.filinhat.openlibapp.book.domain.BookRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

/**
 * ViewModel для экрана списка книг.
 */
class BookListViewModel(
    private val bookRepository: BookRepository,
) : ViewModel() {
    private var cachedBooks: List<Book> = emptyList()

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

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                errorMessage = null,
                                searchResults = cachedBooks,
                            )
                        }
                    }
                    query.length >= 2 -> {
                        searchBooks(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) {
        TODO("Not yet implemented")
    }
}
