package com.example.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.models.Band
import com.example.vinilos.repositories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class FavoritePerformersViewModel(application: Application, collectorId: String) :  AndroidViewModel(application) {
    private val _favoritePerformers = MutableLiveData<List<Band>>()
    private val collectorRepository = CollectorRepository(application)
    private val collectorId: String = collectorId
    val favoritePerformers: LiveData<List<Band>>
        get() = _favoritePerformers

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    val data = collectorRepository.getCollectorFavoritePerformers(collectorId)
                    _favoritePerformers.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        } catch (e: HttpException) {
            Log.d("Error", e.toString())
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(
        val app: Application,
        val collectorId: String
    ) : ViewModelProvider.AndroidViewModelFactory(app){

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoritePerformersViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavoritePerformersViewModel(app, collectorId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }


}