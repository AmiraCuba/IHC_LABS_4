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
            "Trabajo Social", "Antropología", "Turismo y Hotelería",
            "Sociología", "Historia"
        ),
        "Psicología, RR.II. y Cs. de la Comunicación" to listOf(
            "Psicología", "Relaciones Industriales", "Ciencias de la Comunicación"
        ),
        "Filosofía y Humanidades" to listOf(
            "Filosofía", "Literatura y Lingüística", "Artes Plásticas", "Música"
        ),
        "Administración" to listOf(
            "Administración", "Marketing", "Banca y Seguros", "Gestión Pública",
            "Gestión de Recursos Humanos", "Gestión de Negocios Internacionales"
        ),
        "Educación" to listOf(
            "Esp. Ciencias Naturales", "Esp. Ciencias Sociales", "Esp. Informática",
            "Esp. Educación Inicial", "Esp. Educación Primaria", "Esp. Educación Secundaria"
        )
    )

    // Numero de laboratorios por escuela
    private val aulasPorEscuela = mapOf(
        "Arquitectura" to (1..8).map { "Laboratorio $it" },
        "Biología" to (1..8).map { "Laboratorio $it" },
        "Ciencias de la Nutrición" to (1..6).map { "Laboratorio $it" },
        "Ing. Pesquera" to (1..6).map { "Laboratorio $it" },
        "Medicina" to (1..10).map { "Laboratorio $it" },
        "Enfermería" to (1..12).map { "Laboratorio $it" },
        "Agronomía" to (1..8).map { "Laboratorio $it" },
        "Contabilidad" to (1..6).map { "Laboratorio $it" },
        "Finanzas" to (1..6).map { "Laboratorio $it" },
        "Ing. Química" to (1..10).map { "Laboratorio $it" },
        "Ing. Ambiental" to (1..8).map { "Laboratorio $it" },
        "Ing. de Materiales" to (1..8).map { "Laboratorio $it" },
        "Ing. Metalúrgica" to (1..8).map { "Laboratorio $it" },
        "Ing. de Industrias Alimentarias" to (1..8).map { "Laboratorio $it" },
        "Ing. de Sistemas" to (1..10).map { "Laboratorio $it" },
        "Ing. Eléctrica" to (1..10).map { "Laboratorio $it" },
        "Ing. Electrónica" to (1..10).map { "Laboratorio $it" },
        "Ing. Mecánica" to (1..10).map { "Laboratorio $it" },
        "Ing. Industrial" to (1..10).map { "Laboratorio $it" },
        "Ciencia de la Computación" to (1..10).map { "Laboratorio $it" },
        "Ing. de Telecomunicaciones" to (1..10).map { "Laboratorio $it" },
        "Ing. Geofísica" to (1..8).map { "Laboratorio $it" },
        "Ing. Geológica" to (1..8).map { "Laboratorio $it" },
        "Ing. de Minas" to (1..8).map { "Laboratorio $it" },
        "Ing. Civil" to (1..15).map { "Laboratorio $it" },
        "Ing. Sanitaria" to (1..12).map { "Laboratorio $it" },
        "Física" to (1..8).map { "Laboratorio $it" },
        "Matemáticas" to (1..6).map { "Laboratorio $it" },
        "Química" to (1..8).map { "Laboratorio $it" },
        "Economía" to (1..6).map { "Laboratorio $it" },
        "Derecho" to (1..6).map { "Laboratorio $it" },
        "Trabajo Social" to (1..4).map { "Laboratorio $it" },
        "Antropología" to (1..4).map { "Laboratorio $it" },
        "Turismo y Hotelería" to (1..4).map { "Laboratorio $it" },
        "Sociología" to (1..4).map { "Laboratorio $it" },
        "Historia" to (1..4).map { "Laboratorio $it" },
        "Psicología" to (1..6).map { "Laboratorio $it" },
        "Relaciones Industriales" to (1..6).map { "Laboratorio $it" },
        "Ciencias de la Comunicación" to (1..6).map { "Laboratorio $it" },
        "Filosofía" to (1..4).map { "Laboratorio $it" },
        "Literatura y Lingüística" to (1..4).map { "Laboratorio $it" },
        "Artes Plásticas" to (1..4).map { "Laboratorio $it" },
        "Música" to (1..4).map { "Laboratorio $it" },
        "Administración" to (1..6).map { "Laboratorio $it" },
        "Marketing" to (1..6).map { "Laboratorio $it" },
        "Banca y Seguros" to (1..6).map { "Laboratorio $it" },
        "Gestión Pública" to (1..6).map { "Laboratorio $it" },
        "Gestión de Recursos Humanos" to (1..6).map { "Laboratorio $it" },
        "Gestión de Negocios Internacionales" to (1..6).map { "Laboratorio $it" },
        "Esp. Ciencias Naturales" to (1..4).map { "Laboratorio $it" },
        "Esp. Ciencias Sociales" to (1..4).map { "Laboratorio $it" },
        "Esp. Informática" to (1..4).map { "Laboratorio $it" },
        "Esp. Educación Inicial" to (1..4).map { "Laboratorio $it" },
        "Esp. Educación Primaria" to (1..4).map { "Laboratorio $it" },
        "Esp. Educación Secundaria" to (1..4).map { "Laboratorio $it" }
    )

    // Numero de equipos por laboratorio (máquinas PC-01 a PC-20 en cada aula)
    private val equiposPorAula: Map<String, List<String>> =
        aulasPorEscuela.values.flatten().associate { aula ->
            aula to (1..20).map { "PC-%02d".format(it) }
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
