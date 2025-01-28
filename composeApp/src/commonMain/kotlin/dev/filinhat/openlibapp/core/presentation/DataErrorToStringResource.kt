package dev.filinhat.openlibapp.core.presentation

import dev.filinhat.openlibapp.core.domain.DataError
import openlibrarycmpapp.composeapp.generated.resources.Res
import openlibrarycmpapp.composeapp.generated.resources.error_disk_full
import openlibrarycmpapp.composeapp.generated.resources.error_no_data
import openlibrarycmpapp.composeapp.generated.resources.error_no_internet_connection
import openlibrarycmpapp.composeapp.generated.resources.error_request_timeout
import openlibrarycmpapp.composeapp.generated.resources.error_serialization
import openlibrarycmpapp.composeapp.generated.resources.error_server
import openlibrarycmpapp.composeapp.generated.resources.error_too_many_requests
import openlibrarycmpapp.composeapp.generated.resources.error_unknown_local
import openlibrarycmpapp.composeapp.generated.resources.error_unknown_remote

fun DataError.toUiText(): UiText {
    val string =
        when (this) {
            DataError.Local.DISK_FULL_ERROR -> Res.string.error_disk_full
            DataError.Local.NO_DATA_ERROR -> Res.string.error_no_data
            DataError.Local.UNKNOWN_ERROR -> Res.string.error_unknown_local
            DataError.Remote.REQUEST_TIMEOUT_ERROR -> Res.string.error_request_timeout
            DataError.Remote.TOO_MANY_REQUESTS_ERROR -> Res.string.error_too_many_requests
            DataError.Remote.NO_INTERNET_CONNECTION_ERROR -> Res.string.error_no_internet_connection
            DataError.Remote.SERVER_ERROR -> Res.string.error_server
            DataError.Remote.SERIALIZATION_ERROR -> Res.string.error_serialization
            DataError.Remote.UNKNOWN_ERROR -> Res.string.error_unknown_remote
        }
    return UiText.StringResourceId(string)
}
