package dev.filinhat.openlibapp.book.data.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

/**
 * Пользовательский сериализатор для [BookWorkDto].
 *
 * Этот сериализатор обрабатывает десериализацию поля "description",
 * которое может быть либо объектом [DescriptionDto], либо простой строкой.
 *
 * @see BookWorkDto
 */
object BookWorkDtoSerializer : KSerializer<BookWorkDto> {
    /**
     * Дескриптор сериализатора.
     */
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor(
            BookWorkDto::class.simpleName!!,
        ) {
            element<String?>("description")
        }

    /**
     * Десериализует данные в объект [BookWorkDto].
     *
     * @param decoder Декодер для десериализации.
     * @return Десериализованный объект [BookWorkDto].
     * @throws SerializationException Если декодер не является [JsonDecoder] или если обнаружен неожиданный индекс.
     */
    override fun deserialize(decoder: Decoder): BookWorkDto =
        decoder.decodeStructure(descriptor) {
            var description: String? = null

            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> {
                        val jsonDecoder =
                            decoder as? JsonDecoder ?: throw SerializationException(
                                "This decoder only works with JSON.",
                            )
                        val element = jsonDecoder.decodeJsonElement()
                        description =
                            if (element is JsonObject) {
                                decoder.json
                                    .decodeFromJsonElement<DescriptionDto>(
                                        element = element,
                                        deserializer = DescriptionDto.serializer(),
                                    ).value
                            } else if (element is JsonPrimitive && element.isString) {
                                element.content
                            } else {
                                null
                            }
                    }

                    CompositeDecoder.DECODE_DONE -> break
                    else -> throw SerializationException("Unexpected index $index")
                }
            }

            return@decodeStructure BookWorkDto(description)
        }

    /**
     * Сериализует объект [BookWorkDto] в данные.
     *
     * @param encoder Энкодер для сериализации.
     * @param value Объект [BookWorkDto] для сериализации.
     */
    override fun serialize(
        encoder: Encoder,
        value: BookWorkDto,
    ) = encoder.encodeStructure(
        descriptor,
    ) {
        value.description?.let {
            encodeStringElement(descriptor, 0, it)
        }
    }
}
