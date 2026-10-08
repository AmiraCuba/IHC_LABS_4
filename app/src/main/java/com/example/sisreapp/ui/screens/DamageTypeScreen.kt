package com.example.sisreapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sisreapp.data.Catalogo
import com.example.sisreapp.data.Equipo
import com.example.sisreapp.data.TipoDano
import com.example.sisreapp.ui.components.*
import com.example.sisreapp.ui.theme.*

@Composable
fun DamageTypeScreen(
    equipo: Equipo,
    seleccionado: TipoDano?,
    onSeleccion: (TipoDano) -> Unit,
    onRevisar: () -> Unit
) {
    ScreenContainer {
        AppHeader()
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            // Resumen del equipo seleccionado
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Superficie)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.size(24.dp).clip(RoundedCornerShape(6.dp)).background(Azul))
                Spacer(Modifier.width(10.dp))
                Column {
                    Text(equipo.aula, style = MaterialTheme.typography.labelSmall, color = TextoSecundario)
                    Text(equipo.codigo, style = MaterialTheme.typography.titleMedium)
                }
            }

            Spacer(Modifier.height(16.dp))
            Text("¿Qué tipo de daño tiene?", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(10.dp))

            Column(Modifier.selectableGroup(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TipoDano.entries.forEach { opcion ->
                    OpcionDano(
                        dano = opcion,
                        seleccionada = opcion == seleccionado,
                        onClick = { onSeleccion(opcion) }
                    )
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        PrimaryButton("Revisar reporte", onRevisar, enabled = seleccionado != null)
    }
}

@Composable
private fun OpcionDano(dano: TipoDano, seleccionada: Boolean, onClick: () -> Unit) {
    val forma = RoundedCornerShape(12.dp)
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(forma)
            .background(if (seleccionada) AzulClaro else Color.White)
            .border(
                width = if (seleccionada) 1.5.dp else 1.dp,
                color = if (seleccionada) Azul else Borde,
                shape = forma
            )
            .selectable(selected = seleccionada, onClick = onClick, role = Role.RadioButton)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = seleccionada,
            onClick = null,
            colors = RadioButtonDefaults.colors(selectedColor = Azul, unselectedColor = TextoSecundario)
        )
        Spacer(Modifier.width(12.dp))
        Text(dano.etiqueta, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        if (seleccionada) {
            Icon(
                Icons.Default.Check,
                contentDescription = "Seleccionado",
                tint = Verde,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
private fun DamageTypePreview() {
    SISREAppTheme {
        DamageTypeScreen(
            equipo = Catalogo.equipoDemo,
            seleccionado = TipoDano.TECLADO,
            onSeleccion = {},
            onRevisar = {}
        )
    }
}
