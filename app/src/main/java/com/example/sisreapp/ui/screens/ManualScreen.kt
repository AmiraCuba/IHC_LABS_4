package com.example.sisreapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sisreapp.data.Catalogo
import com.example.sisreapp.data.Equipo
import com.example.sisreapp.ui.components.*
import com.example.sisreapp.ui.theme.*

@Composable
fun ManualScreen(onConfirmar: (Equipo) -> Unit, onVolver: () -> Unit) {
    var facultad by rememberSaveable { mutableStateOf<String?>(null) }
    var escuela by rememberSaveable { mutableStateOf<String?>(null) }
    var aula by rememberSaveable { mutableStateOf<String?>(null) }
    var codigo by rememberSaveable { mutableStateOf<String?>(null) }

    val opcionesEscuela = facultad?.let { Catalogo.escuelasDe(it) }.orEmpty()
    val opcionesAula = escuela?.let { Catalogo.aulasDe(it) }.orEmpty()
    val opcionesEquipo = aula?.let { Catalogo.equiposDe(it) }.orEmpty()

    val f = facultad
    val e = escuela
    val a = aula
    val c = codigo
    val equipoSeleccionado = if (f != null && e != null && a != null && c != null) Equipo(c, a, e, f) else null

    ScreenContainer {
        AppHeader(onVolver = onVolver)
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            ScreenTitle("Identifica el equipo", "Úsalo si el código está dañado o no se puede escanear.")
            Spacer(Modifier.height(20.dp))

            CampoSelector(
                etiqueta = "Facultad",
                opciones = Catalogo.facultades.keys.toList(),
                seleccion = facultad,
                onSeleccion = { facultad = it; escuela = null; aula = null; codigo = null }
            )
            Spacer(Modifier.height(12.dp))
            CampoSelector(
                etiqueta = "Escuela",
                opciones = opcionesEscuela,
                seleccion = escuela,
                onSeleccion = { escuela = it; aula = null; codigo = null },
                habilitado = facultad != null
            )
            Spacer(Modifier.height(12.dp))
            CampoSelector(
                etiqueta = "Aula",
                opciones = opcionesAula,
                seleccion = aula,
                onSeleccion = { aula = it; codigo = null },
                habilitado = opcionesAula.isNotEmpty(),
                ayuda = if (escuela != null && opcionesAula.isEmpty())
                    "Aún no hay aulas registradas para esta escuela" else null
            )
            Spacer(Modifier.height(12.dp))
            CampoSelector(
                etiqueta = "Equipo",
                opciones = opcionesEquipo,
                seleccion = codigo,
                onSeleccion = { codigo = it },
                habilitado = opcionesEquipo.isNotEmpty()
            )

            Spacer(Modifier.height(12.dp))
            Text(
                "Toca un campo para cambiar la selección.",
                style = MaterialTheme.typography.bodySmall,
                color = TextoSecundario
            )
        }

        PrimaryButton(
            texto = if (c != null) "Confirmar $c" else "Confirmar equipo",
            onClick = { equipoSeleccionado?.let(onConfirmar) },
            enabled = equipoSeleccionado != null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CampoSelector(
    etiqueta: String,
    opciones: List<String>,
    seleccion: String?,
    onSeleccion: (String) -> Unit,
    habilitado: Boolean = true,
    ayuda: String? = null
) {
    var expandido by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expandido && habilitado,
        onExpandedChange = { if (habilitado) expandido = it }
    ) {
        OutlinedTextField(
            value = seleccion.orEmpty(),
            onValueChange = {},
            readOnly = true,
            enabled = habilitado,
            label = { Text(etiqueta) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido && habilitado) },
            supportingText = if (ayuda != null) { { Text(ayuda) } } else null,
            textStyle = MaterialTheme.typography.bodyLarge,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Azul,
                unfocusedBorderColor = Borde,
                focusedLabelColor = Azul,
                cursorColor = Azul
            ),
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expandido && habilitado,
            onDismissRequest = { expandido = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion, style = MaterialTheme.typography.bodyLarge) },
                    onClick = { onSeleccion(opcion); expandido = false }
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
private fun ManualPreview() {
    SISREAppTheme { ManualScreen(onConfirmar = {}, onVolver = {}) }
}
