package com.example.vinilos.network
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos.brokers.VolleyBroker

import com.example.vinilos.models.Album
import com.example.vinilos.models.Band
import com.example.vinilos.models.Collector
import org.json.JSONArray
import org.json.JSONObject


class NetworkServiceAdapter constructor(context: Context) {
    companion object {
        const val BASE_URL = "https://back-vinilos.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getAlbums(onComplete: (resp: List<Album>) -> Unit, onError: (error: VolleyError) -> Unit) {
        requestQueue.add(
            getRequest("albums",
                { response ->
                    val resp = JSONArray(response)
                    Log.d("RESPONSE_SIZE", "Size of response: ${resp.length()}")
                    Log.d("RESPONSE", "Response from server: $resp")

                    val list = mutableListOf<Album>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        list.add(
                            i,
                            Album(
                                albumId = item.getInt("id"),
                                name = item.getString("name"),
                                cover = item.getString("cover"),
                                recordLabel = item.getString("recordLabel"),
                                releaseDate = item.getString("releaseDate"),
                                genre = item.getString("genre"),
                                description = item.getString("description")
                            )
                        )
                    }
                    onComplete(list)
                    // Imprimir el tamaño de la lista en consola
                    println("#1. Tamaño de la lista de albums: ${list.size}")
                },
                { error ->
                    Log.e("NETWORK_ERROR", "Error in network request: ${error.message}")
                    onError(error)
                })
        )
    }



    fun getAllBands(onComplete: (resp: List<Band>) -> Unit, onError: (error: VolleyError) -> Unit) {
        requestQueue.add(
            getRequest("bands",
                { response ->
                    val resp = JSONArray(response)
                    Log.d("RESPONSE_SIZE", "Size of response: ${resp.length()}")
                    val list = mutableListOf<Band>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        list.add(
                            i,
                            Band(
                                bandId = item.getInt("id"),
                                name = item.getString("name"),
                                description = item.getString("description"),
                                image = item.getString("image")
                            )
                        )
                    }
                    onComplete(list)
                    // Imprimir el tamaño de la lista en consola
                    println("#2. Tamaño de la lista de bandas: ${list.size}")
                },
                { error ->
                    Log.e("NETWORK_ERROR", "Error in network request: ${error.message}")
                    onError(error)
                })
        )
    }

    fun getAlbum(
        albumId: String,
        onComplete: (resp: Album) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        requestQueue.add(
            getRequest(
                "albums/$albumId",
                { response ->
                    val albumJson = JSONObject(response)
                    val album = Album(
                        albumJson.getInt("id"),
                        albumJson.getString("name"),
                        albumJson.getString("cover"),
                        albumJson.getString("releaseDate"),
                        albumJson.getString("description"),
                        albumJson.getString("genre"),
                        albumJson.getString("recordLabel")
                    )
                    onComplete(album)
                },
                { error ->
                    onError(error)
                }
            )
        )
    }

    fun getBandDetail(
        bandId: String,
        onComplete: (resp: Band) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        requestQueue.add(
            getRequest(
                "bands/$bandId",
                { response ->
                    val bandJson = JSONObject(response)
                    val bandDetail = Band(
                        bandJson.getInt("id"),
                        bandJson.getString("name"),
                        bandJson.getString("description"),
                        bandJson.getString("image")
                    )
                    onComplete(bandDetail)
                },
                { error ->
                    onError(error)
                }
            )
        )
    }


    fun getCollectors(onComplete:(resp:List<Collector>)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("collectors",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                var item: JSONObject;
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i, Collector(id = item.getInt("id"),name = item.getString("name"), telephone = item.getString("telephone"), email = item.getString("email")))
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }


    fun crearAlbum(album: Album, onComplete: () -> Unit, onError: (error: VolleyError) -> Unit) {
        val albumJson = JSONObject().apply {
            put("name", album.name)
            put("description", album.description)
            put("cover", album.cover)
            put("releaseDate", album.releaseDate)
            put("genre", album.genre)
            put("recordLabel", album.recordLabel)
        }

        val request = JsonObjectRequest(
            Request.Method.POST,
            BASE_URL + "albums",
            albumJson,
            { _ ->
                onComplete()
            },
            { error ->
                onError(error)
            }
        )

        requestQueue.add(request)
    }

    fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, VolleyBroker.BASE_URL +path, responseListener,errorListener)
    }
    fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.POST, VolleyBroker.BASE_URL +path, body, responseListener, errorListener)
    }

}