package com.example.vinilos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bands_table")
data class Band (
    @PrimaryKey val bandId:Int,
    val name:String,
    val description:String,
    val image:String
)
