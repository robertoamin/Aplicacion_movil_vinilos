package com.example.vinilos.repositories
import android.app.Application
import com.example.vinilos.models.Album
import com.example.vinilos.network.NetworkServiceAdapter
import com.android.volley.VolleyError

class AlbumCrearRepository(val application: Application) {

    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)

    fun crearAlbum(album: Album, onSuccess: () -> Unit, onError: (VolleyError) -> Unit) {
        networkServiceAdapter.crearAlbum(album, onSuccess, onError)
    }
}
