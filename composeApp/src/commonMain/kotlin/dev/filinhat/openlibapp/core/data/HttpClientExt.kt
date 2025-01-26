package dev.filinhat.openlibapp.core.data

import dev.filinhat.openlibapp.core.domain.DataError
import dev.filinhat.openlibapp.core.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

/**
 * Выполняет безопасный вызов сетевого запроса и обрабатывает возможные ошибки.
 *
 * Функция оборачивает выполнение сетевого запроса в блок `try-catch`
 * и обрабатывает распространенные ошибки, такие как тайм-аут запроса,
 * отсутствие интернет-соединения и неизвестные ошибки.
 *
 * В случае успеха, функция возвращает `Result.Success` с результатом запроса,
 * преобразованным в тип `T`.
 * В случае ошибки, функция возвращает `Result.Error` с соответствующей ошибкой
 * типа `DataError.Remote`.
 *
 * @param T Тип результата запроса.
 * @param execute Функция, выполняющая сетевой запрос и возвращающая `HttpResponse`.
 * @return `Result` с результатом запроса или ошибкой.
 */
suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T, DataError.Remote> {
    val response =
        try {
            execute()
        } catch (e: SocketTimeoutException) {
            return Result.Error(DataError.Remote.REQUEST_TIMEOUT_ERROR)
        } catch (e: UnresolvedAddressException) {
            return Result.Error(DataError.Remote.NO_INTERNET_CONNECTION_ERROR)
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            return Result.Error(DataError.Remote.UNKNOWN_ERROR)
        }
    return responseToResult(response)
}

/**
 * Преобразует ответ от сервера (`HttpResponse`) в `Result`.
 *
 * Эта функция проверяет код состояния ответа и возвращает `Result.Success`
 * с десериализованным телом ответа, если код состояния находится в диапазоне 200-299.
 *
 * В случае ошибки, функция возвращает `Result.Error` с соответствующей ошибкой
 * типа `DataError.Remote`.
 *
 * @param T Тип результата запроса.
 * @param response Ответ от сервера (`HttpResponse`).
 * @return `Result` с результатом запроса или ошибкой.
 */
suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError.Remote> =
    when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Result.Error(DataError.Remote.SERIALIZATION_ERROR)
            }
        }

        408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT_ERROR)
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS_ERROR)
        in 500..599 -> Result.Error(DataError.Remote.SERVER_ERROR)
        else -> Result.Error(DataError.Remote.UNKNOWN_ERROR)
    }
