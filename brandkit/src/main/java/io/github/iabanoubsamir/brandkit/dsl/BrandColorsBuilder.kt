package io.github.iabanoubsamir.brandkit.dsl

import androidx.compose.ui.graphics.Color
import io.github.iabanoubsamir.brandkit.model.BrandColors

/**
 * Builder for [BrandColors].
 *
 * All fields are nullable internally so that the `darkColors { }` block can express
 * "inherit this value from light mode" simply by leaving a field unset.
 *
 * Use inside the `colors { }` or `darkColors { }` block of [BrandConfigBuilder].
 *
 * ### Light mode (required primary + secondary)
 * ```kotlin
 * colors {
 *     primary = Color(0xFF6200EE)
 *     secondary = Color(0xFF03DAC6)
 * }
 * ```
 *
 * ### Dark overrides (only set what differs)
 * ```kotlin
 * darkColors {
 *     primary = Color(0xFFBB86FC)
 *     background = Color(0xFF121212)
 *     surface = Color(0xFF1E1E1E)
 *     onBackground = Color.White
 *     onSurface = Color.White
 * }
 * ```
 */
@BrandKitDsl
class BrandColorsBuilder {

    /** The brand's dominant color. Required for light mode. */
    var primary: Color? = null

    /** Content drawn on top of [primary] surfaces. */
    var onPrimary: Color? = null

    /** A tonal container derived from [primary]. Auto-derived when `null`. */
    var primaryContainer: Color? = null

    /** Content drawn on top of [primaryContainer]. Auto-derived when `null`. */
    var onPrimaryContainer: Color? = null

    /** A complementary accent color. Required for light mode. */
    var secondary: Color? = null

    /** Content drawn on top of [secondary] surfaces. */
    var onSecondary: Color? = null

    /** A tonal container derived from [secondary]. Auto-derived when `null`. */
    var secondaryContainer: Color? = null

    /** Content drawn on top of [secondaryContainer]. Auto-derived when `null`. */
    var onSecondaryContainer: Color? = null

    /** Default screen background. */
    var background: Color? = null

    /** Content drawn on top of [background]. */
    var onBackground: Color? = null

    /** Color of cards, sheets, and menus. */
    var surface: Color? = null

    /** Content drawn on top of [surface]. */
    var onSurface: Color? = null

    /** A variant surface used for chips and text fields. */
    var surfaceVariant: Color? = null

    /** Content drawn on top of [surfaceVariant]. */
    var onSurfaceVariant: Color? = null

    /** Color for error states. */
    var error: Color? = null

    /** Content drawn on top of [error] surfaces. */
    var onError: Color? = null

    /**
     * Builds [BrandColors] for light mode, applying defaults for unset fields.
     *
     * Requires [primary] and [secondary] to be set.
     */
    internal fun build(): BrandColors {
        val resolvedPrimary = checkNotNull(primary) {
            "BrandColors requires a `primary` color. Add `primary = Color(0xFF…)` inside the `colors { }` block."
        }
        val resolvedSecondary = checkNotNull(secondary) {
            "BrandColors requires a `secondary` color. Add `secondary = Color(0xFF…)` inside the `colors { }` block."
        }
        return BrandColors(
            primary = resolvedPrimary,
            onPrimary = onPrimary ?: Color.White,
            primaryContainer = primaryContainer ?: resolvedPrimary.copy(alpha = 0.2f),
            onPrimaryContainer = onPrimaryContainer ?: resolvedPrimary,
            secondary = resolvedSecondary,
            onSecondary = onSecondary ?: Color.White,
            secondaryContainer = secondaryContainer ?: resolvedSecondary.copy(alpha = 0.2f),
            onSecondaryContainer = onSecondaryContainer ?: resolvedSecondary,
            background = background ?: Color(0xFFFFFBFE),
            onBackground = onBackground ?: Color(0xFF1C1B1F),
            surface = surface ?: Color(0xFFFFFBFE),
            onSurface = onSurface ?: Color(0xFF1C1B1F),
            surfaceVariant = surfaceVariant ?: Color(0xFFE7E0EC),
            onSurfaceVariant = onSurfaceVariant ?: Color(0xFF49454F),
            error = error ?: Color(0xFFB3261E),
            onError = onError ?: Color.White,
        )
    }

    /**
     * Builds [BrandColors] for dark mode, inheriting any unset field from [fallback].
     *
     * This allows `darkColors { }` blocks to only specify the colors that actually
     * differ from light mode, rather than re-specifying the entire palette.
     */
    internal fun buildWithFallback(fallback: BrandColors): BrandColors = BrandColors(
        primary = primary ?: fallback.primary,
        onPrimary = onPrimary ?: fallback.onPrimary,
        primaryContainer = primaryContainer ?: fallback.primaryContainer,
        onPrimaryContainer = onPrimaryContainer ?: fallback.onPrimaryContainer,
        secondary = secondary ?: fallback.secondary,
        onSecondary = onSecondary ?: fallback.onSecondary,
        secondaryContainer = secondaryContainer ?: fallback.secondaryContainer,
        onSecondaryContainer = onSecondaryContainer ?: fallback.onSecondaryContainer,
        background = background ?: fallback.background,
        onBackground = onBackground ?: fallback.onBackground,
        surface = surface ?: fallback.surface,
        onSurface = onSurface ?: fallback.onSurface,
        surfaceVariant = surfaceVariant ?: fallback.surfaceVariant,
        onSurfaceVariant = onSurfaceVariant ?: fallback.onSurfaceVariant,
        error = error ?: fallback.error,
        onError = onError ?: fallback.onError,
    )
}
