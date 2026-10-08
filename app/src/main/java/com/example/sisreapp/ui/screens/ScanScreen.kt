package com.example.sisreapp.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sisreapp.ui.components.*
import com.example.sisreapp.ui.theme.*

/**
 * Pantalla de escaneo.
 * DEMO: toca el recuadro = código leído; mantén presionado = error de lectura.
 * Para producción reemplaza el Box por CameraX + ML Kit Barcode Scanning.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScanScreen(
    onCodigoLeido: () -> Unit,
    onError: () -> Unit,
    onManual: () -> Unit
) {
    val transicion = rememberInfiniteTransition(label = "laser")
    val posicion by transicion.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(tween(1800, easing = LinearEasing), RepeatMode.Reverse),
        label = "posicionLaser"
    )

    ScreenContainer {
        AppHeader()
        Text(
            "Apunta al código QR del equipo",
            style = MaterialTheme.typography.labelMedium,
            color = Azul
        )
        Spacer(Modifier.height(12.dp))

        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(1.15f)
                .clip(RoundedCornerShape(16.dp))
                .background(EscanerFondo)
                .combinedClickable(
                    onClick = onCodigoLeido,
                    onClickLabel = "Simular lectura de código",
                    onLongClick = onError,
                    onLongClickLabel = "Simular error de lectura"
                )
        ) {
            Canvas(Modifier.fillMaxSize()) {
                val margen = 20.dp.toPx()
                val largo = 26.dp.toPx()
                val grosor = 3.dp.toPx()
                val w = size.width
                val h = size.height

                fun esquina(x: Float, y: Float, dx: Float, dy: Float) {
                    drawLine(EscanerAcento, Offset(x, y), Offset(x + dx * largo, y), grosor, StrokeCap.Round)
                    drawLine(EscanerAcento, Offset(x, y), Offset(x, y + dy * largo), grosor, StrokeCap.Round)
                }
                esquina(margen, margen, 1f, 1f)
                esquina(w - margen, margen, -1f, 1f)
                esquina(margen, h - margen, 1f, -1f)
                esquina(w - margen, h - margen, -1f, -1f)

                val yLaser = h * posicion
                drawLine(EscanerAcento, Offset(margen, yLaser), Offset(w - margen, yLaser), 2.dp.toPx())
            }
            Row(
                Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.size(8.dp).clip(CircleShape).background(EscanerAcento))
                Spacer(Modifier.width(8.dp))
                Text("Buscando código…", style = MaterialTheme.typography.labelMedium, color = EscanerAcento)
            }
        }

        Spacer(Modifier.height(12.dp))
        Text(
            "Demo: toca el recuadro para simular una lectura. Mantén presionado para simular un error.",
            style = MaterialTheme.typography.bodySmall,
            color = TextoSecundario
        )

        Spacer(Modifier.weight(1f))
        LinkButton("Ingresar aula manualmente", onManual)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
private fun ScanPreview() {
    SISREAppTheme { ScanScreen(onCodigoLeido = {}, onError = {}, onManual = {}) }
}
