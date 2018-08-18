package com.teamnexters.mosaic.ui.result

import android.support.v7.widget.RecyclerView
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.databinding.ViewResultBinding


internal class ResultViewHolder(
        private val viewSearchResultBinding: ViewResultBinding

) : RecyclerView.ViewHolder(viewSearchResultBinding.root) {

    fun bind(card: ScriptResponse) {
        viewSearchResultBinding.card = card
    }

}