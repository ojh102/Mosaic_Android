package com.teamnexters.mosaic.ui.common.theme

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamnexters.mosaic.data.remote.model.CategoryResponse
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

    var onClickItem: (item: List<CategoryResponse>) -> Unit = {}

    private val items = mutableListOf<CategoryResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val binding = ViewThemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ThemeViewHolder(binding)

        compatibleThemeAction.bindSelector(binding)

        binding.clickListener = object : ThemeClickListener {
            override fun onClickFilter() {
                compatibleThemeAction.onClickTheme(this@ThemeAdapter, viewHolder, items)
                onClickItem(getSelectedItems())
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

    fun setItems(items: List<CategoryResponse>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getSelectedItems(): List<CategoryResponse> {
        return items.filter { it.selected }
    }

}