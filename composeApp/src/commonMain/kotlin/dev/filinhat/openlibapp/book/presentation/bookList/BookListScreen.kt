package dev.filinhat.openlibapp.book.presentation.bookList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.filinhat.openlibapp.book.domain.Book
import dev.filinhat.openlibapp.book.presentation.bookList.components.BookList
import dev.filinhat.openlibapp.book.presentation.bookList.components.BookSearchBar
import dev.filinhat.openlibapp.core.presentation.DarkBlue
import dev.filinhat.openlibapp.core.presentation.DesertWhite
import dev.filinhat.openlibapp.core.presentation.SandYellow
import openlibrarycmpapp.composeapp.generated.resources.Res
import openlibrarycmpapp.composeapp.generated.resources.empty_favorites_list
import openlibrarycmpapp.composeapp.generated.resources.favorites
import openlibrarycmpapp.composeapp.generated.resources.no_results
import openlibrarycmpapp.composeapp.generated.resources.search_results
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

/**
 * Корневой компонент экрана списка книг.
 *
 * Компонент отвечает за сбор состояния из [BookListViewModel]
 * и передачу его в [BookListScreen].
 *
 * @param viewModel ViewModel для экрана списка книг.
 * @param onBookClick Лямбда-функция, вызываемая при нажатии на книгу.
 * @see BookListScreen
 */
@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel = koinViewModel(),
    onBookClick: (Book) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is BookListAction.OnBookClick -> onBookClick(action.book)
                else -> Unit
            }
            viewModel.onAction(action)
        },
    )
}

/**
 * Экран списка книг.
 *
 * Компонент отображает список книг, результаты поиска и избранные книги.
 * Он также включает в себя строку поиска и вкладки для переключения между результатами поиска и избранным.
 *
 * @param state Состояние экрана.
 * @param onAction Лямбда-функция, вызываемая при возникновении действий пользователя.
 * @param modifier Модификатор для настройки внешнего вида компонента.
 * @see BookListState
 * @see BookListAction
 */
@Composable
fun BookListScreen(
    state: BookListState,
    onAction: (BookListAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val pagerState = rememberPagerState { 2 }
    val searchResultListState = rememberLazyListState()
    val favoriteBooksResultListState = rememberLazyListState()

    LaunchedEffect(state.searchResults) {
        searchResultListState.animateScrollToItem(0)
    }

    LaunchedEffect(state.selectedTabIndex) {
        pagerState.animateScrollToPage(state.selectedTabIndex)
    }

    val currentOnAction by rememberUpdatedState(onAction)
    LaunchedEffect(pagerState.currentPage) {
        currentOnAction(BookListAction.OnTabSelected(pagerState.currentPage))
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(DarkBlue)
                .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BookSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(BookListAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier =
                Modifier
                    .widthIn(max = 400.dp)
                    .fillMaxWidth()
                    .padding(16.dp),
        )

        Surface(
            modifier =
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp),
            color = DesertWhite,
            shape =
                RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp,
                ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    modifier =
                        Modifier
                            .padding(vertical = 12.dp)
                            .widthIn(max = 700.dp)
                            .fillMaxWidth(),
                    containerColor = DesertWhite,
                    contentColor = SandYellow,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            color = SandYellow,
                            modifier =
                                Modifier
                                    .tabIndicatorOffset(tabPositions[state.selectedTabIndex]),
                        )
                    },
                ) {
                    Tab(
                        selected = state.selectedTabIndex == 0,
                        onClick = {
                            onAction(BookListAction.OnTabSelected(0))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f),
                    ) {
                        Text(
                            text = stringResource(Res.string.search_results),
                            modifier = Modifier.padding(vertical = 12.dp),
                        )
                    }

                    Tab(
                        selected = state.selectedTabIndex == 1,
                        onClick = {
                            onAction(BookListAction.OnTabSelected(1))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f),
                    ) {
                        Text(
                            text = stringResource(Res.string.favorites),
                            modifier = Modifier.padding(vertical = 12.dp),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                HorizontalPager(
                    state = pagerState,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                ) { pageIndex ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        when (pageIndex) {
                            0 -> {
                                if (state.isLoading) {
                                    CircularProgressIndicator()
                                } else {
                                    when {
                                        state.errorMessage != null -> {
                                            Text(
                                                text = state.errorMessage.asString(),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error,
                                                modifier = Modifier.padding(8.dp),
                                            )
                                        }

                                        state.searchResults.isEmpty() -> {
                                            Text(
                                                text = stringResource(Res.string.no_results),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                modifier = Modifier.padding(8.dp),
                                            )
                                        }

                                        else -> {
                                            BookList(
                                                books = state.searchResults,
                                                onBookClick = { book ->
                                                    onAction(BookListAction.OnBookClick(book))
                                                },
                                                modifier = Modifier.fillMaxSize(),
                                                scrollState = searchResultListState,
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                if (state.favoriteBooks.isEmpty()) {
                                    Text(
                                        text = stringResource(Res.string.empty_favorites_list),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.headlineSmall,
                                        modifier = Modifier.padding(8.dp),
                                    )
                                } else {
                                    BookList(
                                        books = state.favoriteBooks,
                                        onBookClick = { book ->
                                            onAction(BookListAction.OnBookClick(book))
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                        scrollState = favoriteBooksResultListState,
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
