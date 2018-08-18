package com.teamnexters.mosaic.ui.detail

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.ui.detail.data.ReplyDetailData
import com.teamnexters.mosaic.ui.widget.CustomTextView
import com.teamnexters.mosaic.utils.extension.toast


class DetailReplyAdapter(val context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        val REPLY_TYPE = 0
        val REREPLY_TYPE = 1
    }

    var replyList = ArrayList<ReplyDetailData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder{
        lateinit var view : View

        if(viewType == REPLY_TYPE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_reply, parent, false);

            return ReplyViewHolder(view, context)
        } else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rereply, parent, false);

            return RereplyViewHolder(view, context)
        }
    }

    override fun getItemCount(): Int = replyList.size


    override fun getItemViewType(position: Int): Int = replyList.get(position).type


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as ReplyAbstractViewHolder).setData(replyList.get(position))



    abstract class ReplyAbstractViewHolder(itemView :View, val context: Context) : RecyclerView.ViewHolder(itemView) {
        abstract fun setData(replyDetailData: ReplyDetailData)
    }

    class ReplyViewHolder(itemView :View, context: Context) : ReplyAbstractViewHolder(itemView, context) {
        val universityImage by lazy { itemView.findViewById<AppCompatImageView>(R.id.university_image) }
        val universityName by lazy { itemView.findViewById<AppCompatTextView>(R.id.university_name) }
        val userId by lazy { itemView.findViewById<AppCompatTextView>(R.id.user_id) }
        val myBadge by lazy { itemView.findViewById<AppCompatTextView>(R.id.my_badge) }
        val replyContent by lazy { itemView.findViewById<CustomTextView>(R.id.reply_content) }
        val replyWriteTime by lazy { itemView.findViewById<AppCompatTextView>(R.id.reply_write_time) }
        val replyWriteRereply by lazy { itemView.findViewById<AppCompatTextView>(R.id.reply_write_rereply) }

        override fun setData(replyDetailData: ReplyDetailData){
            Glide.with(context).load(replyDetailData.universityImageUrl).into(universityImage)
            universityName.setText(replyDetailData.universityName)
            userId.setText(replyDetailData.userId)
            if(replyDetailData.isMy) myBadge.visibility = VISIBLE else myBadge.visibility = GONE
            replyContent.setText(replyDetailData.replyContent)
            replyWriteTime.setText(replyDetailData.replyWriteTime)

            replyWriteRereply.setOnClickListener {
                context.toast("대댓글")
            }
        }
    }

    class RereplyViewHolder(itemView :View, context: Context) : ReplyAbstractViewHolder(itemView, context) {
        val universityImage by lazy { itemView.findViewById<AppCompatImageView>(R.id.university_image) }
        val universityName by lazy { itemView.findViewById<AppCompatTextView>(R.id.university_name) }
        val userId by lazy { itemView.findViewById<AppCompatTextView>(R.id.user_id) }
        val myBadge by lazy { itemView.findViewById<AppCompatTextView>(R.id.my_badge) }
        val replyContent by lazy { itemView.findViewById<CustomTextView>(R.id.rereply_content) }
        val replyWriteTime by lazy { itemView.findViewById<AppCompatTextView>(R.id.rereply_write_time) }

        override fun setData(replyDetailData: ReplyDetailData){
            Glide.with(context).load(replyDetailData.universityImageUrl).into(universityImage)
            universityName.setText(replyDetailData.universityName)
            userId.setText(replyDetailData.userId)
            if(replyDetailData.isMy) myBadge.visibility = VISIBLE else myBadge.visibility = GONE
            replyContent.setText(Html.fromHtml("<font color=#ff573d>${replyDetailData.rereplyTo}</font>  ${replyDetailData.replyContent}"))
            replyWriteTime.setText(replyDetailData.replyWriteTime)
        }
    }
}
