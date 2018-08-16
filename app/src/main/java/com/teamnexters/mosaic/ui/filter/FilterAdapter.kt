package com.teamnexters.mosaic.ui.filter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamnexters.mosaic.databinding.ViewFilterBinding

internal class FilterAdapter : RecyclerView.Adapter<FilterViewHolder>() {

    private val items = mutableListOf<FilterData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val binding = ViewFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FilterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItmes(items: List<FilterData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}