package io.github.iabanoubsamir.brandkit.ui.copyright

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.iabanoubsamir.brandkit.core.LocalBrandConfig
import io.github.iabanoubsamir.brandkit.model.resolve
import io.github.iabanoubsamir.brandkit.ui.BrandContainer
import io.github.iabanoubsamir.brandkit.ui.BrandContainerStyle
import io.github.iabanoubsamir.brandkit.ui.preview.PreviewBrandThemeWrapper
import java.util.Calendar

/**
 * Displays a standard copyright line, e.g. "© 2008–2026 GitHub, Inc.".
 *
 * The year range is computed automatically: the start year comes from
 * [io.github.iabanoubsamir.brandkit.model.BrandInfo.copyrightYear] and the end year
 * is the current calendar year. When both years are equal only a single year is shown.
 *
 * This composable must be called inside a
 * [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] wrapper.
 *
 * ### Standalone text
 * ```kotlin
 * CopyrightBanner()
 * ```
 *
 * ### Inside a surface card
 * ```kotlin
 * CopyrightBanner(containerStyle = BrandContainerStyle.Surface)
 * ```
 *
 * @param modifier Modifier applied to the outermost container.
 * @param centered When `true` (default), the text is center-aligned. Set to `false` for start alignment.
 * @param style The [TextStyle] used for the copyright text.
 *   Defaults to [MaterialTheme.typography.bodySmall].
 * @param containerStyle Optional wrapping container. Defaults to [BrandContainerStyle.None].
 */
@Composable
fun CopyrightBanner(
    modifier: Modifier = Modifier,
    centered: Boolean = true,
    style: TextStyle = MaterialTheme.typography.bodySmall,
    containerStyle: BrandContainerStyle = BrandContainerStyle.None,
) {
    val brand = LocalBrandConfig.current
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val startYear = brand.info.copyrightYear

    val yearRange = if (currentYear > startYear) "$startYear\u2013$currentYear" else "$startYear"
    val text = "\u00A9 $yearRange ${brand.info.copyrightHolder.resolve()}"
    val textAlign = if (centered) TextAlign.Center else TextAlign.Start

    BrandContainer(style = containerStyle, modifier = modifier) {
        Text(
            text = text,
            style = style,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = textAlign,
            modifier = if (containerStyle != BrandContainerStyle.None) Modifier.padding(12.dp) else Modifier,
        )
    }
}

@Preview(showBackground = true, name = "CopyrightBanner — styles")
@Composable
private fun CopyrightBannerPreview() {
    PreviewBrandThemeWrapper {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CopyrightBanner()
            CopyrightBanner(containerStyle = BrandContainerStyle.Surface)
            CopyrightBanner(containerStyle = BrandContainerStyle.Outlined)
        }
    }
}
