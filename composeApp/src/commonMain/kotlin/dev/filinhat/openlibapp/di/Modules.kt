package dev.filinhat.openlibapp.di

import dev.filinhat.openlibapp.book.data.network.KtorRemoteBookDataSource
import dev.filinhat.openlibapp.book.data.network.RemoteBookDataSource
import dev.filinhat.openlibapp.book.data.repository.DefaultBookRepository
import dev.filinhat.openlibapp.book.domain.BookRepository
import dev.filinhat.openlibapp.book.presentation.bookList.BookListViewModel
import dev.filinhat.openlibapp.book.presentation.bookList.SelectedBookViewModel
import dev.filinhat.openlibapp.core.data.network.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule =
    module {
        single { HttpClientFactory.create(get()) }
        singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
        singleOf(::DefaultBookRepository).bind<BookRepository>()

        viewModelOf(::BookListViewModel)
        viewModelOf(::SelectedBookViewModel)
    }
