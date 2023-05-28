package com.example.vinilos.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentCollectorItemBinding
import com.example.vinilos.models.Collector
import com.example.vinilos.ui.collector.CollectorDetailActivity

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

        holder.viewDataBinding.card.setOnClickListener {
            val context = holder.viewDataBinding.root.context
            val intent = Intent(context, CollectorDetailActivity::class.java)
            intent.putExtra("collectorId", collector.id.toString())
            context.startActivity(intent)
        }
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

