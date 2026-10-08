package com.example.sisreapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sisreapp.data.Equipo
import com.example.sisreapp.data.TipoDano
import kotlin.random.Random

/** Estado compartido entre pantallas durante un reporte. */
class ReportViewModel : ViewModel() {

    var equipo by mutableStateOf<Equipo?>(null)
        private set
    var dano by mutableStateOf<TipoDano?>(null)
        private set
    var ticket by mutableStateOf<String?>(null)
        private set

    fun seleccionarEquipo(nuevo: Equipo) {
        equipo = nuevo
        dano = null
    }

    fun seleccionarDano(nuevo: TipoDano) {
        dano = nuevo
    }

    fun enviarReporte() {
        // Aquí iría la llamada real al backend
        ticket = "TKT-2026-" + Random.nextInt(1000, 10000)
    }

    fun reiniciar() {
        equipo = null
        dano = null
        ticket = null
    }
}
