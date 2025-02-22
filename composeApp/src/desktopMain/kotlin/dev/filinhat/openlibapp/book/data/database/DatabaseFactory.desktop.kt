package dev.filinhat.openlibapp.book.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun createDatabase(): RoomDatabase.Builder<FavoriteBookDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir =
            when {
                os.contains("win") -> File(System.getenv("APPDATA"), "OpenLibApp")
                os.contains("mac") -> File(userHome, "Library/Application Support/OpenLibApp")
                else -> File(userHome, ".local/share/OpenLibApp")
            }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, FavoriteBookDatabase.DATABASE_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}
