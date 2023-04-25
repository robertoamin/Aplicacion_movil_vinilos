package com.example.vinilos.models

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Album(
    val albumId:Int,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String
)
