package dev.filinhat.openlibapp.book.data.database

import androidx.room.RoomDatabaseConstructor

/**
 * Конструктор базы данных в зависимости от платформ. Используется для в новых версиях Room
 */
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor : RoomDatabaseConstructor<FavoriteBookDatabase> {
    override fun initialize(): FavoriteBookDatabase
}
