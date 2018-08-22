package com.teamnexters.mosaic.ui.result

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.databinding.ViewResultBinding
import com.teamnexters.mosaic.databinding.ViewResultHeaderBinding
import com.teamnexters.mosaic.ui.Screen


internal class ResultAdapter(private val screen: Screen) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface Factory {
        fun newInstance(screen: Screen): ResultAdapter
    }

    companion object {
        const val SIZE_OF_HEADER = 1
        const val TYPE_HEADER = 0
        const val TYPE_RESULT = 1
    }

    private val items = mutableListOf<ScriptResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                ResultHeaderViewHolder(
                        ViewResultHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            TYPE_RESULT -> {
                val binding = ViewResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                binding.screen = screen

                ResultViewHolder(binding)
            }
            else -> throw RuntimeException("invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_HEADER -> {
                if (holder is ResultHeaderViewHolder) {
                    holder.bind(items.size)
                }
            }
            TYPE_RESULT -> {
                if (holder is ResultViewHolder) {
                    holder.bind(items[position - SIZE_OF_HEADER])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size + SIZE_OF_HEADER
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
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

    fun unScrap(scarpUuid: String, scraped: Boolean) {
        val item: ScriptResponse? = items.firstOrNull { it.uuid == scarpUuid && !scraped }

        item?.let {
            val deletePosition = items.indexOf(it) + 1
            items.remove(it)
            notifyItemRemoved(deletePosition)
            notifyItemChanged(0)
        }
    }

    fun delete(uuId: String) {
        val item: ScriptResponse? = items.firstOrNull { it.uuid == uuId }

        item?.let {
            val deletePosition = items.indexOf(it) + 1
            items.remove(it)
            notifyItemRemoved(deletePosition)
            notifyItemChanged(0)
        }
    }

}