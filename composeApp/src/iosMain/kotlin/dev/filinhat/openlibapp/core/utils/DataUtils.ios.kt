package dev.filinhat.openlibapp.core.utils

import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarIdentifierGregorian
import platform.Foundation.NSDate
import platform.Foundation.NSYearCalendarUnit

actual fun getCurrentYear(): Int {
    val calendar = NSCalendar(NSCalendarIdentifierGregorian)
    val currentDate = NSDate()
    val components = calendar.components(NSYearCalendarUnit, currentDate)
    return components.year.toInt()
}
