package io.github.iabanoubsamir.brandkit.model

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * The complete color palette that defines a brand's visual identity.
 *
 * Colors follow the Material Design 3 naming convention so they map directly
 * onto a [ColorScheme] without any guesswork. Define separate [BrandColors]
 * instances for light and dark if you need distinct palettes; pass [isDark]
 * to [toColorScheme] to select the right one at composition time.
 *
 * @property primary The brand's dominant color — buttons, FABs, highlighted elements.
 * @property onPrimary Content (text/icons) drawn on top of [primary] surfaces.
 * @property primaryContainer A tonal container derived from [primary] for lower-emphasis surfaces.
 * @property onPrimaryContainer Content drawn on top of [primaryContainer].
 * @property secondary A complementary accent color for less prominent UI elements.
 * @property onSecondary Content drawn on top of [secondary] surfaces.
 * @property secondaryContainer A tonal container derived from [secondary].
 * @property onSecondaryContainer Content drawn on top of [secondaryContainer].
 * @property background The default screen background.
 * @property onBackground Content drawn on top of [background].
 * @property surface The color of cards, sheets, and menus.
 * @property onSurface Content drawn on top of [surface].
 * @property surfaceVariant A variant surface used for chips and text fields.
 * @property onSurfaceVariant Content drawn on top of [surfaceVariant].
 * @property error Color for error states and destructive actions.
 * @property onError Content drawn on top of [error] surfaces.
 */
data class BrandColors(
    val primary: Color,
    val onPrimary: Color = Color.White,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color = Color.White,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val background: Color = Color(0xFFFFFBFE),
    val onBackground: Color = Color(0xFF1C1B1F),
    val surface: Color = Color(0xFFFFFBFE),
    val onSurface: Color = Color(0xFF1C1B1F),
    val surfaceVariant: Color = Color(0xFFE7E0EC),
    val onSurfaceVariant: Color = Color(0xFF49454F),
    val error: Color = Color(0xFFB3261E),
    val onError: Color = Color.White,
)

/**
 * Converts this [BrandColors] to a Material 3 [ColorScheme].
 *
 * @param isDark When `true` a dark color scheme is produced; otherwise a light scheme.
 */
fun BrandColors.toColorScheme(isDark: Boolean): ColorScheme =
    if (isDark) {
        darkColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            primaryContainer = primaryContainer,
            onPrimaryContainer = onPrimaryContainer,
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondaryContainer,
            onSecondaryContainer = onSecondaryContainer,
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = onSurfaceVariant,
            error = error,
            onError = onError,
        )
    } else {
        lightColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            primaryContainer = primaryContainer,
            onPrimaryContainer = onPrimaryContainer,
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondaryContainer,
            onSecondaryContainer = onSecondaryContainer,
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = onSurfaceVariant,
            error = error,
            onError = onError,
        )
    }
