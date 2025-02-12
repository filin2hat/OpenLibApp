package dev.filinhat.openlibapp.book.presentation.bookDetail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import dev.filinhat.openlibapp.core.presentation.PulseAnimation
import openlibapp.composeapp.generated.resources.Res
import openlibapp.composeapp.generated.resources.book_cover
import openlibapp.composeapp.generated.resources.book_error_2
import openlibapp.composeapp.generated.resources.btn_go_back
import openlibapp.composeapp.generated.resources.btn_mark_as_favorite
import openlibapp.composeapp.generated.resources.btn_remove_from_favorites
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * Компонент фона с картинкой книги.
 *
 * @param imageUrl Ссылка на изображение книги.
 * @param isFavorite Флаг, показывающий наличие книги в избранном.
 * @param onFavoriteClick Обработчик нажатия на кнопку "Добавить в избранное".
 * @param onBackClick Обработчик нажатия на кнопку "Назад".
 * @param modifier Модификатор для настройки внешнего вида компонента.
 * @param content Содержимое компонента.
 */
@Composable
fun BlurredImageBackground(
    imageUrl: String?,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    var imageLoadResult by remember { mutableStateOf<Result<Painter>?>(null) }
    val painter =
        rememberAsyncImagePainter(
            model = imageUrl,
            onSuccess = {
                val size = it.painter.intrinsicSize
                imageLoadResult =
                    if (size.width > 1 && size.height > 1) {
                        Result.success(it.painter)
                    } else {
                        Result.failure(Exception("Invalid image size"))
                    }
            },
            onError = {
                it.result.throwable.stackTraceToString()
            },
        )

    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier =
                    Modifier
                        .weight(0.3f)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background),
            ) {
                imageLoadResult?.getOrNull()?.let { painter ->
                    Image(
                        painter = painter,
                        contentDescription = stringResource(Res.string.book_cover),
                        contentScale = ContentScale.Crop,
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .blur(20.dp),
                    )
                }
            }

            Box(
                modifier =
                    Modifier
                        .weight(0.7f)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface),
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButton(
                onClick = onBackClick,
                modifier =
                    Modifier
                        .padding(top = 16.dp, start = 16.dp)
                        .statusBarsPadding()
                        .background(
                            brush =
                                Brush.radialGradient(
                                    colors =
                                        listOf(
                                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f),
                                            Color.Transparent,
                                        ),
                                    radius = 60f,
                                ),
                        ),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.btn_go_back),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(36.dp),
                )
            }

            IconButton(
                onClick = onFavoriteClick,
                modifier =
                    Modifier
                        .padding(top = 16.dp, end = 16.dp)
                        .statusBarsPadding()
                        .background(
                            brush =
                                Brush.radialGradient(
                                    colors =
                                        listOf(
                                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f),
                                            Color.Transparent,
                                        ),
                                    radius = 60f,
                                ),
                        ),
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription =
                        if (isFavorite) {
                            stringResource(Res.string.btn_remove_from_favorites)
                        } else {
                            stringResource(
                                Res.string.btn_mark_as_favorite,
                            )
                        },
                    tint = MaterialTheme.colorScheme.primary,
                    modifier =
                        Modifier
                            .size(32.dp),
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.15f))

            Box(
                modifier =
                    Modifier
                        .height(250.dp)
                        .aspectRatio(2 / 3f),
            ) {
                AnimatedContent(
                    targetState = imageLoadResult,
                ) { result ->
                    when (result) {
                        null ->
                            Box(
                                modifier =
                                    Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                PulseAnimation(
                                    modifier =
                                        Modifier
                                            .offset(y = 40.dp)
                                            .size(80.dp),
                                )
                            }

                        else -> {
                            Box {
                                Image(
                                    painter = if (result.isSuccess) painter else painterResource(Res.drawable.book_error_2),
                                    contentDescription = stringResource(Res.string.book_cover),
                                    contentScale = if (result.isSuccess) ContentScale.Crop else ContentScale.Fit,
                                    modifier =
                                        Modifier
                                            .background(Color.Transparent)
                                            .fillMaxSize(),
                                )
                            }
                        }
                    }
                }
            }
            content()
        }
    }
}
