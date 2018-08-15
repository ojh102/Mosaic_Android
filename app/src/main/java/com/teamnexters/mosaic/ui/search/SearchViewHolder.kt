package com.teamnexters.mosaic.ui.search

import android.support.v7.widget.RecyclerView
import com.teamnexters.mosaic.data.local.model.Keyword
import com.teamnexters.mosaic.databinding.ViewSearchBinding


internal class SearchViewHolder(private val viewSearchBinding: ViewSearchBinding) : RecyclerView.ViewHolder(viewSearchBinding.root) {

    fun bind(keyword: Keyword) {
        viewSearchBinding.keyword = keyword
    }
}