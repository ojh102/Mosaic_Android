package com.teamnexters.mosaic.ui.detail

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


class DetailReplyAdapter(val context : Context, val scriptUuid : String) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        val REPLY_TYPE = 0
        val REREPLY_TYPE = 1
    }

    internal var replyList = ArrayList<ReplyResponse>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder{
        lateinit var view : View

        if(viewType == REPLY_TYPE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_reply, parent, false);

            return ReplyViewHolder(view, context, scriptUuid)
        } else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rereply, parent, false);

            return RereplyViewHolder(view, context, scriptUuid)
        }
    }

    override fun getItemCount(): Int = replyList.size

    //override fun getItemViewType(position: Int): Int = replyList.get(position).type
    override fun getItemViewType(position: Int): Int = REPLY_TYPE //타입을 모르겟땅

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as ReplyAbstractViewHolder).setData(replyList.get(position))


    abstract class ReplyAbstractViewHolder(itemView :View, val context: Context, val scriptUuid : String) : RecyclerView.ViewHolder(itemView) {
        internal abstract fun setData(replyDetailData: ReplyResponse)
    }

    internal fun addReply(replyResponse: ReplyResponse){
        replyList.add(replyResponse)
        notifyDataSetChanged()
    }

    class ReplyViewHolder(itemView :View, context: Context, scriptUuid : String) : ReplyAbstractViewHolder(itemView, context, scriptUuid) {
        val universityImage by lazy { itemView.findViewById<AppCompatImageView>(R.id.university_image) }
        val universityName by lazy { itemView.findViewById<AppCompatTextView>(R.id.university_name) }
        val userId by lazy { itemView.findViewById<AppCompatTextView>(R.id.user_id) }
        val myBadge by lazy { itemView.findViewById<AppCompatTextView>(R.id.my_badge) }
        val replyImage by lazy { itemView.findViewById<AppCompatImageView>(R.id.reply_image) }
        val replyContent by lazy { itemView.findViewById<CustomTextView>(R.id.reply_content) }
        val replyWriteTime by lazy { itemView.findViewById<AppCompatTextView>(R.id.reply_write_time) }
        val replyWriteRereply by lazy { itemView.findViewById<AppCompatTextView>(R.id.reply_write_rereply) }

        override fun setData(replyDetailData: ReplyResponse){
            Glide.with(context).load(replyDetailData.writer.university.imgUrl).into(universityImage)
            universityName.setText(replyDetailData.writer.university.name)
            userId.setText(replyDetailData.writer.nick)
            if(replyDetailData.writer.uuid.equals(scriptUuid)) myBadge.visibility = VISIBLE else myBadge.visibility = GONE
            if(TextUtils.isEmpty(replyDetailData.imgUrl) == false) Glide.with(context).load(replyDetailData.imgUrl).into(replyImage) else replyImage.visibility = GONE
            replyContent.setText(replyDetailData.content)
            replyWriteTime.setText(replyDetailData.createdAt.toString())

            replyWriteRereply.setOnClickListener {
                context.toast("대댓글")
            }
        }
    }

    class RereplyViewHolder(itemView :View, context: Context, scriptUuid : String) : ReplyAbstractViewHolder(itemView, context, scriptUuid) {
        val universityImage by lazy { itemView.findViewById<AppCompatImageView>(R.id.university_image) }
        val universityName by lazy { itemView.findViewById<AppCompatTextView>(R.id.university_name) }
        val userId by lazy { itemView.findViewById<AppCompatTextView>(R.id.user_id) }
        val myBadge by lazy { itemView.findViewById<AppCompatTextView>(R.id.my_badge) }
        val rereplyImage by lazy { itemView.findViewById<AppCompatImageView>(R.id.rereply_image) }
        val replyContent by lazy { itemView.findViewById<CustomTextView>(R.id.rereply_content) }
        val replyWriteTime by lazy { itemView.findViewById<AppCompatTextView>(R.id.rereply_write_time) }

        override fun setData(replyDetailData: ReplyResponse){
            Glide.with(context).load(replyDetailData.writer.university.imgUrl).into(universityImage)
            universityName.setText(replyDetailData.writer.university.name)
            userId.setText(replyDetailData.writer.nick)
            if(replyDetailData.writer.uuid.equals(scriptUuid)) myBadge.visibility = VISIBLE else myBadge.visibility = GONE
            if(TextUtils.isEmpty(replyDetailData.imgUrl) == false) Glide.with(context).load(replyDetailData.imgUrl).into(rereplyImage) else rereplyImage.visibility = GONE
            replyContent.setText(Html.fromHtml("<font color=#ff573d>재환에게</font>  ${replyDetailData.content}"))
            replyWriteTime.setText(replyDetailData.createdAt.toString())
        }
    }
}
