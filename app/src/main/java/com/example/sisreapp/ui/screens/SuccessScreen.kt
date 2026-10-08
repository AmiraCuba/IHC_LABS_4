package com.example.sisreapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sisreapp.data.Catalogo
import com.example.sisreapp.data.Equipo
import com.example.sisreapp.data.TipoDano
import com.example.sisreapp.ui.components.*
import com.example.sisreapp.ui.theme.*

@Composable
fun SuccessScreen(
    ticket: String,
    equipo: Equipo,
    dano: TipoDano,
    onListo: () -> Unit
) {
    val snackbar = remember { SnackbarHostState() }
    // Retroalimentación ante la acción "Enviar reporte" (Fase 7)
    LaunchedEffect(Unit) { snackbar.showSnackbar("Reporte enviado correctamente") }

    ScreenContainer(snackbar) {
        AppHeader()
        Column(
            Modifier.weight(1f).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            StatusIcon(Icons.Default.Check, Verde, VerdeClaro)
            Spacer(Modifier.height(16.dp))
            Text("Reporte recibido", style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)
            Spacer(Modifier.height(12.dp))

            Box(
                Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Superficie)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(ticket, style = MaterialTheme.typography.labelMedium)
            }
            Spacer(Modifier.height(8.dp))
            Text(
                "${equipo.codigo} · ${dano.etiqueta}",
                style = MaterialTheme.typography.bodyMedium,
                color = TextoSecundario
            )
            Text(equipo.aula, style = MaterialTheme.typography.bodyMedium, color = TextoSecundario)

            Spacer(Modifier.height(20.dp))
            val forma = RoundedCornerShape(12.dp)
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(forma)
                    .background(VerdeClaro)
                    .border(1.dp, VerdeBorde, forma)
                    .padding(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                Icon(Icons.Default.Check, contentDescription = null, tint = Verde, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(8.dp))
                Text(
                    "Soporte técnico fue notificado y atenderá el problema a la brevedad.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Verde
                )
            }
        }
        PrimaryButton("Listo", onListo)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
private fun SuccessPreview() {
    SISREAppTheme {
        SuccessScreen("TKT-2026-4831", Catalogo.equipoDemo, TipoDano.TECLADO, onListo = {})
    }
}
