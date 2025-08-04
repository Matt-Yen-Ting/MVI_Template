package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.core.design_system.theme.SageBlue
import com.example.core.design_system.theme.Shapes
import com.example.core.design_system.theme.Teal200
import com.example.core.design_system.theme.TurquoiseBlue
import com.example.core.design_system.theme.Typography

private val DarkColorPalette = darkColors(
    primary = SageBlue,
    primaryVariant = TurquoiseBlue,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = SageBlue,
    primaryVariant = TurquoiseBlue,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MobileCAndroidRETheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Immutable
data class MainTopBarColors(
    val colorList: List<Color>,
)

val LocalMainTopBarColors = staticCompositionLocalOf {
    MainTopBarColors(
        listOf(Color.Unspecified, Color.Unspecified)
    )
}

@Composable
fun MainTopBarTheme(
    content: @Composable () -> Unit
) {
    val customColorList = MainTopBarColors(
        listOf(
            SageBlue,
            TurquoiseBlue,
        )
    )

    CompositionLocalProvider(LocalMainTopBarColors provides customColorList) {
        MaterialTheme(content = content)
    }
}

object MainTopBarColorsTheme {
    val colorList: MainTopBarColors
        @Composable
        get() = LocalMainTopBarColors.current
}
