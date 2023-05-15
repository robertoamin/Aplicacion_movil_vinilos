package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Album
import com.example.vinilos.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    suspend fun refreshData(): List<Album>  {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    fun crearAlbum(album: Album, onSuccess: () -> Unit, onError: (VolleyError) -> Unit) {
        networkServiceAdapter.crearAlbum(album, onSuccess, onError)
    }
    fun getAlbum(albumId: String, callback: (Album) -> Unit, onError: (VolleyError) -> Unit) {
        networkServiceAdapter.getAlbum(albumId, callback, onError)
    }
}
