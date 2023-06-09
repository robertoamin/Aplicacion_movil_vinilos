package com.example.vinilos.ui.collector

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.MainActivity
import com.example.vinilos.R
import com.example.vinilos.databinding.ActivityCollectorDetailBinding
import com.example.vinilos.models.Collector
import com.example.vinilos.viewmodels.CollectorDetailViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.activity.result.ActivityResultLauncher;


class CollectorDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: CollectorDetailViewModel
    private lateinit var binding:ActivityCollectorDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val collectorId = intent?.extras?.getString("collectorId").toString()
        binding = ActivityCollectorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val view = binding.root
        val addFavoriteButton: FloatingActionButton = view.findViewById(R.id._favorite_action_button)

        addFavoriteButton.setOnClickListener {
            val intent = Intent(this, CollectorAddFavoritePerformers::class.java)
            intent.putExtra("collectorId", collectorId)
            startActivity(intent)
        }

        viewModel = ViewModelProvider(this, CollectorDetailViewModel.Factory(this.application, collectorId)).get(
            CollectorDetailViewModel::class.java)

        viewModel.collector.observe(this, Observer<Collector> { collector ->

            binding.collector = collector

        })

        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        val appBar: MaterialToolbar = findViewById(R.id.topAppBar)

        appBar.setNavigationOnClickListener {
            // Handle navigation icon press
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("setCollectors", "true")
            startActivity(intent)
        }

        val fragment = FavoritePerformersFragment()
        val bundle = Bundle()
        bundle.putString("collectorId", collectorId)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fp_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshDataFromNetwork()
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}


