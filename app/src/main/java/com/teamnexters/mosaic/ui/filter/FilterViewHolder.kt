package com.teamnexters.mosaic.ui.filter

import android.support.v7.widget.RecyclerView
import com.teamnexters.mosaic.databinding.ViewFilterBinding

internal class FilterViewHolder(private val binding: ViewFilterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(filterData: FilterData) {
        binding.data = filterData
    }

}