package com.teamnexters.mosaic.ui.detail

import android.app.Activity
import android.content.Context
import android.support.v4.view.ViewPager
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
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.ui.widget.CustomTextView
import com.teamnexters.mosaic.utils.extension.toPx
import com.teamnexters.mosaic.utils.extension.toast


class DetailReplyAdapter internal constructor(val activity : Activity, val myUuid : String, val cardData : ScriptResponse) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        val REPLY_HEADER = 0
        val REPLY_TYPE = 1
        val REREPLY_TYPE = 2
        val REPLY_EMPTY = 3
    }

    internal val replyList = ArrayList<ReplyResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder{
        lateinit var viewHolder : RecyclerView.ViewHolder

        if(viewType == REPLY_HEADER){
            viewHolder = ReplyHeaderViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.view_reply_header, parent, false), activity)
        } else if(viewType == REPLY_TYPE){
            viewHolder = ReplyViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.view_reply, parent, false), activity, myUuid)
        } else if(viewType == REREPLY_TYPE) {
            viewHolder = RereplyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rereply, parent, false), activity, myUuid)
        }else {
            viewHolder = ReplyEmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_reply_empty, parent, false))
        }

        return viewHolder
    }

    internal fun addReplyList(replyList : ArrayList<ReplyResponse>){
        this.replyList.addAll(replyList)

        cardData.replies = replyList.size

        notifyDataSetChanged()
    }

    internal fun addReply(replyResponse: ReplyResponse){
        replyList.add(replyResponse)

        cardData.replies = replyList.size

        notifyDataSetChanged()
    }

    internal fun addReply(position : Int, replyResponse: ReplyResponse){
        replyList.add(position, replyResponse)

        cardData.replies = replyList.size

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        var size = 0
        if(replyList.size  == 0 ){
            size = replyList.size + 2
        }else{
            size = replyList.size + 1
        }

        return size
    }

    override fun getItemViewType(position: Int): Int {
        var type = -1

        if(replyList.size == 0){
            if(position == 0) type = REPLY_HEADER else type = REPLY_EMPTY
        } else{
            if(position == 0){
                type = REPLY_HEADER
            }else if(replyList.get(position-1).depth == 0 ){
                type = REPLY_TYPE
            }else{
                type = REREPLY_TYPE
            }
        }

        return type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is ReplyViewHolder -> {
                holder.setData(replyList.get(position-1))

                holder.replyWriteRereply.setOnClickListener { if(activity is DetailActivity) activity.setEditTextRereply(holder.replyDetailData) }
            }
            is RereplyViewHolder -> {
                holder.setData(replyList.get(position-1))
            }
            is ReplyHeaderViewHolder -> {
                holder.setData(cardData)
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

    class ReplyEmptyViewHolder internal constructor(itemView :View) : RecyclerView.ViewHolder(itemView){

    }


    class ReplyHeaderViewHolder internal constructor(itemView :View, val context: Context) : RecyclerView.ViewHolder(itemView){
        val imageViewPager = itemView.findViewById<ViewPager>(R.id.image_view_pager)
        val contentCategory = itemView.findViewById<CustomTextView>(R.id.content_category)
        val content = itemView.findViewById<CustomTextView>(R.id.content)
        val writeTime = itemView.findViewById<AppCompatTextView>(R.id.write_time)
        val replyCount = itemView.findViewById<AppCompatTextView>(R.id.reply_count)
        val indicatorLayout = itemView.findViewById<IndicatorLayout>(R.id.indicator_layout)
        val noReplyBottomLine = itemView.findViewById<View>(R.id.no_reply_bottom_line)

        init {
            initViewPager()

            imageViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) { indicatorLayout.onSelected(position) }
                override fun onPageScrollStateChanged(state: Int) {}
            })
        }

        internal fun setData(cardData : ScriptResponse){
            if(cardData.imgUrls.size != 0) loadViewPager(cardData.imgUrls)

            contentCategory.text = "${cardData.category.name}${cardData.category.emoji}"
            content.text = cardData.content
            replyCount.text = cardData.replies.toString()
            writeTime.text = cardData.getDate()

            if(cardData.replies == 0){
                noReplyBottomLine.visibility = VISIBLE
            }else{
                noReplyBottomLine.visibility = GONE
            }
        }


        fun initViewPager() { imageViewPager.adapter = DetailImagePagerAdapter(context) }

        fun loadViewPager(imageList: List<String>){
            when(imageViewPager.adapter){is DetailImagePagerAdapter -> (imageViewPager.adapter as DetailImagePagerAdapter).addImageList(imageList as ArrayList<String>) }

            initIndicator(imageList.size, 6.toPx)

            imageViewPager.visibility = View.VISIBLE
            indicatorLayout.visibility = View.VISIBLE
        }

        fun initIndicator(num: Int, spacing: Int) {
            indicatorLayout.removeAllIndicator()
            indicatorLayout.spacing = spacing

            for (i in 1..num) {
                indicatorLayout.addIndicator(createIndicatorView())
            }

            indicatorLayout.onSelected(0)
        }
        fun createIndicatorView(): View {
            val view = View(context)
            view.setBackgroundResource(R.drawable.detail_image_dot_indicator)
            val layoutParams = ViewGroup.LayoutParams(5.toPx, 5.toPx)
            view.layoutParams = layoutParams

            return view
        }
    }
}
