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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tuespacioliterario.model.Cliente
import com.example.tuespacioliterario.ui.theme.TuEspacioLiterarioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TuEspacioLiterarioTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    // LLAMAMOS A LA PANTALLA INTEGRADA
                    PantallaPrincipalIntegrada()
                }
            }
        }
    }
}

@Composable
fun PantallaPrincipalIntegrada() {
    // ESTADOS DE INTEGRACIÓN (CLIENTES Y LIBROS)
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val listaClientes = remember { mutableStateListOf<Cliente>() }

    val listaLibros = remember {
        mutableStateListOf(
            Libro(1, "Cien años de soledad", "Gabriel García Márquez", 45000.0),
            Libro(2, "El resplandor", "Stephen King", 35000.0),
            Libro(3, "Código Limpio", "Robert C. Martin", 120000.0)
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // MÓDULO 1: REGISTRO DE CLIENTES
        Text(text = "Registro de Clientes", style = MaterialTheme.typography.headlineSmall)
        TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())

        Button(onClick = {
            if (nombre.isNotBlank() && email.isNotBlank()) {
                listaClientes.add(Cliente(id = listaClientes.size + 1, nombre = nombre, email = email))
                nombre = ""; email = ""
            }
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Guardar Cliente")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        // MÓDULO 2: CATÁLOGO DE LIBROS INTEGRADO
        Text(text = "Catálogo de Libros", style = MaterialTheme.typography.headlineSmall)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(listaLibros) { libro ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = libro.titulo, style = MaterialTheme.typography.titleMedium)
                        Text(text = "Autor: ${libro.autor}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
            // TAMBIÉN MOSTRAMOS CLIENTES REGISTRADOS ABAJO
            item { Text("Clientes Registrados:", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp)) }
            items(listaClientes) { cliente ->
                Text(text = "• ${cliente.nombre} (${cliente.email})")
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TuEspacioLiterarioTheme {
        PantallaPrincipalIntegrada()
    }
}

