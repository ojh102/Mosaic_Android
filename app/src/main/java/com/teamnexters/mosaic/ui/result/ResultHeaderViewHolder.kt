package com.teamnexters.mosaic.ui.result

import android.support.v7.widget.RecyclerView
import com.teamnexters.mosaic.databinding.ViewResultHeaderBinding

class ResultHeaderViewHolder(
        private val viewResultHeaderBinding: ViewResultHeaderBinding

) : RecyclerView.ViewHolder(viewResultHeaderBinding.root) {

    fun bind(sizeOfResult: Int) {
        viewResultHeaderBinding.sizeOfResult = sizeOfResult
    }
}