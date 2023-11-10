package com.example.apppreguntas.ui.pantallas

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.apppreguntas.models.Pregunta
import com.example.apppreguntas.R
import com.example.apppreguntas.ui.theme.ButtonColor
import com.example.apppreguntas.ui.theme.Green
import com.example.apppreguntas.ui.theme.Red


val listaDePreguntas = listOf(
    Pregunta("Los himnos cantados en honor a Apolo recibían el nombre de peanes.", true,
        "Como jefe de las Musas inspiradoras (con el epíteto Apolo Musageta), y director de su coro, actuaba como dios patrón de la música y la poesía.",
        R.drawable.apolo),
    Pregunta("Psique es la personificación de la memoria.", false,
        "Es personificación griega del alma y suele representarse como una hermosa mujer con alas de mariposa.", R.drawable.psique),
    Pregunta("El Tártaro es un lugar del Inframundo, más profundo incluso que el Hades.", true,
        "Como mejor se conoce el tártaro es gracias a la Teogonía de Hesíodo, donde es uno de los primeros seres en existir en el universo además del " +
                "lugar donde se encierra a los monstruos, los titanes.", R.drawable.tartaro),
    Pregunta("A Perseo se le atribuye la muerte del León de Citerón.", false,
        "Heracles mató al león estrangulándolo con sus propias manos.", R.drawable.perseo),
    Pregunta("El ciervo y el ciprés le estaban consagrados a la diosa Hera.", false, "Estaban consagrados a la diosa Artemisa",
        R.drawable.hera)
)
val ColorSaver = Saver<Color, Int>(
    save = {it.toArgb()},
    restore={ Color(it) }
)
@Composable
fun PantallaPregunta(){
    val sharedPref = LocalContext.current.getSharedPreferences(
        stringResource(R.string.stats),
        Context.MODE_PRIVATE
    )
    var correcion by rememberSaveable { mutableStateOf("") }
    var preguntaActual by rememberSaveable { mutableStateOf(0) }
    var colorBotonTrue by rememberSaveable(stateSaver = ColorSaver) { mutableStateOf(ButtonColor) }
    var colorBotonFalse by rememberSaveable(stateSaver = ColorSaver) { mutableStateOf(ButtonColor) }
    var aciertos by rememberSaveable { mutableStateOf(sharedPref.getInt("aciertos", 0)) }
    var fallos by rememberSaveable { mutableStateOf(sharedPref.getInt("fallos", 0)) }
    var total by rememberSaveable { mutableStateOf(sharedPref.getInt("total", 0)) }
    var buttonTrueEnabled by rememberSaveable { mutableStateOf(true) }
    var buttonFalseEnabled by rememberSaveable { mutableStateOf(true) }
    Column(verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = listaDePreguntas[preguntaActual].enunciado, fontSize = 24.sp, modifier = Modifier.weight(1F))
        Image(painter = painterResource(listaDePreguntas[preguntaActual].imagen), contentDescription = "",
            modifier = Modifier
                .weight(3F)
                .fillMaxSize(), contentScale = ContentScale.Crop)
        Text(text = correcion, modifier = Modifier.weight(1.5F), fontSize = 16.sp)
        Row(Modifier.weight(0.5F)) {
            Boton(texto = "true", ButtonDefaults.buttonColors(colorBotonTrue, disabledContainerColor = colorBotonTrue, disabledContentColor = Color.White)
                ,buttonTrueEnabled) {
                if (listaDePreguntas[preguntaActual].opcionCorrecta) {
                    correcion = "Bien. " + listaDePreguntas[preguntaActual].infoExtra
                    aciertos++
                    total++
                } else {
                    correcion = "Mal. " + listaDePreguntas[preguntaActual].infoExtra
                    fallos++
                    total++
                }
                buttonFalseEnabled = false
                buttonTrueEnabled = false
                colorBotonTrue =
                    if (listaDePreguntas[preguntaActual].opcionCorrecta) Green else Red
                colorBotonFalse =
                    if (!listaDePreguntas[preguntaActual].opcionCorrecta) Green else Red

            }
            Boton(texto = "false", ButtonDefaults.buttonColors(colorBotonFalse, disabledContainerColor = colorBotonFalse, disabledContentColor = Color.White)
                ,buttonFalseEnabled) {
                if (!listaDePreguntas[preguntaActual].opcionCorrecta) {
                    correcion = "Bien. " + listaDePreguntas[preguntaActual].infoExtra
                    aciertos++
                    total++
                } else {
                    correcion = "Mal. " + listaDePreguntas[preguntaActual].infoExtra
                    fallos++
                    total++
                }
                buttonFalseEnabled = false
                buttonTrueEnabled = false
                colorBotonFalse =
                    if (!listaDePreguntas[preguntaActual].opcionCorrecta) Green else Red
                colorBotonTrue =
                    if (listaDePreguntas[preguntaActual].opcionCorrecta) Green else Red
            }
        }
        Row(Modifier.weight(0.5F)) {
            BotonConIcono(
                texto = "Previous",
                ButtonDefaults.buttonColors(ButtonColor),
                Icons.Filled.ArrowBack
            ) {
                if (preguntaActual == 0) {
                    preguntaActual = listaDePreguntas.size - 1
                } else {
                    preguntaActual--
                }
                correcion = ""
                colorBotonTrue = ButtonColor
                colorBotonFalse = ButtonColor
                buttonFalseEnabled = true
                buttonTrueEnabled = true
            }
            BotonConIcono(
                texto = "Next",
                ButtonDefaults.buttonColors(ButtonColor),
                Icons.Filled.ArrowForward
            ) {
                if (preguntaActual == listaDePreguntas.size - 1) {
                    preguntaActual = 0
                } else {
                    preguntaActual++
                }
                correcion = ""
                colorBotonTrue = ButtonColor
                colorBotonFalse = ButtonColor
                buttonFalseEnabled = true
                buttonTrueEnabled = true
            }
        }
        Row(Modifier.weight(0.5F)){
            BotonConIcono(
                texto = "Random",
                ButtonDefaults.buttonColors(ButtonColor),
                Icons.Filled.Star) {
                var rnds = -1
                while(rnds == -1 || rnds == preguntaActual){
                    rnds = (listaDePreguntas.indices).random()
                }
                preguntaActual = rnds
                correcion = ""
                colorBotonTrue = ButtonColor
                colorBotonFalse = ButtonColor
                buttonFalseEnabled = true
                buttonTrueEnabled = true
            }
        }
        sharedPref.edit().putInt("aciertos", aciertos).putInt("fallos", fallos).putInt("total", total).apply()
    }
}
@Composable
fun Boton(texto: String, color: ButtonColors, enabled: Boolean, accion: (() -> Unit)) {
    Button(onClick = accion, colors = color, enabled = enabled){
        Text( text = texto)
    }
}
@Composable
fun BotonConIcono(texto: String, color: ButtonColors, icono: ImageVector, accion: (() -> Unit)) {
    Button(onClick = accion, colors = color){
        Icon(icono, contentDescription = "")
        Text( text = texto)
    }
}