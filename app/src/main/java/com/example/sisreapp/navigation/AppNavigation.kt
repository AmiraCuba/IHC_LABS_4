package com.example.sisreapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sisreapp.data.Catalogo
import com.example.sisreapp.data.Equipo
import com.example.sisreapp.data.TipoDano
import com.example.sisreapp.ui.screens.*
import com.example.sisreapp.viewmodel.ReportViewModel

object Rutas {
    const val LOGIN = "login"
    const val SCAN = "scan"
    const val ERROR = "error_lectura"
    const val ENCONTRADO = "equipo_encontrado"
    const val MANUAL = "manual"
    const val DUPLICADO = "duplicado"
    const val DANO = "tipo_dano"
    const val REVISION = "revision"
    const val EXITO = "exito"
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    vm: ReportViewModel = viewModel()
) {
    // Tras identificar el equipo: si ya tiene un reporte activo → pantalla de duplicado
    fun continuarConEquipo(equipo: Equipo) {
        vm.seleccionarEquipo(equipo)
        val destino = if (Catalogo.reporteActivoDe(equipo) != null) Rutas.DUPLICADO else Rutas.DANO
        navController.navigate(destino)
    }

    NavHost(navController = navController, startDestination = Rutas.LOGIN) {

        composable(Rutas.LOGIN) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Rutas.SCAN) { popUpTo(Rutas.LOGIN) { inclusive = true } }
            })
        }

        composable(Rutas.SCAN) {
            ScanScreen(
                onCodigoLeido = {
                    vm.seleccionarEquipo(Catalogo.equipoDemo)
                    navController.navigate(Rutas.ENCONTRADO)
                },
                onError = { navController.navigate(Rutas.ERROR) },
                onManual = { navController.navigate(Rutas.MANUAL) }
            )
        }

        composable(Rutas.ERROR) {
            ScanErrorScreen(
                onReintentar = { navController.popBackStack(Rutas.SCAN, inclusive = false) },
                onManual = { navController.navigate(Rutas.MANUAL) }
            )
        }

        composable(Rutas.ENCONTRADO) {
            EquipmentFoundScreen(
                equipo = vm.equipo ?: Catalogo.equipoDemo,
                onContinuar = { continuarConEquipo(vm.equipo ?: Catalogo.equipoDemo) },
                onNoEsEquipo = { navController.navigate(Rutas.MANUAL) },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Rutas.MANUAL) {
            ManualScreen(
                onConfirmar = { continuarConEquipo(it) },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Rutas.DUPLICADO) {
            val reporte = vm.equipo?.let { Catalogo.reporteActivoDe(it) }
                ?: Catalogo.reportesActivos.first()
            DuplicateScreen(
                reporte = reporte,
                onCrearNuevo = { navController.navigate(Rutas.DANO) },
                onVolver = { navController.popBackStack(Rutas.SCAN, inclusive = false) },
                onAtras = { navController.popBackStack() }
            )
        }

        composable(Rutas.DANO) {
            DamageTypeScreen(
                equipo = vm.equipo ?: Catalogo.equipoDemo,
                seleccionado = vm.dano,
                onSeleccion = vm::seleccionarDano,
                onRevisar = { navController.navigate(Rutas.REVISION) },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Rutas.REVISION) {
            ReviewScreen(
                equipo = vm.equipo ?: Catalogo.equipoDemo,
                dano = vm.dano ?: TipoDano.OTRO,
                onEnviar = {
                    vm.enviarReporte()
                    navController.navigate(Rutas.EXITO) { popUpTo(Rutas.SCAN) }
                },
                onCorregir = { navController.popBackStack() }
            )
        }

        composable(Rutas.EXITO) {
            SuccessScreen(
                ticket = vm.ticket ?: "TKT-2026-4831",
                equipo = vm.equipo ?: Catalogo.equipoDemo,
                dano = vm.dano ?: TipoDano.OTRO,
                onListo = {
                    navController.popBackStack(Rutas.SCAN, inclusive = false)
                    vm.reiniciar()
                }
            )
        }
    }
}
