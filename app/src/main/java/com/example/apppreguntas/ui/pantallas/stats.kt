package com.example.apppreguntas.ui.pantallas

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.apppreguntas.R

@Composable
fun PantallaStats() {
    val sharedPref = LocalContext.current.getSharedPreferences(
        stringResource(R.string.stats),
        Context.MODE_PRIVATE
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Aciertos: ${sharedPref.getInt("aciertos", 0)}", fontSize = 24.sp)
        Text("Fallos: ${sharedPref.getInt("fallos", 0)}", fontSize = 24.sp)
        Text("Total ${sharedPref.getInt("total", 0)}", fontSize = 24.sp)
    }
}