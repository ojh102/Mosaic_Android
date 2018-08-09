package com.teamnexters.mosaic.ui.main

import com.teamnexters.mosaic.databinding.ViewCardBinding

class MosaicStackViewHolder(val binding: ViewCardBinding) {
    fun bind(card: CardLooknFeel) {
        binding.card = card
    }
}