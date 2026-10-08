package com.example.sisreapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val EsquemaClaro = lightColorScheme(
    primary = Azul,
    onPrimary = Color.White,
    secondary = Ginda,
    onSecondary = Color.White,
    background = Color.White,
    onBackground = TextoPrincipal,
    surface = Color.White,
    onSurface = TextoPrincipal,
    surfaceVariant = Superficie,
    onSurfaceVariant = TextoSecundario,
    outline = Borde,
    error = Ginda,
    onError = Color.White
)

// Mismo nombre que genera la plantilla de Android Studio (SISREAppTheme)
@Composable
fun SISREAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EsquemaClaro,
        typography = AppTypography,
        content = content
    )
}
