package com.example.sisreapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sisreapp.ui.components.*
import com.example.sisreapp.ui.theme.*
import kotlinx.coroutines.launch

private val REGEX_UNSA = Regex("^[A-Za-z0-9._%+-]+@unsa\\.edu\\.pe$", RegexOption.IGNORE_CASE)

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    // Entradas del formulario (3): correo, contraseña y "mantener sesión"
    var correo by rememberSaveable { mutableStateOf("") }
    var contrasena by rememberSaveable { mutableStateOf("") }
    var mostrarContrasena by rememberSaveable { mutableStateOf(false) }
    var mantenerSesion by rememberSaveable { mutableStateOf(false) }
    var intentoEnvio by rememberSaveable { mutableStateOf(false) }

    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val correoValido = REGEX_UNSA.matches(correo.trim())
    val errorCorreo = when {
        !intentoEnvio || correoValido -> null
        correo.isBlank() -> "Ingresa tu correo institucional"
        else -> "Usa tu correo institucional @unsa.edu.pe"
    }
    val errorContrasena = if (intentoEnvio && contrasena.isBlank()) "Ingresa tu contraseña" else null

    fun iniciarSesion() {
        intentoEnvio = true
        if (correoValido && contrasena.isNotBlank()) {
            onLoginSuccess()
        } else {
            // Retroalimentación (Fase 7)
            scope.launch { snackbar.showSnackbar("Revisa los datos ingresados") }
        }
    }

    ScreenContainer(snackbar) {
        AppHeader()

        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            ScreenTitle("Inicia sesión", "Ingresa con tu correo institucional para reportar un equipo.")
            Spacer(Modifier.height(20.dp))

            CampoTexto(
                etiqueta = "Correo institucional",
                valor = correo,
                onCambio = { correo = it },
                placeholder = "nombre@unsa.edu.pe",
                error = errorCorreo,
                teclado = KeyboardType.Email,
                accion = ImeAction.Next
            )
            Spacer(Modifier.height(16.dp))
            CampoTexto(
                etiqueta = "Contraseña",
                valor = contrasena,
                onCambio = { contrasena = it },
                placeholder = "Tu contraseña",
                error = errorContrasena,
                teclado = KeyboardType.Password,
                accion = ImeAction.Done,
                onAccion = { iniciarSesion() },
                esContrasena = true,
                mostrar = mostrarContrasena,
                onMostrar = { mostrarContrasena = !mostrarContrasena }
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
                    .toggleable(
                        value = mantenerSesion,
                        onValueChange = { mantenerSesion = it },
                        role = Role.Checkbox
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = mantenerSesion,
                    onCheckedChange = null,
                    colors = CheckboxDefaults.colors(checkedColor = Azul)
                )
                Spacer(Modifier.width(8.dp))
                Text("Mantener sesión iniciada", style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(Modifier.height(8.dp))
            Text(
                "Solo puedes ingresar con una cuenta @unsa.edu.pe.",
                style = MaterialTheme.typography.bodySmall,
                color = TextoSecundario
            )
        }

        PrimaryButton("Iniciar sesión", onClick = { iniciarSesion() })
    }
}

@Composable
private fun CampoTexto(
    etiqueta: String,
    valor: String,
    onCambio: (String) -> Unit,
    placeholder: String,
    error: String?,
    teclado: KeyboardType,
    accion: ImeAction,
    onAccion: () -> Unit = {},
    esContrasena: Boolean = false,
    mostrar: Boolean = false,
    onMostrar: () -> Unit = {}
) {
    Column {
        Text(
            etiqueta,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        OutlinedTextField(
            value = valor,
            onValueChange = onCambio,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder, color = TextoSecundario) },
            singleLine = true,
            isError = error != null,
            shape = RoundedCornerShape(12.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            keyboardOptions = KeyboardOptions(keyboardType = teclado, imeAction = accion),
            keyboardActions = KeyboardActions(onDone = { onAccion() }),
            visualTransformation = if (esContrasena && !mostrar) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = if (esContrasena) {
                {
                    TextButton(onClick = onMostrar) {
                        Text(
                            if (mostrar) "Ocultar" else "Mostrar",
                            style = MaterialTheme.typography.labelMedium,
                            color = Azul
                        )
                    }
                }
            } else null,
            supportingText = if (error != null) { { MensajeError(error) } } else null,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Azul,
                unfocusedBorderColor = Borde,
                errorBorderColor = Ginda,
                cursorColor = Azul
            )
        )
    }
}

/** El error se comunica con ícono + texto (no solo con color). */
@Composable
private fun MensajeError(texto: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.Warning, contentDescription = null, tint = Ginda, modifier = Modifier.size(14.dp))
        Spacer(Modifier.width(4.dp))
        Text(texto, color = Ginda)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
private fun LoginPreview() {
    SISREAppTheme { LoginScreen(onLoginSuccess = {}) }
}
