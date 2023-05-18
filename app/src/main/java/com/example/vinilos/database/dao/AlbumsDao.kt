package com.example.vinilos.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vinilos.models.Album

@Dao
interface AlbumsDao {
    // listar albums
    @Query("SELECT * FROM albums_table")
    fun getAlbums():List<Album>
    // insertar album
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(album:Album)
  /*/ Detalle de album
    @Query("SELECT * FROM albums_table WHERE albumId = :albumId")
    fun getAlbum(albumId:Int):List<Album>

    @Query("DELETE FROM albums_table")
    suspend fun deleteAll()
*/
}