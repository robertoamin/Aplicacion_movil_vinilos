package com.example.vinilos.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vinilos.models.Album
import com.example.vinilos.models.Collector

@Dao
interface CollectorsDao {
    // listar coleccionista
    @Query("SELECT * FROM collectors_table")
    fun getCollectors():List<Collector>
  // insertar coleccionista
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(collector:Collector)

    // insertar coleccionistas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(vararg collectors:Collector)

}