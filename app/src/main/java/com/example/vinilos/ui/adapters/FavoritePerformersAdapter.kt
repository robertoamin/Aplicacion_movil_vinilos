package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentBandItemBinding
import com.example.vinilos.models.Band

class FavoritePerformersAdapter : RecyclerView.Adapter<FavoritePerformersAdapter.FavoritePerformersViewHolder>() {

    var favoritePerformers: List<Band> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePerformersViewHolder {
        val withDataBinding: FragmentBandItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            FavoritePerformersViewHolder.LAYOUT,
            parent,
            false
        )
        return FavoritePerformersViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: FavoritePerformersViewHolder, position: Int) {
        val band = favoritePerformers[position]

        holder.viewDataBinding.also {
            it.band= band
        }

        Glide.with(holder.itemView)
            .load(band.image)
            .into(holder.viewDataBinding.imageArtist)

    }


    override fun getItemCount(): Int {
        return favoritePerformers.size
    }
    class FavoritePerformersViewHolder(val viewDataBinding: FragmentBandItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.fragment_band_item
        }
    }

}

