package com.example.vinilos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collectors_table")
data class Collector(
    @PrimaryKey val id:Int,
    val name:String,
    val telephone:String,
    val email:String
)

