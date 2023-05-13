package com.example.vinilos.ui.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos.models.Band
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentBandItemBinding
import com.example.vinilos.ui.album.AlbumDetailActivity
import com.example.vinilos.ui.band.BandDetailActivity

class BandsAdapter : RecyclerView.Adapter<BandsAdapter.BandViewHolder>() {

    var bands: List<Band> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandsAdapter.BandViewHolder {
        val withDataBinding: FragmentBandItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            BandsAdapter.BandViewHolder.LAYOUT,
            parent,
            false
        )
        return BandsAdapter.BandViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: BandViewHolder, position: Int) {
        val band = bands[position]

        holder.viewDataBinding.also {
            it.band= band
        }

        Glide.with(holder.itemView)
            .load(band.image)
            .into(holder.viewDataBinding.imageArtist)

        holder.viewDataBinding.card.setOnClickListener {
            val context = holder.viewDataBinding.root.context
            val intent = Intent(context, BandDetailActivity::class.java)
            intent.putExtra("bandId", band.bandId.toString())
            context.startActivity(intent)
        }

//        holder.viewDataBinding.root.setOnClickListener {
//            val action = AlbumFragmentDirections.actionAlbumFragmentToCommentFragment(albums[position].albumId)
//            // Navigate using that action
//            holder.viewDataBinding.root.findNavController().navigate(action)
//        }
    }


    override fun getItemCount(): Int {
        return bands.size
    }
    class BandViewHolder(val viewDataBinding: FragmentBandItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.fragment_band_item
        }
    }

}

