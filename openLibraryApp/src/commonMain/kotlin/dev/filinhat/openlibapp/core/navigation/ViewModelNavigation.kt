package dev.filinhat.openlibapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel

/**
 * Возвращает экземпляр ViewModel, который может быть общим между несколькими NavBackStackEntry.
 *
 * Если родительский навигационный граф имеет маршрут, функция возвращает ViewModel, связанный с этим графом.
 * В противном случае функция возвращает ViewModel, созданный с помощью koinViewModel.
 *
 * @param navController NavController, используемый для навигации
 * @return Экземпляр ViewModel, который может быть общией между несколькими NavBackStackEntry
 */
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry =
        remember(this) {
            navController.getBackStackEntry(navGraphRoute)
        }
    return koinViewModel(
        viewModelStoreOwner = parentEntry,
    )
}
