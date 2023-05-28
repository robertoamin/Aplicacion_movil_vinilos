package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.database.dao.VinylRoomDatabase
import com.example.vinilos.models.Album
import com.example.vinilos.repositories.AlbumRepository

class AlbumDetailViewModel(
    application: Application,
    albumId: String
) : AndroidViewModel(application) {

    private val albumRepository = AlbumRepository(application, VinylRoomDatabase.getDatabase(application.applicationContext).albumsDao())

    private val _album = MutableLiveData<Album>()

    val album: LiveData<Album>
        get() = _album

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private val albumId: String = albumId

    private fun refreshDataFromNetwork() {
        albumRepository.getAlbum(albumId, { album ->
            _album.postValue(album)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        }, {
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
    init {
        refreshDataFromNetwork()
    }
    class Factory(
        private val app: Application,
        private val albumId: String
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumDetailViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
