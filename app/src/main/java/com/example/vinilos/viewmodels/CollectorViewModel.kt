package com.example.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.database.dao.VinylRoomDatabase
import com.example.vinilos.models.Collector
import com.example.vinilos.repositories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CollectorViewModel(application: Application) :  AndroidViewModel(application) {
    private val _collectors = MutableLiveData<List<Collector>>()
    private val collectorRepository = CollectorRepository(application, VinylRoomDatabase.getDatabase(application.applicationContext).collectorsDao())

    val collectors: LiveData<List<Collector>>
        get() = _collectors

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
                    var data = collectorRepository.refreshData()
                    _collectors.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:HttpException){ //se procesa la excepcion
            Log.d("Error", e.toString())
            _eventNetworkError.value = true
        }
    }


    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.AndroidViewModelFactory(app){

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CollectorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}