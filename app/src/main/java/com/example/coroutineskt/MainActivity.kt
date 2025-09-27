package com.example.coroutineskt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coroutineskt.ui.theme.CoroutinesktTheme
import kotlin.properties.ReadOnlyProperty
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Inicializa el ViewModel
    private val viewModel: UsuarioViewModel by viewModels()

    private fun viewModels(): ReadOnlyProperty<MainActivity, UsuarioViewModel> {}

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Observa el LiveData del usuario
        viewModel.usuario.observe(this) { usuario ->
            // Actualiza TextViews con el nombre y email
            findViewById<TextView>(R.id.text_nombre).text = usuario.nombre
            findViewById<TextView>(R.id.text_email).text = usuario.email
        }

        viewModel.estadoCarga.observe(this) { estado ->
            Log.d("API_CALL", "Estado: $estado")
        }

        viewModel.cargarDatosUsuario(idUsuario = 42) // Ejemplo de ID
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoroutinesktTheme {
        Greeting("Android")
    }
}