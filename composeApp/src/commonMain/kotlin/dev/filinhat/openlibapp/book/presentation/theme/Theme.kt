package dev.filinhat.openlibapp.book.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

private val LightColors =
    lightColorScheme(
        primary = primaryAppLight,
        secondary = secondaryAppLight,
        onPrimary = onPrimaryAppLight,
        error = errorAppLight,
        surface = surfaceAppLight,
        onSurface = onSurfaceAppLight,
        surfaceDim = surfaceVariantAppLight,
        surfaceBright = surfaceBrightAppLight,
        background = backgroundAppLight,
    )

private val DarkColors =
    darkColorScheme(
        primary = primaryAppDark,
        secondary = secondaryAppDark,
        onPrimary = onPrimaryAppDark,
        error = errorAppDark,
        surface = surfaceAppDark,
        onSurface = onSurfaceAppDark,
        surfaceDim = surfaceVariantAppDark,
        surfaceBright = surfaceBrightAppDark,
        background = backgroundAppDark,
    )

@Suppress("ktlint:compose:compositionlocal-allowlist")
internal val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }

@Composable
internal fun OpenLibAppTheme(content: @Composable () -> Unit) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember { mutableStateOf(systemIsDark) }
    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkState,
    ) {
        val isDark by isDarkState
        SystemAppearance(!isDark)
        MaterialTheme(
            colorScheme = if (isDark) DarkColors else LightColors,
            content = { Surface(content = content) },
        )
    }
}
