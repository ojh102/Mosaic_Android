package com.teamnexters.mosaic.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.ui.detail.data.ReplyDetailData

/*

class DetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val REPLY_TYPE = 0
    val REREPLY_TYPE = 1

    val replyList = ArrayList<ReplyDetailData>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder{
        lateinit var viewHolder : RecyclerView.ViewHolder

        if(viewType == REPLY_TYPE){
            // create a new view
            val view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_text_view, parent, false);

            viewHolder = RecyclerView.ViewHolder(view)
        } else{

            val view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_text_view, parent, false);

            viewHolder = RecyclerView.ViewHolder(view);
        }

        return viewHolder;

    }

    override fun getItemCount(): Int {
        var count = 0

        count += replyList.count()

        for(reply in replyList){
            count += reply.rereplyList.count()
        }

        return count
    }

    override fun getItemViewType(position: Int): Int {
        var count = 0

        for(reply in replyList){
            if(count == position) return REPLY_TYPE
            count++
            for(rereply in reply.rereplyList){
                if(count == position) return REREPLY_TYPE
                count++
            }
        }

        return count
    }

    override fun onBindViewHolder(holder:, position: Int) {

    }
}
*/
