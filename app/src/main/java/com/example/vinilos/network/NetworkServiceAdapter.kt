package com.example.vinilos.network
import android.content.Context
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos.brokers.VolleyBroker.Companion.getRequest
import com.example.vinilos.models.Album
import com.example.vinilos.models.Band
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
                },
                {
                    onError(it)
                })
        )
    }

    fun getAllBands(onComplete: (resp: List<Band>) -> Unit, onError: (error: VolleyError) -> Unit) {
        requestQueue.add(
            getRequest("bands",
                { response ->
                    val resp = JSONArray(response)
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
                },
                {
                    onError(it)
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


}