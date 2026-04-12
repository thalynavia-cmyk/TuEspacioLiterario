package com.example.tuespacioliterario.model  // solo una línea de package

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val price: Double,
    val imageUrl: String, // puede ser URL o nombre de drawable
    val description: String
) : java.io.Serializable
