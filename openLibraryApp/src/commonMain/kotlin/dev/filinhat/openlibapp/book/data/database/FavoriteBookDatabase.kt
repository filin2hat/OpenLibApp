package dev.filinhat.openlibapp.book.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * База данных для избранных книг.
 */
@Database(
    entities = [BookEntity::class],
    version = 1,
)
@TypeConverters(
    StringListTypeConverter::class,
)
@ConstructedBy(BookDatabaseConstructor::class)
abstract class FavoriteBookDatabase : RoomDatabase() {
    abstract val favoriteBookDao: FavoriteBookDao

    companion object {
        const val DATABASE_NAME = "favorite_books.db"
    }
}
