package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Band
import com.example.vinilos.network.NetworkServiceAdapter

class BandRepository (val application: Application) {

    suspend fun refreshData(): List<Band>{
        return NetworkServiceAdapter.getInstance(application).getAllBands()
    }

    fun getBandDetail(bandId: String, callback: (Band) -> Unit, onError: (VolleyError) -> Unit) {
        NetworkServiceAdapter.getInstance(application).getBandDetail(bandId,callback,onError)
    }
}