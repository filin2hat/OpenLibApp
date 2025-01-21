package dev.filinhat.openlibapp.book.presentation.bookList.components

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Компонент строки поиска книг.
 *
 * @param searchQuery Текст поискового запроса.
 * @param onSearchQueryChange Функция, вызываемая при изменении текста поискового запроса.
 * @param onImeSearch Функция, вызываемая при нажатии кнопки "Поиск" на клавиатуре.
 * @param modifier Модификатор для настройки внешнего вида компонента.
 */
@Composable
fun BookSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
    )
}
