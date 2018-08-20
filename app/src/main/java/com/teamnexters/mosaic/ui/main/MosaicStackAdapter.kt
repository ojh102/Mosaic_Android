package com.teamnexters.mosaic.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.databinding.ViewCardBinding

internal class MosaicStackAdapter(context: Context) : ArrayAdapter<ScriptResponse>(context, 0) {

    interface ScarpClickListener {
        fun onClick(binding: ViewCardBinding, items: List<ScriptResponse>)
    }

    private var scrapClickListener: ScarpClickListener? = null

    private val items = mutableListOf<ScriptResponse>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var contentView = convertView

        val scriptResponse = items[position]

        val mosaicStackViewHolder = if(contentView == null) {
            val binding = ViewCardBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

            MosaicStackViewHolder(binding).also { viewHolder ->
                binding.root.tag = viewHolder
                binding.scrapClickListener = View.OnClickListener { v ->
                    scrapClickListener?.onClick(binding, items)
                }

                contentView = binding.root
            }
        } else {
            contentView?.tag as MosaicStackViewHolder
        }

        mosaicStackViewHolder.bind(scriptResponse)

        return contentView!!
    }

    fun setItems(items: List<ScriptResponse>) {
        this.items.clear()
        this.items.addAll(items)
        clear()
        addAll(items)
        notifyDataSetChanged()
    }

    fun setScrapClickListener(scrapClickListener: ScarpClickListener) {
        this.scrapClickListener = scrapClickListener
    }

    fun setScrap(scriptUuid: String, scrap: Boolean) {
        val selectedItem = items.first { it.uuid == scriptUuid }
        selectedItem.scrap = scrap

        setItems(items.toList())
    }

    fun getItems(): List<ScriptResponse> {
        return items
    }

}