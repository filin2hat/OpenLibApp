package dev.filinhat.openlibapp.core.common

import java.time.Year

actual fun getCurrentYear(): Int {
    return Year.now().value
}
