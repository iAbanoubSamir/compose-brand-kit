package io.github.iabanoubsamir.brandkit.sample.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import io.github.iabanoubsamir.brandkit.sample.SampleBrand
import io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme

/**
 * Root composable for the compose-brand-kit sample app.
 *
 * Owns the dark-mode toggle state so the user can demonstrate
 * light/dark brand color switching in real time.
 */
@Composable
fun SampleApp() {
    var darkMode by rememberSaveable { mutableStateOf(false) }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkMode
                isAppearanceLightNavigationBars = !darkMode
            }
        }
    }

    BrandTheme(brandConfig = SampleBrand, darkTheme = darkMode) {
        DemoHomeScreen(
            darkMode = darkMode,
            onToggleDarkMode = { darkMode = !darkMode },
        )
    }
}
