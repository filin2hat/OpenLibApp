package dev.filinhat.openlibapp.core.utils

import java.util.Calendar

actual fun getCurrentYear(): Int = Calendar.getInstance().get(Calendar.YEAR)
