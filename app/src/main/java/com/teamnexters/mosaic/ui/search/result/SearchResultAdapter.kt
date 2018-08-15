package com.teamnexters.mosaic.ui.search.result

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamnexters.mosaic.databinding.ViewSearchResultBinding


internal class SearchResultAdapter : RecyclerView.Adapter<SearchResultViewHolder>() {

    private val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = ViewSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return items.size
    }

}