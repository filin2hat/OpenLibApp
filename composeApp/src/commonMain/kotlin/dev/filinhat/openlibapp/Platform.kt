package dev.filinhat.openlibapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform