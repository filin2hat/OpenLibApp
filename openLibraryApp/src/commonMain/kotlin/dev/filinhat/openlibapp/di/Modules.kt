package dev.filinhat.openlibapp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.filinhat.openlibapp.book.data.database.DatabaseFactory
import dev.filinhat.openlibapp.book.data.database.FavoriteBookDatabase
import dev.filinhat.openlibapp.book.data.network.KtorRemoteBookDataSource
import dev.filinhat.openlibapp.book.data.network.RemoteBookDataSource
import dev.filinhat.openlibapp.book.data.repository.DefaultBookRepository
import dev.filinhat.openlibapp.book.domain.BookRepository
import dev.filinhat.openlibapp.book.presentation.bookDetail.BookDetailViewModel
import dev.filinhat.openlibapp.book.presentation.bookList.BookListViewModel
import dev.filinhat.openlibapp.book.presentation.sharedViewModel.SelectedBookViewModel
import dev.filinhat.openlibapp.core.data.network.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Модуль, содержащий зависимости, специфичные для платформы
 */
expect val platformModule: Module

/**
 * Модуль, содержащий зависимости, которые не зависят от платформы
 */
val sharedModule =
    module {
        single { HttpClientFactory.create(get()) }
        singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
        singleOf(::DefaultBookRepository).bind<BookRepository>()

        single {
            get<DatabaseFactory>()
                .createDatabase()
                .setDriver(BundledSQLiteDriver())
                .build()
        }
        single { get<FavoriteBookDatabase>().favoriteBookDao }

        viewModelOf(::BookListViewModel)
        viewModelOf(::SelectedBookViewModel)
        viewModelOf(::BookDetailViewModel)
    }
