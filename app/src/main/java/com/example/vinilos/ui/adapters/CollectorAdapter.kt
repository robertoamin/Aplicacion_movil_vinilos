package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentCollectorItemBinding

import com.example.vinilos.models.Collector

class CollectorAdapter : RecyclerView.Adapter<CollectorAdapter.BandViewHolder>() {

    var collectors: List<Collector> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorAdapter.BandViewHolder {
        val withDataBinding: FragmentCollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorAdapter.BandViewHolder.LAYOUT,
            parent,
            false
        )
        return CollectorAdapter.BandViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: BandViewHolder, position: Int) {
        val collector = collectors[position]

        holder.viewDataBinding.also {
            it.collector= collector
        }

        /*Glide.with(holder.itemView)
            .load(collector.image)
            .into(holder.viewDataBinding.imageArtist)*/

//        holder.viewDataBinding.root.setOnClickListener {
//            val action = AlbumFragmentDirections.actionAlbumFragmentToCommentFragment(albums[position].albumId)
//            // Navigate using that action
//            holder.viewDataBinding.root.findNavController().navigate(action)
//        }
    }


    override fun getItemCount(): Int {
        return collectors.size
    }
    class BandViewHolder(val viewDataBinding: FragmentCollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.fragment_collector_item
        }
    }

}
