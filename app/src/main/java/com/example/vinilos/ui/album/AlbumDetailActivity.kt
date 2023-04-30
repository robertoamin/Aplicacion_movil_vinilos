package com.example.vinilos.ui.album

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.vinilos.MainActivity
import com.example.vinilos.R
import com.example.vinilos.databinding.ActivityAlbumDetailBinding
import com.example.vinilos.models.Album
import com.example.vinilos.viewmodels.AlbumDetailViewModel
import com.google.android.material.appbar.MaterialToolbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AlbumDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: AlbumDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val albumId = intent?.extras?.getString("albumId").toString()
        val binding = DataBindingUtil.setContentView<ActivityAlbumDetailBinding>(this, R.layout.activity_album_detail)

        viewModel = ViewModelProvider(this, AlbumDetailViewModel.Factory(this.application, albumId)).get(
            AlbumDetailViewModel::class.java)


        viewModel.album.observe(this, Observer<Album> { album ->
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            val releaseDate: Date = dateFormat.parse(album.releaseDate) as Date
            val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())
            val year = yearFormat.format(releaseDate)
            binding.yearFormatted = year
            binding.album = album
            Glide.with(binding.root)
                .load(album.cover)
                .into(binding.imageCover)
        })

        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })


        val appBar: MaterialToolbar = findViewById(R.id.topAppBar)
        appBar.setNavigationOnClickListener {
            // Handle navigation icon press
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("setAlbum", "true")
            startActivity(intent)
        }
    }


    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }


}
