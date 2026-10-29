package com.example.sisreapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sisreapp.R
import com.example.sisreapp.data.Equipo
import com.example.sisreapp.data.TipoDano
import com.example.sisreapp.ui.theme.*

/** Contenedor base de todas las pantallas: fondo blanco, márgenes, teclado y snackbar. */
@Composable
fun ScreenContainer(
    snackbarHostState: SnackbarHostState? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        snackbarHost = { if (snackbarHostState != null) SnackbarHost(snackbarHostState) }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .consumeWindowInsets(inner)
                .imePadding()
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 20.dp),
            content = content
        )
    }
}

/**
 * Logo + nombre de la app (encabezado de cada pantalla).
 * Si [onVolver] no es nulo se muestra una flecha para regresar al paso anterior.
 */
@Composable
fun AppHeader(onVolver: (() -> Unit)? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        if (onVolver != null) {
            IconButton(
                onClick = onVolver,
                // Compensa el relleno interno del IconButton para alinearlo con el margen
                modifier = Modifier.offset(x = (-12).dp)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver al paso anterior",
                    tint = Azul
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.logo_sisreapp),
            contentDescription = "Logo SISREApp",
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(Modifier.width(10.dp))
        Text("Reportar equipo", style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun ScreenTitle(titulo: String, subtitulo: String? = null) {
    Column {
        Text(titulo, style = MaterialTheme.typography.headlineSmall)
        if (subtitulo != null) {
            Spacer(Modifier.height(6.dp))
            Text(
                subtitulo,
                style = MaterialTheme.typography.bodyMedium,
                color = TextoSecundario
            )
        }
    }
}

@Composable
fun PrimaryButton(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Azul,
            contentColor = Color.White,
            disabledContainerColor = Azul.copy(alpha = 0.35f),
            disabledContentColor = Color.White
        )
    ) {
        Text(texto, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun LinkButton(texto: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    TextButton(onClick = onClick, modifier = modifier.fillMaxWidth()) {
        Text(texto, style = MaterialTheme.typography.labelMedium, color = Azul)
    }
}

/** Cuadro con ícono para estados (éxito, advertencia). */
@Composable
fun StatusIcon(icono: ImageVector, tinte: Color, fondo: Color) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(fondo),
        contentAlignment = Alignment.Center
    ) {
        Icon(icono, contentDescription = null, tint = tinte, modifier = Modifier.size(28.dp))
    }
}

/** Filas Facultad / Escuela / Aula / Equipo (+ Tipo de daño opcional) con divisores. */
@Composable
fun DatosEquipo(equipo: Equipo, dano: TipoDano? = null) {
    val filas = buildList {
        add("FACULTAD" to equipo.facultad)
        add("ESCUELA" to equipo.escuela)
        add("AULA" to equipo.aula)
        add("EQUIPO" to equipo.codigo)
        if (dano != null) add("TIPO DE DAÑO" to dano.etiqueta)
    }
    Column {
        filas.forEachIndexed { indice, (etiqueta, valor) ->
            if (indice > 0) HorizontalDivider(color = Borde)
            Column(Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
                Text(etiqueta, style = MaterialTheme.typography.labelSmall, color = TextoSecundario)
                Spacer(Modifier.height(2.dp))
                Text(valor, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

/** Línea "✓ Equipo encontrado" en verde (ícono + texto, no solo color). */
@Composable
fun EstadoVerificado(texto: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.Check, contentDescription = null, tint = Verde, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(6.dp))
        Text(texto, style = MaterialTheme.typography.labelMedium, color = Verde)
    }
}
