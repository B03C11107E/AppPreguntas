package com.example.apppreguntas.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apppreguntas.ui.pantallas.PantallaHome
import com.example.apppreguntas.ui.pantallas.PantallaPregunta
import com.example.apppreguntas.ui.pantallas.PantallaStats
import com.example.apppreguntas.ui.rutas.rutas

@Composable
fun GrafoNavegacion(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = rutas.home.ruta) {

        composable(rutas.home.ruta){
            PantallaHome(navController = navController)
        }
        composable(rutas.stats.ruta){
                PantallaStats()
        }
        composable(rutas.pregunta.ruta){
            PantallaPregunta(navController = navController)
        }
    }
}