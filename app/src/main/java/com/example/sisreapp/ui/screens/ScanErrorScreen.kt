package com.example.sisreapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sisreapp.ui.components.*
import com.example.sisreapp.ui.theme.*

@Composable
fun ScanErrorScreen(onReintentar: () -> Unit, onManual: () -> Unit) {
    ScreenContainer {
        AppHeader(onVolver = onReintentar)
        Column(
            Modifier.weight(1f).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            StatusIcon(Icons.Default.Warning, Ambar, AmbarClaro)
            Spacer(Modifier.height(16.dp))
            Text(
                "No pudimos leer el código",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Puede estar dañado, sucio o con poca luz. Intenta de nuevo o ingresa el aula manualmente.",
                style = MaterialTheme.typography.bodyMedium,
                color = TextoSecundario,
                textAlign = TextAlign.Center
            )
        }
        PrimaryButton("Reintentar escaneo", onReintentar)
        LinkButton("Ingresar aula manualmente", onManual)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
private fun ScanErrorPreview() {
    SISREAppTheme { ScanErrorScreen(onReintentar = {}, onManual = {}) }
}
