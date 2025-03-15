package dev.filinhat.openlibapp.book.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context,
) {
    actual fun createDatabase(): RoomDatabase.Builder<FavoriteBookDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(FavoriteBookDatabase.DATABASE_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath,
        )
    }
}
