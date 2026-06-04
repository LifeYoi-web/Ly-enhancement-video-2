package com.lifeyoi.cards.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val LyDarkColors = darkColorScheme(
    primary = BrandOrange,
    onPrimary = BrandBlack,
    primaryContainer = BrandOrangeDark,
    onPrimaryContainer = OnDark,
    secondary = BrandOrangeLight,
    onSecondary = BrandBlack,
    tertiary = BrandOrangeLight,
    background = BrandBlack,
    onBackground = OnDark,
    surface = Surface1,
    onSurface = OnDark,
    surfaceVariant = Surface2,
    onSurfaceVariant = OnDarkMuted,
    outline = Surface3,
)

@Composable
fun LifeYoiCardsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LyDarkColors,
        typography = LyTypography,
        content = content
    )
}
