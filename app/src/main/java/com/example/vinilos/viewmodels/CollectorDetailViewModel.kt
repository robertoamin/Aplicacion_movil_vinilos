package com.example.vinilos.viewmodels
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.bumptech.glide.load.HttpException
import com.example.vinilos.database.dao.VinylRoomDatabase
import com.example.vinilos.models.Band
import com.example.vinilos.models.Collector
import com.example.vinilos.repositories.BandRepository
import com.example.vinilos.repositories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectorDetailViewModel(application: Application, collectorId:String): AndroidViewModel(application) {
    private val collectorRepository: CollectorRepository = CollectorRepository(application, VinylRoomDatabase.getDatabase(application.applicationContext).collectorsDao())
    private val _collector = MutableLiveData<Collector>()

    val collector: LiveData<Collector>
        get() = _collector

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private val collectorId: String = collectorId

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = collectorRepository.getCollectorDetail(collectorId)
                    _collector.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e: HttpException){ //se procesa la excepcion
            Log.d("Error", e.toString())
            _eventNetworkError.value = true
        }

    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    init {
        refreshDataFromNetwork()
    }


    class Factory(
        private val app: Application,
        private val collectorId: String
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CollectorDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorDetailViewModel(app, collectorId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}