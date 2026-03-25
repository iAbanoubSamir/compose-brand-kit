package io.github.iabanoubsamir.brandkit.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.github.iabanoubsamir.brandkit.brandKit
import io.github.iabanoubsamir.brandkit.model.SocialPlatform
import io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme

/**
 * A lightweight [BrandTheme] wrapper pre-configured with a sample brand,
 * intended exclusively for `@Preview` composables within the library module.
 */
@Composable
internal fun PreviewBrandThemeWrapper(content: @Composable () -> Unit) {
    val previewBrand = brandKit {
        info {
            name = "Acme Corp"
            tagline = "We make everything"
            website = "https://acme.example.com"
            copyrightYear = 2021
            versionName = "2.1.0"
            versionCode = 42
        }
        colors {
            primary = Color(0xFF6650A4)
            onPrimary = Color.White
            primaryContainer = Color(0xFFEADDFF)
            onPrimaryContainer = Color(0xFF21005D)
            secondary = Color(0xFF625B71)
            onSecondary = Color.White
            secondaryContainer = Color(0xFFE8DEF8)
            onSecondaryContainer = Color(0xFF1D192B)
        }
        darkColors {
            primary = Color(0xFFD0BCFF)
            onPrimary = Color(0xFF381E72)
            primaryContainer = Color(0xFF4F378B)
            onPrimaryContainer = Color(0xFFEADDFF)
            secondary = Color(0xFFCCC2DC)
            onSecondary = Color(0xFF332D41)
            background = Color(0xFF1C1B1F)
            onBackground = Color(0xFFE6E1E5)
            surface = Color(0xFF1C1B1F)
            onSurface = Color(0xFFE6E1E5)
            surfaceVariant = Color(0xFF49454F)
            onSurfaceVariant = Color(0xFFCAC4D0)
        }
        socials {
            link(SocialPlatform.GITHUB, "https://github.com/acme")
            link(SocialPlatform.LINKEDIN, "https://linkedin.com/company/acme")
            link(SocialPlatform.TWITTER, "https://twitter.com/acme")
        }
    }
    BrandTheme(previewBrand, content = content)
}
