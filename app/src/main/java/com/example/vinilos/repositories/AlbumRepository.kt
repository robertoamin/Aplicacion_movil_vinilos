package com.example.vinilos.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos.database.dao.AlbumsDao
import com.example.vinilos.models.Album
import com.example.vinilos.network.NetworkServiceAdapter


class AlbumRepository (val application: Application, private val albumsDao: AlbumsDao){

    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    suspend fun refreshData(): List<Album>{
        var cached = albumsDao.getAlbums()
        albumsDao.deleteAll()
        return if(cached.isNullOrEmpty()){
            val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if( cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE){
                emptyList()
            } else {
                var busquedaA = networkServiceAdapter.getAlbums()
                albumsDao.insertMany(busquedaA)
                Log.d("tamaño", "albums: ${busquedaA.size}")
                return busquedaA
            }
        } else cached
    }

    fun crearAlbum(album: Album, onSuccess: () -> Unit, onError: (VolleyError) -> Unit) {
        networkServiceAdapter.crearAlbum(album, onSuccess, onError)
    }
    fun getAlbum(albumId: String, callback: (Album) -> Unit, onError: (VolleyError) -> Unit) {
        networkServiceAdapter.getAlbum(albumId, callback, onError)
    }
}
