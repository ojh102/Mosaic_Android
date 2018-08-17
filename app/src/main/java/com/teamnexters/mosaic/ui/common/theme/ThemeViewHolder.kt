package com.teamnexters.mosaic.ui.common.theme

import android.support.v7.widget.RecyclerView
import com.teamnexters.mosaic.databinding.ViewThemeBinding

internal class ThemeViewHolder(private val binding: ViewThemeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(themeData: ThemeData) {
        binding.data = themeData
    }

}