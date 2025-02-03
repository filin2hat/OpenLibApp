package dev.filinhat.openlibapp.book.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun createDatabase(): RoomDatabase.Builder<FavoriteBookDatabase>
}
