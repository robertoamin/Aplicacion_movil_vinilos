package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Band
import com.example.vinilos.models.Collector
import com.example.vinilos.network.NetworkServiceAdapter

class CollectorRepository (val application: Application) {

    suspend fun refreshData(): List<Collector>{
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getCollectors()
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