package com.example.vinilos

import android.app.Application
import com.example.vinilos.database.dao.VinylRoomDatabase

class VinylsApplication: Application()  {
    val database by lazy { VinylRoomDatabase.getDatabase(this) }
}