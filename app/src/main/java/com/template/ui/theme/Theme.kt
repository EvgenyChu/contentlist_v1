package com.template.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun Contentlist_v1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = darkPrimaryColor,
            primaryVariant = primaryColor,
            secondary = accentColor,
            onPrimary = primaryText,
            onSecondary = textIcon,
            background = primaryColor,
            onBackground = primaryText,
            surface = dividerColor,
            secondaryVariant = secondaryText
        )
    } else {
        lightColors(
            primary = primaryColor,
            primaryVariant = lightPrimaryColor,
            secondary = accentColor,
            onPrimary = primaryText,
            onSecondary = textIcon,
            background = lightPrimaryColor,
            onBackground = primaryText,
            surface = dividerColor,
            secondaryVariant = secondaryText

        )
    }

    MaterialTheme(
        colors = colors,
        typography = MyTypography(
            colors.onPrimary,
            colors.onBackground,
            colors.onSecondary
        ),
        shapes = Shapes,
        content = content
    )
}