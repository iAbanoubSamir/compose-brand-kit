package io.github.iabanoubsamir.brandkit.ui.palette

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.iabanoubsamir.brandkit.core.LocalBrandConfig
import io.github.iabanoubsamir.brandkit.model.BrandColors
import io.github.iabanoubsamir.brandkit.ui.preview.PreviewBrandThemeWrapper

/**
 * Displays the brand's active color palette as a grid of labeled swatches.
 *
 * Each swatch shows the color fill plus its role name. The label text adapts between
 * white and black automatically based on the swatch's luminance, ensuring legibility.
 *
 * Useful for brand style-guide screens or design-system documentation within your app.
 *
 * This composable must be called inside a
 * [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] wrapper.
 *
 * ### Usage
 * ```kotlin
 * BrandColorPalette(modifier = Modifier.fillMaxWidth())
 * ```
 *
 * @param modifier Modifier applied to the outer [Column].
 * @param columns Number of swatches per row. Defaults to `3`.
 */
@Composable
fun BrandColorPalette(
    modifier: Modifier = Modifier,
    columns: Int = 3,
) {
    val brand = LocalBrandConfig.current
    val colors = brand.colors

    val swatches = buildSwatchList(colors)
    val rows = swatches.chunked(columns)

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        rows.forEach { rowSwatches ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                rowSwatches.forEach { (label, color) ->
                    ColorSwatch(
                        label = label,
                        color = color,
                        modifier = Modifier.weight(1f),
                    )
                }
                // Pad the last row if it's shorter than [columns]
                repeat(columns - rowSwatches.size) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun ColorSwatch(
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    val labelColor = if (color.luminance() > 0.4f) Color.Black else Color.White
    val borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.4f)
                .clip(RoundedCornerShape(8.dp))
                .background(color)
                .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.BottomStart,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = labelColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(6.dp),
            )
        }
    }
}

private fun buildSwatchList(colors: BrandColors): List<Pair<String, Color>> = listOf(
    "Primary" to colors.primary,
    "On Primary" to colors.onPrimary,
    "Primary Container" to colors.primaryContainer,
    "On Primary Container" to colors.onPrimaryContainer,
    "Secondary" to colors.secondary,
    "On Secondary" to colors.onSecondary,
    "Secondary Container" to colors.secondaryContainer,
    "On Secondary Container" to colors.onSecondaryContainer,
    "Background" to colors.background,
    "On Background" to colors.onBackground,
    "Surface" to colors.surface,
    "On Surface" to colors.onSurface,
    "Surface Variant" to colors.surfaceVariant,
    "On Surface Variant" to colors.onSurfaceVariant,
    "Error" to colors.error,
    "On Error" to colors.onError,
)

@Preview(showBackground = true, name = "BrandColorPalette")
@Composable
private fun BrandColorPalettePreview() {
    PreviewBrandThemeWrapper {
        BrandColorPalette(modifier = Modifier.padding(16.dp))
    }
}
