package com.teamnexters.mosaic.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamnexters.mosaic.data.local.model.Keyword
import com.teamnexters.mosaic.databinding.ViewSearchBinding


internal class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {

    private val items = mutableListOf<Keyword>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ViewSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Keyword>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}