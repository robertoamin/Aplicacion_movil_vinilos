package com.example.vinilos.ui.collector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.R
import com.example.vinilos.databinding.ActivityCollectorAddFavoritePerformersBinding
import com.example.vinilos.models.Band
import com.example.vinilos.viewmodels.CollectorAddFavoriteViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class CollectorAddFavoritePerformers : AppCompatActivity() {
    private lateinit var viewModel: CollectorAddFavoriteViewModel
    private lateinit var binding: ActivityCollectorAddFavoritePerformersBinding
    private lateinit var _bands: List<Band>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCollectorAddFavoritePerformersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CollectorAddFavoriteViewModel::class.java)

        setupView()
    }

    fun handleOnCancel(view: View) {
        finish()
    }

    fun handleOnSave(view: View) {
        val collectorId = intent?.extras?.getString("collectorId").toString()
        val autoCompleteTextView: MaterialAutoCompleteTextView = binding.editTextFavoritePerformer as MaterialAutoCompleteTextView
        val currentBand = autoCompleteTextView.text

        Log.i("ListSelection", autoCompleteTextView.text.toString())

        if (currentBand.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.favorite_performers_error_message), Toast.LENGTH_LONG).show()
            return
        }

        val band = _bands.find { it.name == currentBand.toString() }

        if (band != null) {
            viewModel.addFavoriteBand(
                band.bandId,
                collectorId
            ) {
                finish()
            }
        }
    }

    private fun setupView() {
        viewModel.bands.observe(this, Observer<List<Band>> { bands ->
            _bands = bands
            Log.i("getBands", bands.toString())

            val bandNames = bands.map { band -> band.name }

            Log.i("getBandsNames", bandNames.toString())

            (findViewById<MaterialAutoCompleteTextView>(R.id.editTextFavoritePerformer))?.setSimpleItems(bandNames.toTypedArray())
        })

        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })


        val appBar: MaterialToolbar = findViewById(R.id.topAppBar)

        appBar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}