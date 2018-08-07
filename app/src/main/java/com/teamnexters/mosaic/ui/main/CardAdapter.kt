package com.teamnexters.mosaic.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamnexters.mosaic.databinding.ViewCardBinding

class CardAdapter : RecyclerView.Adapter<CardViewHolder>() {

    private val items = mutableListOf<CardLooknFeel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ViewCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<CardLooknFeel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<CardLooknFeel>) {
        val insertPosition = items.size
        this.items.addAll(items)
        notifyItemInserted(insertPosition)
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

}