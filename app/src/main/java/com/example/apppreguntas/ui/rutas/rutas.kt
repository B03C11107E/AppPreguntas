package com.example.apppreguntas.ui.rutas

sealed class rutas(val ruta: String) {
    object home: rutas("home")
    object pregunta: rutas("pregunta")
    object stats: rutas("stats")
}