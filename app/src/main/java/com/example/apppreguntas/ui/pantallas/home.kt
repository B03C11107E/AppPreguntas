package com.example.apppreguntas.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.apppreguntas.ui.rutas.rutas

@Composable
fun PantallaHome(navController : NavHostController?){
    Column(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)) {
        Text(text = "Aplicacion de preguntas", fontSize = 24.sp, modifier = Modifier.weight(1F))
        Button(onClick = { navController?.navigate(rutas.stats.ruta) },
            Modifier
                .wrapContentSize(Alignment.Center)) {
                Text("Estadísticas")

        }
        Button(onClick = { navController?.navigate(rutas.pregunta.ruta) },
            Modifier
                .wrapContentSize(Alignment.Center)) {
                Text("Preguntas")
        }


    }
}