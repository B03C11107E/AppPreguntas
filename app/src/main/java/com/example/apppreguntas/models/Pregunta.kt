package com.example.apppreguntas.models

data class Pregunta(
    val enunciado : String,
    val opcionCorrecta : Boolean,
    val infoExtra : String,
    val imagen : Int
)