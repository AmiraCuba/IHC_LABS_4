package com.example.sisreapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sisreapp.data.Catalogo
import com.example.sisreapp.data.Equipo
import com.example.sisreapp.data.TipoDano
import com.example.sisreapp.ui.components.*
import com.example.sisreapp.ui.theme.SISREAppTheme

@Composable
fun ReviewScreen(
    equipo: Equipo,
    dano: TipoDano,
    onEnviar: () -> Unit,
    onCorregir: () -> Unit
) {
    ScreenContainer {
        AppHeader()
        ScreenTitle("Revisa tu reporte", "Confirma los datos antes de enviar.")
        Spacer(Modifier.height(12.dp))
        DatosEquipo(equipo, dano)
        Spacer(Modifier.weight(1f))
        PrimaryButton("Enviar reporte", onEnviar)
        LinkButton("Corregir información", onCorregir)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
private fun ReviewPreview() {
    SISREAppTheme {
        ReviewScreen(Catalogo.equipoDemo, TipoDano.TECLADO, onEnviar = {}, onCorregir = {})
    }
}
