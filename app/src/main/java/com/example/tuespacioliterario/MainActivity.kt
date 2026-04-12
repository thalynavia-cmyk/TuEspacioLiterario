package com.example.tuespacioliterario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tuespacioliterario.model.Cliente // Importación clave
import com.example.tuespacioliterario.ui.theme.TuEspacioLiterarioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TuEspacioLiterarioTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    GestionClientesScreen()
                }
            }
        }
    }
}

@Composable
fun GestionClientesScreen() {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    // Integración de datos: Lista reactiva de la clase Cliente
    val listaClientes = remember { mutableStateListOf<Cliente>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Registro de Clientes - Tu Espacio Literario", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())

        Button(
            onClick = {
                if (nombre.isNotBlank() && email.isNotBlank()) {
                    // Integración: Se crea objeto Cliente y se añade a la lista
                    listaClientes.add(Cliente(id = listaClientes.size + 1, nombre = nombre, email = email))
                    nombre = ""
                    email = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Guardar Cliente")
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        Text(text = "Tabla donde se integran los datos:", style = MaterialTheme.typography.titleMedium)

        // Visualización de la integración
        LazyColumn {
            items(listaClientes) { cliente ->
                Text(text = "ID: ${cliente.id} - ${cliente.nombre} (${cliente.email})", modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}
