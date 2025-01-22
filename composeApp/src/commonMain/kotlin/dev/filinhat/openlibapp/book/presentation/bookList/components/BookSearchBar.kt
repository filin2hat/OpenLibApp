package dev.filinhat.openlibapp.book.presentation.bookList.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import dev.filinhat.openlibapp.core.presentation.DarkBlue
import dev.filinhat.openlibapp.core.presentation.DesertWhite
import dev.filinhat.openlibapp.core.presentation.SandYellow
import openlibrarycmpapp.composeapp.generated.resources.Res
import openlibrarycmpapp.composeapp.generated.resources.clear_search_query
import openlibrarycmpapp.composeapp.generated.resources.search_hint
import org.jetbrains.compose.resources.stringResource

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
    CompositionLocalProvider(
        LocalTextSelectionColors provides
            TextSelectionColors(
                handleColor = SandYellow,
                backgroundColor = SandYellow,
            ),
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            shape = RoundedCornerShape(100),
            colors =
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = SandYellow,
                    cursorColor = DarkBlue,
                ),
            placeholder = {
                Text(text = stringResource(Res.string.search_hint))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.66f),
                )
            },
            singleLine = true,
            keyboardActions =
                KeyboardActions(
                    onSearch = {
                        onImeSearch()
                    },
                ),
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search,
                ),
            trailingIcon = {
                AnimatedVisibility(
                    visible = searchQuery.isNotBlank(),
                ) {
                    IconButton(
                        onClick = {
                            onSearchQueryChange("")
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(Res.string.clear_search_query),
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            },
            modifier =
                modifier
                    .minimumInteractiveComponentSize()
                    .background(
                        shape = RoundedCornerShape(100),
                        color = DesertWhite,
                    ),
        )
    }
}
