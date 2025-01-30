package dev.filinhat.openlibapp.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.filinhat.openlibapp.book.presentation.bookList.BookListScreenRoot
import dev.filinhat.openlibapp.book.presentation.bookList.BookListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(modifier: Modifier = Modifier) {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.BookGraph,
        ) {
            navigation<Route.BookGraph>(startDestination = Route.BookList) {
                composable<Route.BookList> {
                    BookListScreenRoot(
                        viewModel = koinViewModel<BookListViewModel>(),
                        onBookClick = { book ->
                            navController.navigate(
                                Route.BookDetails(book.id),
                            )
                        },
                    )
                }
            }
            composable<Route.BookDetails> { entry ->
                val args = entry.toRoute<Route.BookDetails>()
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("Book details: ${args.bookId}")
                }
            }
        }
    }
}
