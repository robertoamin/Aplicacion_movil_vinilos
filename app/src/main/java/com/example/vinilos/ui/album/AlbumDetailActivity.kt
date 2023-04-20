package com.example.vinilos.ui.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.R
import com.example.vinilos.viewmodels.AlbumDetailViewModel

class AlbumDetailActivity : AppCompatActivity() {

    // Declarar una instancia del ViewModel
    private lateinit var albumDetailViewModel: AlbumDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        // Obtener la instancia del ViewModel
        albumDetailViewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)

        // Observar cambios en los datos del ViewModel
        albumDetailViewModel.album.observe(this, Observer { album ->
            // Actualizar la vista con los datos del álbum
            // ...
        })
    }

}
