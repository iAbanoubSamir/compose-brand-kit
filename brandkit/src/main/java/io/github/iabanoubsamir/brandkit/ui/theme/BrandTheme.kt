package io.github.iabanoubsamir.brandkit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import io.github.iabanoubsamir.brandkit.core.BrandConfig
import io.github.iabanoubsamir.brandkit.core.LocalBrandConfig
import io.github.iabanoubsamir.brandkit.model.toColorScheme
import io.github.iabanoubsamir.brandkit.model.toMaterial3Typography

/**
 * The root theming composable for compose-brand-kit.
 *
 * `BrandTheme` makes all brand-aware composables available in the Compose tree by
 * installing [brandConfig] into [LocalBrandConfig]. It also optionally overrides the
 * active [MaterialTheme] with brand colors and typography.
 *
 * ### Default behaviour — uses brand colors
 * ```kotlin
 * BrandTheme(myBrand) {
 *     // MaterialTheme.colorScheme now reflects brand colors
 *     // All brand composables (BrandLogo, BrandFooter, etc.) work here
 * }
 * ```
 *
 * ### Respecting the host app's existing MaterialTheme
 * If your app already configures its own `MaterialTheme` (e.g. via a design-system),
 * set `applyColorScheme = false` and/or `applyTypography = false` so the library
 * doesn't override your theme:
 * ```kotlin
 * // Wrap with your own theme first
 * MyAppTheme {
 *     // Then add brand context without touching your colors or fonts
 *     BrandTheme(myBrand, applyColorScheme = false, applyTypography = false) {
 *         BrandFooter()   // reads brand info, but renders in your app's colors
 *     }
 * }
 * ```
 *
 * @param brandConfig The brand configuration produced by the [io.github.iabanoubsamir.brandkit.brandKit] DSL.
 * @param darkTheme Whether to apply dark colors. Defaults to the system setting.
 * @param applyColorScheme When `true` (default), overrides [MaterialTheme.colorScheme] with brand
 *   colors. Set to `false` to keep the host app's existing color scheme.
 * @param applyTypography When `true` (default), overrides [MaterialTheme.typography] with brand
 *   fonts. Set to `false` to keep the host app's existing typography.
 * @param content The Compose content tree that will have access to brand theming.
 */
@Composable
fun BrandTheme(
    brandConfig: BrandConfig,
    darkTheme: Boolean = isSystemInDarkTheme(),
    applyColorScheme: Boolean = true,
    applyTypography: Boolean = true,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalBrandConfig provides brandConfig) {
        if (!applyColorScheme && !applyTypography) {
            // Just provide brand context — leave MaterialTheme completely untouched.
            content()
        } else {
            val colorScheme = if (applyColorScheme) {
                brandConfig.resolveColors(isDark = darkTheme).toColorScheme(isDark = darkTheme)
            } else {
                MaterialTheme.colorScheme
            }
            val typography = if (applyTypography) {
                brandConfig.typography.toMaterial3Typography()
            } else {
                MaterialTheme.typography
            }
            MaterialTheme(
                colorScheme = colorScheme,
                typography = typography,
                content = content,
            )
        }
    }
}
