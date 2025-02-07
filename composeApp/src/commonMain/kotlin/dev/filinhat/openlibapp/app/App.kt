package dev.filinhat.openlibapp.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.filinhat.openlibapp.book.presentation.bookDetail.BookDetailAction
import dev.filinhat.openlibapp.book.presentation.bookDetail.BookDetailScreenRoot
import dev.filinhat.openlibapp.book.presentation.bookDetail.BookDetailViewModel
import dev.filinhat.openlibapp.book.presentation.bookList.BookListScreenRoot
import dev.filinhat.openlibapp.book.presentation.bookList.BookListViewModel
import dev.filinhat.openlibapp.book.presentation.bookList.SelectedBookViewModel
import dev.filinhat.openlibapp.core.presentation.theme.OpenLibAppTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    OpenLibAppTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.BookGraph,
        ) {
            navigation<Route.BookGraph>(startDestination = Route.BookList) {
                composable<Route.BookList>(
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
                            navController.navigate(Route.BookDetail(book.id))
                        },
                    )
                }
                composable<Route.BookDetail>(
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
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry =
        remember(this) {
            navController.getBackStackEntry(navGraphRoute)
        }
    return koinViewModel(
        viewModelStoreOwner = parentEntry,
    )
}
