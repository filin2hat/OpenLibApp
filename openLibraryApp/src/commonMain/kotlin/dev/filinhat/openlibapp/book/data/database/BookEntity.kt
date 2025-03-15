package dev.filinhat.openlibapp.book.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Таблица с книгами в избранном.
 */
@Entity(tableName = "FavoriteBooks")
data class BookEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val languages: List<String>,
    val authors: List<String>,
    val firstPublishYear: String?,
    val raitingAverage: Double?,
    val raitingCount: Int?,
    val numPagesMedian: Int?,
    val numEdition: Int,
)
