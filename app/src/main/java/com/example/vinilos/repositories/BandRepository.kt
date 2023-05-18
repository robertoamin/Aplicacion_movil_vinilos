package com.example.vinilos.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.android.volley.VolleyError
import com.example.vinilos.database.dao.BandsDao
import com.example.vinilos.models.Band
import com.example.vinilos.network.NetworkServiceAdapter


class BandRepository (val application: Application, private val bandsDao: BandsDao){
    suspend fun refreshData(): List<Band>{
        var cached = bandsDao.getBands()
        return if(cached.isNullOrEmpty()){
            val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if( cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE){
                emptyList()
            } else NetworkServiceAdapter.getInstance(application).getAllBands()
        } else cached
    }

    fun getBandDetail(bandId: String, callback: (Band) -> Unit, onError: (VolleyError) -> Unit) {
        NetworkServiceAdapter.getInstance(application).getBandDetail(bandId,callback,onError)
    }
}