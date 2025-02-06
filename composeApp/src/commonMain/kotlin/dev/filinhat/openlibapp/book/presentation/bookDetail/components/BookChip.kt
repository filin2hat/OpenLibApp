package dev.filinhat.openlibapp.book.presentation.bookDetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.filinhat.openlibapp.core.presentation.theme.LightBlue

/**
 * Компонент чипа для отображения информации.
 *
 * @param modifier Модификатор для настройки внешнего вида чипа.
 * @param size Размер чипа ([ChipSize.SMALL] или [ChipSize.REGULAR]).
 * @param chipContent Содержимое чипа.
 */
@Composable
fun BookChip(
    modifier: Modifier = Modifier,
    size: ChipSize = ChipSize.REGULAR,
    chipContent: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier =
            modifier
                .widthIn(
                    min =
                        when (size) {
                            ChipSize.SMALL -> 50.dp
                            ChipSize.REGULAR -> 80.dp
                        },
                ).clip(RoundedCornerShape(16.dp))
                .background(LightBlue)
                .padding(vertical = 8.dp, horizontal = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            chipContent()
        }
    }
}

/**
 * Возможные размеры чипа.
 */
enum class ChipSize {
    /**
     * Маленький размер чипа.
     */
    SMALL,

    /**
     * Обычный размер чипа.
     */
    REGULAR,
}
