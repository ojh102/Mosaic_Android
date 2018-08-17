package com.teamnexters.mosaic.ui.common.theme

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamnexters.mosaic.databinding.ViewThemeBinding
import com.teamnexters.mosaic.ui.Screen

internal class ThemeAdapter (
        private val compatibleThemeAction: CompatibleThemeAction

): RecyclerView.Adapter<ThemeViewHolder>() {

    interface Factory {
        fun newInstance(screen: Screen): ThemeAdapter
    }

    interface ThemeClickListener {
        fun onClickFilter()
    }

    private val items = mutableListOf<ThemeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val binding = ViewThemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ThemeViewHolder(binding)

        compatibleThemeAction.bindSelector(binding)

        binding.clickListener = object : ThemeClickListener {
            override fun onClickFilter() {
                compatibleThemeAction.onClickTheme(this@ThemeAdapter, viewHolder, items)
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<ThemeData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getSelectedItems(): List<ThemeData> {
        return items.filter { it.selected }
    }

}