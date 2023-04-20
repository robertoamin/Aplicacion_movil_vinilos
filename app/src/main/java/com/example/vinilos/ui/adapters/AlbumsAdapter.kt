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
import com.example.vinilos.MainActivity
import com.example.vinilos.models.Album
import com.example.vinilos.viewmodels.AlbumViewModel
import com.example.vinilos.R

import com.example.vinilos.databinding.FragmentAlbumItemListBinding
import com.example.vinilos.databinding.FragmentAlbumItemBinding
import com.example.vinilos.ui.album.AlbumDetailActivity

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    var albums: List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: FragmentAlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false
        )
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]

        holder.viewDataBinding.also {
            it.album = album
        }

        Glide.with(holder.itemView)
            .load(album.cover)
            .into(holder.viewDataBinding.imageCover)
          holder.viewDataBinding.card.setOnClickListener {
              val context = holder.viewDataBinding.root.context
              val intent = Intent(context, AlbumDetailActivity::class.java)
              intent.putExtra("albumId", album.albumId.toString())
              context.startActivity(intent)
          }

//        holder.viewDataBinding.root.setOnClickListener {
//            val action = AlbumFragmentDirections.actionAlbumFragmentToCommentFragment(albums[position].albumId)
//            // Navigate using that action
//            holder.viewDataBinding.root.findNavController().navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    class AlbumViewHolder(val viewDataBinding: FragmentAlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.fragment_album_item
        }
    }
}