package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Band
import com.example.vinilos.network.NetworkServiceAdapter

class BandRepository (val application: Application) {

    fun refreshData(callback: (List<Band>)->Unit, onError: (VolleyError)->Unit){
        NetworkServiceAdapter.getInstance(application).getAllBands({
            callback(it)
        },{
            onError
        })
    }
}