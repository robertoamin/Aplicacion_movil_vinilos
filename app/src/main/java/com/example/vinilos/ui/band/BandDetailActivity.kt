package com.example.vinilos.ui.band

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.vinilos.MainActivity
import com.example.vinilos.R

import com.example.vinilos.databinding.ActivityBandDetailBinding
import com.example.vinilos.viewmodels.BandDetailViewModel
import com.example.vinilos.models.Band
import com.google.android.material.appbar.MaterialToolbar
import java.text.SimpleDateFormat


class BandDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: BandDetailViewModel
    private lateinit var binding:ActivityBandDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val bandId = intent?.extras?.getString("bandId").toString()
        binding = ActivityBandDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, BandDetailViewModel.Factory(this.application, bandId)).get(
            BandDetailViewModel::class.java)

        viewModel.band.observe(this, Observer<Band> { band ->

            binding.band = band
            Glide.with(binding.root)
                .load(band.image)
                .into(binding.imageArtist)
        })

        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        val appBar: MaterialToolbar = findViewById(R.id.topAppBar)

        appBar.setNavigationOnClickListener {
            // Handle navigation icon press
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("listBands", "true")
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


