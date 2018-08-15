package com.teamnexters.mosaic.ui.result

import android.support.v7.widget.RecyclerView
import com.teamnexters.mosaic.databinding.ViewResultBinding
import com.teamnexters.mosaic.ui.main.CardLooknFeel


internal class ResultViewHolder(
        private val viewSearchResultBinding: ViewResultBinding

) : RecyclerView.ViewHolder(viewSearchResultBinding.root) {

    fun bind(card: CardLooknFeel) {
        viewSearchResultBinding.card = card
    }

}