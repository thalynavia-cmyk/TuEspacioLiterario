package com.example.tuespacioliterario
import com.example.tuespacioliterario.model.Cliente

import org.junit.Assert.assertEquals
import org.junit.Test

class ClienteTest {
    @Test
    fun `verificar que el cliente se crea correctamente`() {
        // 1. Preparamos los datos del módulo
        val cliente = Cliente(1, "Thaly Navia", "thaly@ejemplo.com")

        // 2. Verificamos que los datos integrados sean correctos
        assertEquals("Thaly Navia", cliente.nombre)
        assertEquals("thaly@ejemplo.com", cliente.email)
    }
}
