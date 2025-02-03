package dev.filinhat.openlibapp.book.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

object StringListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<String> = Json.decodeFromString(value)

    @TypeConverter
    fun fromList(list: List<String>): String = Json.encodeToString(list)
}
