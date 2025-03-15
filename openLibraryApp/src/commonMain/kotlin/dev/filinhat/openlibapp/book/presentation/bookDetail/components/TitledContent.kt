package dev.filinhat.openlibapp.book.presentation.bookDetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Компонент для отображения контента с заголовком.
 *
 * Компонент отображает заголовок ([title]) и контент ([content]) под ним.
 *
 * @param title Заголовок, который будет отображен.
 * @param modifier Модификатор для настройки внешнего вида компонента.
 * @param content Контент, который будет отображен под заголовком.
 */
@Composable
fun TitledContent(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        content()
    }
}
