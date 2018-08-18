package com.teamnexters.mosaic.ui.mypage

import android.support.v7.widget.RecyclerView
import com.teamnexters.mosaic.databinding.ViewMyPageBinding

internal class MyPageViewHolder(
        private val viewMyPageBinding: ViewMyPageBinding

) : RecyclerView.ViewHolder(viewMyPageBinding.root) {

    fun bind(myPageRowData: MyPageRowData) {
        viewMyPageBinding.data = myPageRowData
    }

}