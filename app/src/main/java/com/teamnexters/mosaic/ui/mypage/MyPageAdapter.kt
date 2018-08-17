package com.teamnexters.mosaic.ui.mypage

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamnexters.mosaic.databinding.ViewMyPageBinding

internal class MyPageAdapter : RecyclerView.Adapter<MyPageViewHolder>() {

    interface MyPageRowClickListener {
        fun onRowClick(myPageRowData: MyPageRowData)
    }

    private val items = mutableListOf<MyPageRowData>()

    private var myPageRowClickListener: MyPageRowClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder {
        val binding = ViewMyPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = MyPageViewHolder(binding)

        binding.clickListener = View.OnClickListener {
            myPageRowClickListener?.onRowClick(items[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: MyPageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<MyPageRowData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setMyPageRowClickListener(myPageRowClickListener: MyPageRowClickListener) {
        this.myPageRowClickListener = myPageRowClickListener
    }

}