package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.models.Band
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.vinilos.database.dao.CollectorsDao
import com.example.vinilos.models.Collector
import com.example.vinilos.network.NetworkServiceAdapter


class CollectorRepository (val application: Application, private val collectorsDao: CollectorsDao){
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    suspend fun refreshData(): List<Collector>{
        var cached = collectorsDao.getCollectors()
        collectorsDao.deleteAll()
        return if(cached.isNullOrEmpty()){
            val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if( cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE){
                emptyList()
            } else {

                var busquedaC = networkServiceAdapter.getCollectors()
                collectorsDao.insertMany(busquedaC)
                Log.d("tamaño", "Coleccionistas: ${busquedaC.size}")
                return busquedaC

            }
        } else cached
    }


    suspend fun getCollectorDetail(collectorId: String): Collector{
        return NetworkServiceAdapter.getInstance(
            application).getCollectorDetail(collectorId)
    }

    suspend fun getCollectorFavoritePerformers(collectorId: String): List<Band>{
        return NetworkServiceAdapter.getInstance(
            application).getCollectorsFavoritePerformers(collectorId)
    }
}
