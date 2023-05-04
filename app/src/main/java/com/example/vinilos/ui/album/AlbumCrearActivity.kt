package com.example.vinilos.ui.album
import android.os.Bundle
import android.view.View

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider

import com.example.vinilos.databinding.ActivityAlbumCrearBinding

import com.example.vinilos.viewmodels.AlbumCrearViewModel


class AlbumCrearActivity : AppCompatActivity() {

        private lateinit var binding: ActivityAlbumCrearBinding
        private lateinit var viewModel: AlbumCrearViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityAlbumCrearBinding.inflate(layoutInflater)
            setContentView(binding.root)

            viewModel = ViewModelProvider(this).get(AlbumCrearViewModel::class.java)

            setupViews()
            setupObservers()
        }


    private fun setupViews() {
        binding.buttonGuardar.setOnClickListener {
            val name = binding.editTextNombre.text.toString()
            val description = binding.editTextDescripcion.text.toString()
            val cover = binding.editTextCover.text.toString()
            val releaseDate = binding.editTextReleasedate.text.toString()
            val genre = binding.editTextGenre.text.toString()
            val recordLabel = binding.editTextRecordlabel.text.toString()
            viewModel.guardarAlbum(name,  cover, releaseDate, description, genre, recordLabel)
        }
    }

    fun cancelarOnClick(view: View) {
        finish()
    }


        private fun setupObservers() {
            viewModel.albumCreado.observe(this, { albumCreado ->
                if (albumCreado) {
                    Toast.makeText(this, "Álbum creado con éxito", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "No se pudo crear el álbum", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
