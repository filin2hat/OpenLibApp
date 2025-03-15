package dev.filinhat.openlibapp.book.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

/**
 * Конвертер для преобразования списка строк в строку и обратно.
 */
object StringListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<String> = Json.decodeFromString(value)

    @TypeConverter
    fun fromList(list: List<String>): String = Json.encodeToString(list)
}
