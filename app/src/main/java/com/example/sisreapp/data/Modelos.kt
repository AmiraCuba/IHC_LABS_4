package com.example.sisreapp.data

data class Equipo(
    val codigo: String,
    val aula: String,
    val escuela: String,
    val facultad: String
)

enum class TipoDano(val etiqueta: String) {
    NO_ENCIENDE("No enciende"),
    MONITOR("Monitor"),
    TECLADO("Teclado"),
    MOUSE("Mouse"),
    SIN_INTERNET("Sin internet"),
    OTRO("Otro")
}

data class ReporteActivo(
    val ticket: String,
    val equipo: Equipo,
    val dano: TipoDano,
    val estado: String,
    val hace: String
)
