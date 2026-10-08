package com.example.sisreapp.data

/**
 * Datos de ejemplo. Facultades y escuelas salen de la "Oferta académica UNSA" (PDF).
 * Los nombres que aparecían cortados en el PDF están marcados con TODO para completarlos.
 */
object Catalogo {

    val facultades: Map<String, List<String>> = mapOf(
        "Arquitectura" to listOf("Arquitectura"),
        "Ciencias Biológicas" to listOf("Biología", "Ciencias de la Nutrición", "Ing. Pesquera"),
        "Medicina" to listOf("Medicina"),
        "Enfermería" to listOf("Enfermería"),
        "Agronomía" to listOf("Agronomía"),
        "Ciencias Contables y Financieras" to listOf("Contabilidad", "Finanzas"),
        "Ing. de Procesos" to listOf(
            "Ing. Química", "Ing. Ambiental", "Ing. de Materiales",
            "Ing. Metalúrgica", "Ing. de Industrias Alimentarias"
        ),
        "Ing. de Producción y Servicios" to listOf(
            "Ing. de Sistemas", "Ing. Eléctrica", "Ing. Electrónica", "Ing. Mecánica",
            "Ing. Industrial", "Ciencia de la Computación", "Ing. de Telecomunicaciones"
        ),
        "Geología, Geofísica y Minas" to listOf("Ing. Geofísica", "Ing. Geológica", "Ing. de Minas"),
        "Ing. Civil" to listOf("Ing. Civil", "Ing. Sanitaria"),
        "Ciencias Naturales y Formales" to listOf("Física", "Matemáticas", "Química"),
        "Economía" to listOf("Economía"),
        "Derecho" to listOf("Derecho"),
        "Ciencias Histórico Sociales" to listOf(
            "Trabajo Social", "Antropología", "Turismo y Hotelería", // TODO verificar nombre completo
            "Sociología", "Historia"
        ),
        "Psicología, RR.II. y Cs. de la Comunicación" to listOf(
            "Psicología", "Relaciones Industriales", "Ciencias de la Comunicación"
        ),
        "Filosofía y Humanidades" to listOf(
            "Filosofía", "Literatura y Lingüística" // TODO completar: especialidades de Artes
        ),
        "Administración" to listOf(
            "Administración", "Marketing", "Banca y Seguros", "Gestión Pública"
            // TODO completar: las dos escuelas "Gestión de ..." que aparecen cortadas en el PDF
        ),
        "Educación" to listOf(
            "Esp. Ciencias Naturales", "Esp. Ciencias Sociales", "Esp. Informática"
            // TODO completar: demás especialidades
        )
    )

    // Numero de laboratorios
    private val aulasPorEscuela = mapOf(
        "Ing. de Sistemas" to (1..10).map { "Laboratorio $it" },
        "Ing. Civil" to (1..15).map { "Laboratorio $it" },
        "Enfermería" to (1..12).map { "Laboratorio $it" }
    )

    // Numero de equipos
    private val equiposPorAula: Map<String, List<String>> =
        (1..15).associate { n ->
            "Laboratorio $n" to (1..20).map { "PC-%02d".format(it) }
        }



    fun escuelasDe(facultad: String): List<String> = facultades[facultad].orEmpty()
    fun aulasDe(escuela: String): List<String> = aulasPorEscuela[escuela].orEmpty()
    fun equiposDe(aula: String): List<String> = equiposPorAula[aula].orEmpty()

    // Equipo que "devuelve" el escáner simulado
    val equipoDemo = Equipo(
        codigo = "PC-07",
        aula = "Laboratorio 3",
        escuela = "Ing. de Sistemas",
        facultad = "Ing. de Producción y Servicios"
    )

    // Reportes ya abiertos (para la pantalla de duplicados)
    val reportesActivos = listOf(
        ReporteActivo(
            ticket = "RT-20410",
            equipo = Equipo("PC-18", "Laboratorio 2", "Ing. de Sistemas", "Ing. de Producción y Servicios"),
            dano = TipoDano.NO_ENCIENDE,
            estado = "En espera de soporte",
            hace = "Hace 5 min"
        )
    )

    fun reporteActivoDe(equipo: Equipo): ReporteActivo? =
        reportesActivos.firstOrNull { it.equipo == equipo }
}
