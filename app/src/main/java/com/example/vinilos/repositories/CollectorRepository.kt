package com.example.vinilos.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.vinilos.database.dao.CollectorsDao
import com.example.vinilos.models.Album
import com.example.vinilos.models.Collector
import com.example.vinilos.network.NetworkServiceAdapter


class CollectorRepository (val application: Application, private val collectorsDao: CollectorsDao){
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    suspend fun refreshData(): List<Collector>{
        var cached = collectorsDao.getCollectors()
        return if(cached.isNullOrEmpty()){
            val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if( cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE){
                emptyList()
            } else {
                NetworkServiceAdapter.getInstance(application).getCollectors()
                var busqueda = networkServiceAdapter.getCollectors()
                collectorsDao.insertMany(*busqueda.toTypedArray())
                Log.d("tamaño", "colecionistas: ${busqueda.size}")
                return busqueda

            }
        } else cached
    }
}

