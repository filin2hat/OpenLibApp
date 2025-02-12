package dev.filinhat.openlibapp.book.presentation.bookDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dev.filinhat.openlibapp.app.navigation.OpenLibRoute
import dev.filinhat.openlibapp.book.domain.BookRepository
import dev.filinhat.openlibapp.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel для экрана с детальной информацией о книге.
 *
 * Этот ViewModel отвечает за получение и управление данными о книге,
 * а также за обработку действий пользователя на экране.
 *
 * @property bookRepository Репозиторий для доступа к данным о книгах.
 * @property savedStateHandle Состояние, сохраненное для ViewModel.
 */
class BookDetailViewModel(
    private val bookRepository: BookRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(BookDetailState())
    val state =
        _state
            .onStart {
                fetchBookDescription()
                observeFavoriteStatus()
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value)

    private val bookId = savedStateHandle.toRoute<OpenLibRoute.BookDetail>().id

    fun onAction(action: BookDetailAction) {
        when (action) {
            is BookDetailAction.OnFavoriteClick -> {
                viewModelScope.launch {
                    if (state.value.isFavorite) {
                        bookRepository.deleteFromFavorites(bookId)
                    } else {
                        state.value.book?.let { book ->
                            bookRepository.markAsFavorite(book)
                        }
                    }
                }
            }

            is BookDetailAction.OnSelectedBookChange -> {
                _state.update {
                    it.copy(book = action.book)
                }
            }

            else -> Unit
        }
    }

    private fun observeFavoriteStatus() {
        bookRepository
            .isBookFavorite(bookId)
            .onEach { isFavorite ->
                _state.update {
                    it.copy(isFavorite = isFavorite)
                }
            }.launchIn(viewModelScope)
    }

    private fun fetchBookDescription() {
        viewModelScope.launch {
            bookRepository
                .getBookDescription(bookId)
                .onSuccess { description ->
                    _state.update {
                        it.copy(
                            book = it.book?.copy(description = description ?: ""),
                            isLoading = false,
                        )
                    }
                }
        }
    }
}
