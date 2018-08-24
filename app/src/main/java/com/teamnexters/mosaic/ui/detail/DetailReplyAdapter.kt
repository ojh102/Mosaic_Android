package com.teamnexters.mosaic.ui.detail

import android.app.Activity
import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.data.remote.model.ReplyResponse
import com.teamnexters.mosaic.ui.widget.CustomTextView
import com.teamnexters.mosaic.utils.extension.toast


class DetailReplyAdapter(val activity : Activity, val myUuid : String) : RecyclerView.Adapter<DetailReplyAdapter.ReplyAbstractViewHolder>(){
    companion object {
        val REPLY_TYPE = 0
        val REREPLY_TYPE = 1
    }

    internal val replyList = ArrayList<ReplyResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : DetailReplyAdapter.ReplyAbstractViewHolder{
        lateinit var viewHolder : ReplyAbstractViewHolder

        if(viewType == REPLY_TYPE){
            viewHolder = ReplyViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.view_reply, parent, false), activity, myUuid)
        } else{
            viewHolder = RereplyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rereply, parent, false), activity, myUuid)
        }

        return viewHolder
    }

    internal fun addReplyList(replyList : ArrayList<ReplyResponse>){
        this.replyList.addAll(replyList)
        notifyDataSetChanged()
    }

    internal fun addReply(replyResponse: ReplyResponse){
        replyList.add(replyResponse)
        notifyDataSetChanged()
    }

    internal fun addReply(position : Int, replyResponse: ReplyResponse){
        replyList.add(position, replyResponse)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = replyList.size

    override fun getItemViewType(position: Int): Int = if(replyList.get(position).depth == 0 ) REPLY_TYPE else REREPLY_TYPE

    override fun onBindViewHolder(holder: DetailReplyAdapter.ReplyAbstractViewHolder, position: Int) {
        holder.setData(replyList.get(position))

        when(holder){
            is ReplyViewHolder -> {
                holder.replyWriteRereply.setOnClickListener { if(activity is DetailActivity) activity.setEditTextRereply(holder.replyDetailData) }
            }
        }
    }

    abstract class ReplyAbstractViewHolder(itemView :View, val context: Context, val myUuid : String) : RecyclerView.ViewHolder(itemView) {
        internal var replyDetailData : ReplyResponse? = null

        internal abstract fun setData(replyDetailData: ReplyResponse)
    }

    class ReplyViewHolder(itemView :View, context: Context, myUuid : String) : ReplyAbstractViewHolder(itemView, context, myUuid) {
        val universityImage = itemView.findViewById<AppCompatImageView>(R.id.university_image)
        val universityName = itemView.findViewById<AppCompatTextView>(R.id.university_name)
        val userId = itemView.findViewById<AppCompatTextView>(R.id.user_id)
        val myBadge = itemView.findViewById<AppCompatTextView>(R.id.my_badge)
        val replyImage = itemView.findViewById<AppCompatImageView>(R.id.reply_image)
        val replyContent = itemView.findViewById<CustomTextView>(R.id.reply_content)
        val replyWriteTime = itemView.findViewById<AppCompatTextView>(R.id.reply_write_time)
        val replyWriteRereply = itemView.findViewById<AppCompatTextView>(R.id.reply_write_rereply)

        override fun setData(replyDetailData: ReplyResponse){
            this.replyDetailData = replyDetailData

            Glide.with(context).load(replyDetailData.writer.university.imgUrl).into(universityImage)
            universityName.setText(replyDetailData.writer.university.name)
            userId.setText(replyDetailData.writer.nick)
            if(replyDetailData.writer.uuid.equals(myUuid)) myBadge.visibility = VISIBLE else myBadge.visibility = GONE
            if(TextUtils.isEmpty(replyDetailData.imgUrl) == false) Glide.with(context).load(replyDetailData.imgUrl).into(replyImage) else replyImage.visibility = GONE
            replyContent.setText(replyDetailData.content)
            replyWriteTime.setText(replyDetailData.getDate())
        }
    }

    class RereplyViewHolder(itemView :View, context: Context, myUuid : String) : ReplyAbstractViewHolder(itemView, context, myUuid) {
        val universityImage = itemView.findViewById<AppCompatImageView>(R.id.university_image)
        val universityName = itemView.findViewById<AppCompatTextView>(R.id.university_name)
        val userId = itemView.findViewById<AppCompatTextView>(R.id.user_id)
        val myBadge = itemView.findViewById<AppCompatTextView>(R.id.my_badge)
        val rereplyImage = itemView.findViewById<AppCompatImageView>(R.id.rereply_image)
        val rereplyContent = itemView.findViewById<CustomTextView>(R.id.rereply_content)
        val rereplyWriteTime = itemView.findViewById<AppCompatTextView>(R.id.rereply_write_time)

        override fun setData(replyDetailData: ReplyResponse){
            this.replyDetailData = replyDetailData;

            Glide.with(context).load(replyDetailData.writer.university.imgUrl).into(universityImage)
            universityName.setText(replyDetailData.writer.university.name)
            userId.setText(replyDetailData.writer.nick)
            if(replyDetailData.writer.uuid.equals(myUuid)) myBadge.visibility = VISIBLE else myBadge.visibility = GONE
            if(TextUtils.isEmpty(replyDetailData.imgUrl) == false) Glide.with(context).load(replyDetailData.imgUrl).into(rereplyImage) else rereplyImage.visibility = GONE
            rereplyContent.setText(Html.fromHtml("<font color=#ff573d>${replyDetailData.upperReplyUuid}</font>  ${replyDetailData.content}"))
            rereplyWriteTime.setText(replyDetailData.getDate())
        }
    }
}
