package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vinilos.models.Album
import com.example.vinilos.repositories.AlbumRepository

class AlbumCrearViewModel(application: Application) : AndroidViewModel(application) {
    private val albumRepository = AlbumRepository(application)
    private val _albumCreado = MutableLiveData<Boolean>()
    val albumCreado: LiveData<Boolean>
        get() = _albumCreado

    fun guardarAlbum(name: String, cover: String, releaseDate: String, description: String, genre: String, recordLabel: String, albumId: Int? = null) {
        val album = Album(albumId ?: 0, name,  cover, releaseDate, description, genre, recordLabel)

        albumRepository.crearAlbum(album,
            {
                _albumCreado.postValue(true)
            },
            { error ->
                _albumCreado.postValue(false)
            }
        )
    }

}


