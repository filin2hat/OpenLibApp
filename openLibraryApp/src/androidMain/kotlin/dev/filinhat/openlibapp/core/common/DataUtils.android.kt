package dev.filinhat.openlibapp.core.common

import java.util.Calendar

actual fun getCurrentYear(): Int = Calendar.getInstance().get(Calendar.YEAR)
