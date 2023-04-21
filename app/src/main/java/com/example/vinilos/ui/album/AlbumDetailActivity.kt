package com.example.vinilos.ui.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.R
import com.example.vinilos.databinding.ActivityAlbumDetailBinding
import com.example.vinilos.models.Album
import com.example.vinilos.viewmodels.AlbumDetailViewModel


class AlbumDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: AlbumDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val albumId = intent?.extras?.getString("albumId").toString()
        val binding = DataBindingUtil.setContentView<ActivityAlbumDetailBinding>(this, R.layout.activity_album_detail)

        viewModel = ViewModelProvider(this, AlbumDetailViewModel.Factory(this.application, albumId)).get(
            AlbumDetailViewModel::class.java)

        viewModel.album.observe(this, Observer<Album> {
            it.apply {
                binding.album = this
            }
        })
        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }


}
