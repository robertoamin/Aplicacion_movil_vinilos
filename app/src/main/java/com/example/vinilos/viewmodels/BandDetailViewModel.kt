package com.example.vinilos.viewmodels
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.models.Band
import com.example.vinilos.network.NetworkServiceAdapter
import com.example.vinilos.repositories.BandRepository

class BandDetailViewModel( application: Application,bandId:String): AndroidViewModel(application) {
    private val bandRepository: BandRepository = BandRepository(application)
    private val _band = MutableLiveData<Band>()

    val band: LiveData<Band>
        get() = _band

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private val bandId: String = bandId

    private fun refreshDataFromNetwork() {
      bandRepository.getBandDetail(bandId,{band ->
          _band.postValue(band)
          _eventNetworkError.value = false
          _isNetworkErrorShown.value = false
      },{
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
        private val bandId: String
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BandDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BandDetailViewModel(app, bandId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}