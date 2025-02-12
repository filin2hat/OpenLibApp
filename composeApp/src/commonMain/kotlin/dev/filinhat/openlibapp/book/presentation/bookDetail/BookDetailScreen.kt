package dev.filinhat.openlibapp.book.presentation.bookDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.filinhat.openlibapp.book.presentation.bookDetail.components.BlurredImageBackground
import dev.filinhat.openlibapp.book.presentation.bookDetail.components.BookChip
import dev.filinhat.openlibapp.book.presentation.bookDetail.components.ChipSize
import dev.filinhat.openlibapp.book.presentation.bookDetail.components.TitledContent
import dev.filinhat.openlibapp.core.presentation.PulseAnimation
import openlibapp.composeapp.generated.resources.Res
import openlibapp.composeapp.generated.resources.book_description_unavailable
import openlibapp.composeapp.generated.resources.book_languages
import openlibapp.composeapp.generated.resources.book_pages
import openlibapp.composeapp.generated.resources.book_raiting
import openlibapp.composeapp.generated.resources.book_synopsis
import org.jetbrains.compose.resources.stringResource
import kotlin.math.round

/**
 * Корневой компонент экрана с детальной информацией о книге.
 *
 * Компонент отвечает за сбор состояния из [BookDetailViewModel]
 * и передачу его в [BookDetailScreen].
 *
 * @param viewModel ViewModel для экрана с детальной информацией о книге.
 * @param onBackClick Лямбда-функция, вызываемая при нажатии кнопки "Назад".
 * @see BookDetailScreen
 */
@Composable
fun BookDetailScreenRoot(
    viewModel: BookDetailViewModel,
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookDetailScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is BookDetailAction.OnBackClick -> onBackClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },
    )
}

/**
 * Экран с детальной информацией о книге.
 *
 * Компонент отображает детальную информацию о книге,
 * включая название, авторов, рейтинг, количество страниц, языки и описание.
 *
 * @param state Состояние экрана.
 * @param onAction Лямбда-функция, вызываемая при возникновении действий пользователя.
 * @see BookDetailState
 * @see BookDetailAction
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BookDetailScreen(
    state: BookDetailState,
    onAction: (BookDetailAction) -> Unit,
) {
    BlurredImageBackground(
        imageUrl = state.book?.imageUrl,
        isFavorite = state.isFavorite,
        onFavoriteClick = { onAction(BookDetailAction.OnFavoriteClick) },
        onBackClick = { onAction(BookDetailAction.OnBackClick) },
        modifier = Modifier.fillMaxSize(),
    ) {
        if (state.book != null) {
            Column(
                modifier =
                    Modifier
                        .widthIn(max = 700.dp)
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                        .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = state.book.title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = state.book.authors.joinToString(" ,"),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    state.book.averageRating?.let { raiting ->
                        TitledContent(
                            title = stringResource(Res.string.book_raiting),
                        ) {
                            BookChip {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.offset(y = (-1).dp),
                                )
                                Text(
                                    text = "${round(raiting * 10) / 10.0}",
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(start = 4.dp, end = 8.dp),
                                )
                            }
                        }
                    }

                    state.book.numPages?.let { pageCount ->
                        TitledContent(
                            title = stringResource(Res.string.book_pages),
                        ) {
                            BookChip {
                                Text(
                                    text = pageCount.toString(),
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            }
                        }
                    }
                }
                if (state.book.description.isNotBlank()) {
                    Text(
                        text = stringResource(Res.string.book_synopsis),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier =
                            Modifier
                                .align(Alignment.Start)
                                .fillMaxWidth()
                                .padding(top = 24.dp, bottom = 8.dp),
                    )
                }

                if (state.isLoading) {
                    PulseAnimation(modifier = Modifier.size(60.dp))
                } else {
                    Text(
                        text =
                            state.book.description.ifBlank {
                                stringResource(Res.string.book_description_unavailable)
                            },
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(vertical = 8.dp),
                    )
                }

                if (state.book.languages.isNotEmpty()) {
                    TitledContent(
                        title = stringResource(Res.string.book_languages),
                        modifier = Modifier.padding(vertical = 6.dp),
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.wrapContentSize(Alignment.Center),
                        ) {
                            state.book.languages.forEach { language ->
                                BookChip(
                                    size = ChipSize.SMALL,
                                    modifier = Modifier.padding(2.dp),
                                ) {
                                    Text(
                                        text = language.uppercase(),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
