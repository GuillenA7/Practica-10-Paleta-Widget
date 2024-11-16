package com.example.prctica10_paletawidget

data class Servicio(
    val nombre: String,
    val descripcion: String,
    val imagenResId: Int,
    var calificacion: Float = 0f // Calificación inicial
)