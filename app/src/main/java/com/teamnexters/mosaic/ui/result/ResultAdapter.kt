package com.teamnexters.mosaic.ui.result

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.databinding.ViewResultBinding
import com.teamnexters.mosaic.databinding.ViewResultHeaderBinding


internal class ResultAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val SIZE_OF_HEADER = 1
        const val TYPE_HEADER = 0
        const val TYPE_RESULT = 1
    }

    private val items = mutableListOf<ScriptResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            TYPE_HEADER -> {
                return ResultHeaderViewHolder(
                        ViewResultHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            TYPE_RESULT -> {
                return ResultViewHolder(
                        ViewResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> throw RuntimeException("invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
            TYPE_HEADER -> {
                if(holder is ResultHeaderViewHolder) {
                    holder.bind(items.size)
                }
            }
            TYPE_RESULT -> {
                if(holder is ResultViewHolder) {
                    holder.bind(items[position - SIZE_OF_HEADER])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size + SIZE_OF_HEADER
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) {
            TYPE_HEADER
        } else {
            TYPE_RESULT
        }
    }

    fun setItems(items: List<ScriptResponse>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}