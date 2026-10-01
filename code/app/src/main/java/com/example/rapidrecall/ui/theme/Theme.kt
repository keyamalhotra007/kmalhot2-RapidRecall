package com.example.rapidrecall.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// App palette
val DarkPink = Color(0xFFC2185B)
val LightPink = Color(0xFFFFE4EC)
val BackgroundPink = Color(0xFFFFC5D3)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPink,
    onPrimary = LightPink,
    background = BackgroundPink
)

private val LightColorScheme = lightColorScheme(
    primary = DarkPink,        // button background
    onPrimary = LightPink,     // button text
    background = BackgroundPink
)

val AppShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(20.dp)
)

@Composable
fun RapidrecallTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Off so the pink palette shows on every device, including the TAs'
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = AppShapes,
        content = content
    )
}