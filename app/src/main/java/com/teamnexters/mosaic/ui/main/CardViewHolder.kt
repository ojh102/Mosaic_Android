package com.teamnexters.mosaic.ui.main

import android.support.v7.widget.RecyclerView
import com.teamnexters.mosaic.databinding.ViewCardBinding

class CardViewHolder(val binding: ViewCardBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(card: CardLooknFeel) {
        binding.card = card
    }
}