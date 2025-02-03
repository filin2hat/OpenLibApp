package dev.filinhat.openlibapp.book.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookDao {
    @Upsert
    suspend fun upsert(bookEntity: BookEntity)

    @Query("SELECT * FROM FavoriteBooks")
    fun geFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM FavoriteBooks WHERE id = :id")
    suspend fun getFavoriteBook(id: String): BookEntity?

    @Query("DELETE FROM FavoriteBooks WHERE id = :id")
    suspend fun deleteFavoriteBook(id: String)
}
