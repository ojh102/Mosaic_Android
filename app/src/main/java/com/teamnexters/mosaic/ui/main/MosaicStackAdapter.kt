package com.teamnexters.mosaic.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.teamnexters.mosaic.databinding.ViewCardBinding

class MosaicStackAdapter(context: Context) : ArrayAdapter<CardLooknFeel>(context, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var contentView = convertView

        val mosaicStackViewHolder = if(contentView == null) {
            val binding = ViewCardBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

            MosaicStackViewHolder(binding).also {
                binding.root.tag = it

                contentView = binding.root
            }
        } else {
            contentView?.tag as MosaicStackViewHolder
        }

        val cardLooknFeel = getItem(position)

        mosaicStackViewHolder.bind(cardLooknFeel)

        return contentView!!
    }

}