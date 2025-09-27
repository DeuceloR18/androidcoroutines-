package com.example.coroutineskt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.appdistribution.gradle.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuarioViewModel : ViewModel() {

    private val _usuario = MutableLiveData<userinfo.Usuario>()
    val usuario: LiveData<userinfo.Usuario> = _usuario

    private val _estadoCarga = MutableLiveData<String>()
    val estadoCarga: LiveData<String> = _estadoCarga

    private val apiService = RetrofitClient.apiService
    fun cargarDatosUsuario(idUsuario: Int) {
        // 1. Lanza una Coroutine en el viewModelScope
        viewModelScope.launch {
            try {
                // 2. Notifica a la UI que la carga ha iniciado
                _estadoCarga.value = "Cargando..."

                // 3. Usa withContext(Dispatchers.IO) para cambiar al hilo de I/O
                //    y ejecutar la función suspendida de la API.
                val resultado = withContext(Dispatchers.IO) {
                    apiService.obtenerUsuario(idUsuario)
                }

                _usuario.value = resultado
                _estadoCarga.value = "Carga exitosa."

            } catch (e: Exception) {
                // 5. Manejo de errores
                _estadoCarga.value = "Error: ${e.message}"
            }
        }
    }

    private fun ApiService.obtenerUsuario(idUsuario: Int) {}
}