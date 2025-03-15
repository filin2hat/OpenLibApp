package dev.filinhat.openlibapp

import android.app.Application
import dev.filinhat.openlibapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApplication)
        }
    }
}
