package dev.filinhat.openlibapp.book.presentation.bookList.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import dev.filinhat.openlibapp.book.domain.Book
import dev.filinhat.openlibapp.core.presentation.PulseAnimation
import dev.filinhat.openlibapp.core.presentation.theme.SandYellow
import openlibrarycmpapp.composeapp.generated.resources.Res
import openlibrarycmpapp.composeapp.generated.resources.book_error_2
import org.jetbrains.compose.resources.painterResource
import kotlin.math.round

/**
 * Компонент для отображения элемента списка книг.
 *
 * Отображает информацию о книге, такую как название, автор, рейтинг и обложку.
 *
 * @param book Объект книги, данные которой будут отображены.
 * @param onClick Функция, вызываемая при нажатии на элемент списка.
 * @param modifier Модификатор для настройки внешнего вида компонента.
 */
@Composable
fun BookListItem(
    book: Book,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.surfaceBright,
        modifier = modifier.clickable(onClick = onClick),
    ) {
        Row(
            modifier =
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier.height(100.dp),
                contentAlignment = Alignment.Center,
            ) {
                var imageLoaderResult by remember { mutableStateOf<Result<Painter>?>(null) }
                val painter =
                    rememberAsyncImagePainter(
                        model = book.imageUrl,
                        onSuccess = {
                            imageLoaderResult =
                                if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                                    Result.success(it.painter)
                                } else {
                                    Result.failure(Exception("Invalid image size"))
                                }
                        },
                        onError = {
                            it.result.throwable.printStackTrace()
                            imageLoaderResult = Result.failure(it.result.throwable)
                        },
                    )

                val painterState by painter.state.collectAsStateWithLifecycle()
                val transition by animateFloatAsState(
                    targetValue = if (painterState is AsyncImagePainter.State.Success) 1f else 0f,
                    animationSpec = tween(durationMillis = 800),
                )

                when (val result = imageLoaderResult) {
                    null ->
                        PulseAnimation(modifier = Modifier.size(60.dp))

                    else -> {
                        Image(
                            painter = if (result.isSuccess) painter else painterResource(Res.drawable.book_error_2),
                            contentDescription = book.title,
                            contentScale = if (result.isSuccess) ContentScale.Crop else ContentScale.Fit,
                            modifier =
                                Modifier
                                    .aspectRatio(
                                        ratio = 0.65f,
                                        matchHeightConstraintsFirst = true,
                                    ).graphicsLayer {
                                        rotationX = (1f - transition) * 60
                                        val scale = 0.8f + (0.2f * transition)
                                        scaleX = scale
                                        scaleY = scale
                                    },
                        )
                    }
                }
            }

            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                book.authors.firstOrNull()?.let { author ->
                    Text(
                        text = author,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                book.firstPublishYear?.let { firstPublishYear ->
                    Text(
                        text = firstPublishYear,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                book.averageRating?.let { avagRating ->

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = SandYellow,
                            modifier = Modifier.offset(y = (-1).dp),
                        )
                        Text(
                            text = "${round(avagRating * 10) / 10.0}",
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = 4.dp),
                        )
                    }
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(36.dp),
            )
        }
    }
}
