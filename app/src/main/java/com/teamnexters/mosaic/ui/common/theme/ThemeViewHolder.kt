package com.teamnexters.mosaic.ui.common.theme

import android.support.v7.widget.RecyclerView
import com.teamnexters.mosaic.data.remote.model.CategoryResponse
import com.teamnexters.mosaic.databinding.ViewThemeBinding

internal class ThemeViewHolder(private val binding: ViewThemeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(themeData: CategoryResponse) {
        binding.data = themeData
    }

}