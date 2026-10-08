package com.example.sisreapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sisreapp.data.Catalogo
import com.example.sisreapp.data.Equipo
import com.example.sisreapp.ui.components.*
import com.example.sisreapp.ui.theme.*

@Composable
fun EquipmentFoundScreen(
    equipo: Equipo,
    onContinuar: () -> Unit,
    onNoEsEquipo: () -> Unit
) {
    ScreenContainer {
        AppHeader()
        EstadoVerificado("Equipo encontrado")
        Spacer(Modifier.height(8.dp))
        DatosEquipo(equipo)
        Spacer(Modifier.height(12.dp))
        Text(
            "Confirma que este es el equipo que vas a reportar.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextoSecundario
        )
        Spacer(Modifier.weight(1f))
        PrimaryButton("Continuar", onContinuar)
        LinkButton("No es este equipo", onNoEsEquipo)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
private fun EquipmentFoundPreview() {
    SISREAppTheme {
        EquipmentFoundScreen(equipo = Catalogo.equipoDemo, onContinuar = {}, onNoEsEquipo = {})
    }
}
