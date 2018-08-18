package com.teamnexters.mosaic.ui.main

import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.databinding.ViewCardBinding

internal class MosaicStackViewHolder(val binding: ViewCardBinding) {
    fun bind(card: ScriptResponse) {
        binding.card = card
    }
}