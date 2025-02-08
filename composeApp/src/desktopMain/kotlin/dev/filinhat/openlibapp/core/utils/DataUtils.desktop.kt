package dev.filinhat.openlibapp.core.utils

import java.time.Year

actual fun getCurrentYear(): Int {
    return Year.now().value
}
