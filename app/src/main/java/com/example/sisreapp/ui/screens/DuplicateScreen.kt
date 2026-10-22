package com.example.sisreapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sisreapp.data.Catalogo
import com.example.sisreapp.data.ReporteActivo
import com.example.sisreapp.ui.components.*
import com.example.sisreapp.ui.theme.*

@Composable
fun DuplicateScreen(
    reporte: ReporteActivo,
    onCrearNuevo: () -> Unit,
    onVolver: () -> Unit
) {
    ScreenContainer {
        AppHeader()
        Column(
            Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            StatusIcon(Icons.Default.Warning, Ambar, AmbarClaro)
            Spacer(Modifier.height(16.dp))
            Text(
                "Ya existe un reporte para este equipo",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Alguien reportó este equipo hace poco. Revisa si es el mismo problema antes de crear uno nuevo.",
                style = MaterialTheme.typography.bodyMedium,
                color = TextoSecundario,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(20.dp))

            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Superficie)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("REPORTE ACTIVO", style = MaterialTheme.typography.labelSmall, color = TextoSecundario)
                    Text("#${reporte.ticket}", style = MaterialTheme.typography.labelSmall, color = Azul)
                }
                CampoLectura("Equipo", "${reporte.equipo.aula} · ${reporte.equipo.codigo}")
                CampoLectura("Daño reportado", reporte.dano.etiqueta)
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(8.dp).clip(CircleShape).background(Ambar))
                        Spacer(Modifier.width(6.dp))
                        Text(reporte.estado, style = MaterialTheme.typography.bodySmall, color = AmbarTexto)
                    }
                    Text(reporte.hace, style = MaterialTheme.typography.bodySmall, color = TextoSecundario)
                }
            }
        }

        PrimaryButton("No crear otro reporte", onClick = onVolver)
        LinkButton("Crear un reporte nuevo de todas formas", onCrearNuevo)
    }
}

@Composable
private fun CampoLectura(etiqueta: String, valor: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Text(etiqueta, style = MaterialTheme.typography.bodySmall, color = TextoSecundario)
        Text(valor, style = MaterialTheme.typography.titleMedium)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
private fun DuplicatePreview() {
    SISREAppTheme {
        DuplicateScreen(reporte = Catalogo.reportesActivos.first(), onCrearNuevo = {}, onVolver = {})
    }
}
