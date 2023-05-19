package com.example.vinilos.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vinilos.models.Album
import com.example.vinilos.models.Band


@Dao
interface BandsDao {
    // Listado de Bandas
    @Query("SELECT * FROM bands_table")
    fun getBands():List<Band>
   // insertar banda
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(band:Band)

    //Insertar Bandas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(bands:List<Band>)
/*     // Detalle de Banda
    @Query("SELECT * FROM bands_table WHERE bandId = :bandId")
    fun getBanda(bandInt: Int):List<Band>
*/
    @Query("DELETE FROM bands_table")
    suspend fun deleteAll()

}