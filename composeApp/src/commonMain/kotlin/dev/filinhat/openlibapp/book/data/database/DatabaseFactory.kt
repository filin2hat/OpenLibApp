package dev.filinhat.openlibapp.book.data.database

import androidx.room.RoomDatabase

/**
 * Фабрика базы данных в зависимости от платформы
 */
expect class DatabaseFactory {
    fun createDatabase(): RoomDatabase.Builder<FavoriteBookDatabase>
}
