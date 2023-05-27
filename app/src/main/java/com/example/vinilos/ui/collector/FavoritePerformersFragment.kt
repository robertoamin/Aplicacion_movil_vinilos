package com.example.vinilos.ui.collector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentCollectorItemListBinding
import com.example.vinilos.models.Band
import com.example.vinilos.ui.adapters.FavoritePerformersAdapter
import com.example.vinilos.viewmodels.FavoritePerformersViewModel


class FavoritePerformersFragment() : Fragment() {
    private var _binding: FragmentCollectorItemListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: FavoritePerformersViewModel
    private var viewModelAdapter: FavoritePerformersAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectorItemListBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = FavoritePerformersAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.list
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(MarginItemDecoration(40))
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_collector)
        viewModel = ViewModelProvider(
            this, FavoritePerformersViewModel.Factory(
                activity.application, arguments?.getString("collectorId").toString())).get(
            FavoritePerformersViewModel::class.java)
        viewModel.favoritePerformers.observe(viewLifecycleOwner, Observer<List<Band>> {
            it.apply {
                viewModelAdapter!!.favoritePerformers = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshDataFromNetwork()
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}