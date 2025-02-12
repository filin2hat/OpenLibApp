package dev.filinhat.openlibapp.book.presentation.sharedViewModel

import androidx.lifecycle.ViewModel
import dev.filinhat.openlibapp.book.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Общая ViewModel для управления выбранной книгой.
 *
 * Этот ViewModel хранит информацию о текущей выбранной книге
 * и предоставляет методы для её изменения.
 */
class SelectedBookViewModel : ViewModel() {
    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook = _selectedBook.asStateFlow()

    fun onSelectBook(book: Book?) {
        _selectedBook.value = book
    }
}
