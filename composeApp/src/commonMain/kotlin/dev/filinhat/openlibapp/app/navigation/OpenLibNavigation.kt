package dev.filinhat.openlibapp.app.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.filinhat.openlibapp.book.presentation.bookDetail.BookDetailAction
import dev.filinhat.openlibapp.book.presentation.bookDetail.BookDetailScreenRoot
import dev.filinhat.openlibapp.book.presentation.bookDetail.BookDetailViewModel
import dev.filinhat.openlibapp.book.presentation.bookList.BookListScreenRoot
import dev.filinhat.openlibapp.book.presentation.bookList.BookListViewModel
import dev.filinhat.openlibapp.book.presentation.sharedViewModel.SelectedBookViewModel
import dev.filinhat.openlibapp.core.navigation.sharedKoinViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Навигационный граф приложения [OpenLibApp].
 */
@Composable
fun OpenLibNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = OpenLibRoute.BookGraph,
    ) {
        navigation<OpenLibRoute.BookGraph>(startDestination = OpenLibRoute.BookList) {
            composable<OpenLibRoute.BookList>(
                exitTransition = { slideOutHorizontally() },
                popEnterTransition = { slideInHorizontally() },
            ) { navBackStackEntry ->
                val viewModel = koinViewModel<BookListViewModel>()
                val selectedBookViewModel =
                    navBackStackEntry.sharedKoinViewModel<SelectedBookViewModel>(navController)

                LaunchedEffect(true) {
                    selectedBookViewModel.onSelectBook(null)
                }

                BookListScreenRoot(
                    viewModel = viewModel,
                    onBookClick = { book ->
                        selectedBookViewModel.onSelectBook(book)
                        navController.navigate(OpenLibRoute.BookDetail(book.id))
                    },
                )
            }
            composable<OpenLibRoute.BookDetail>(
                enterTransition = {
                    slideInHorizontally { initialOffset ->
                        initialOffset
                    }
                },
                exitTransition = {
                    slideOutHorizontally { initialOffset ->
                        initialOffset
                    }
                },
            ) { navBackStackEntry ->
                val selectedBookViewModel =
                    navBackStackEntry.sharedKoinViewModel<SelectedBookViewModel>(navController = navController)
                val viewModel = koinViewModel<BookDetailViewModel>()
                val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

                LaunchedEffect(selectedBook) {
                    selectedBook?.let { book ->
                        viewModel.onAction(BookDetailAction.OnSelectedBookChange(book))
                    }
                }

                BookDetailScreenRoot(
                    viewModel = viewModel,
                    onBackClick = { navController.navigateUp() },
                )
            }
        }
    }
}
