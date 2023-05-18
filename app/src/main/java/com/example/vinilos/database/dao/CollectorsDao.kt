package com.example.vinilos.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vinilos.models.Collector

@Dao
interface CollectorsDao {
    // listar coleccionista
    @Query("SELECT * FROM collectors_table")
    fun getCollectors():List<Collector>
  // insertar coleccionista
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(collector:Collector)

}