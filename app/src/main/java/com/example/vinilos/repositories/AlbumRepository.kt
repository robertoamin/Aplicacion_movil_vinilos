package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Album
import com.example.vinilos.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit)  {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbums({
            //Guardar los albums de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    fun crearAlbum(album: Album, onSuccess: () -> Unit, onError: (VolleyError) -> Unit) {
        networkServiceAdapter.crearAlbum(album, onSuccess, onError)
    }
    fun getAlbum(albumId: String, callback: (Album) -> Unit, onError: (VolleyError) -> Unit) {
        networkServiceAdapter.getAlbum(albumId, callback, onError)
    }
}
