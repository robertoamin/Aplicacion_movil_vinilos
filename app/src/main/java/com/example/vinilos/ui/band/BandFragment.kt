package com.example.vinilos.ui.band

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
import com.example.vinilos.databinding.FragmentAlbumItemListBinding
import com.example.vinilos.databinding.FragmentBandItemListBinding
import com.example.vinilos.models.Album
import com.example.vinilos.models.Band
import com.example.vinilos.ui.adapters.AlbumsAdapter
import com.example.vinilos.ui.adapters.BandsAdapter
import com.example.vinilos.viewmodels.AlbumViewModel
import com.example.vinilos.viewmodels.BandViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [BandFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BandFragment : Fragment() {

    private var _binding: FragmentBandItemListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: BandViewModel
    private var viewModelAdapter: BandsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBandItemListBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = BandsAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.list
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = "Bands"
        viewModel = ViewModelProvider(this, BandViewModel.Factory(activity.application)).get(BandViewModel::class.java)
        viewModel.albums.observe(viewLifecycleOwner, Observer<List<Band>> {
            it.apply {
                viewModelAdapter!!.bands = this
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

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

}