package com.teamnexters.mosaic.ui.detail

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.widget.EditText
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityDetailBinding
import com.teamnexters.mosaic.databinding.ActivitySplashBinding
import com.teamnexters.mosaic.utils.Navigator
import io.reactivex.rxkotlin.subscribeBy

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    companion object {
        val CONTENT_ID = "contentId"
        val UNIVERSITY_NAME = "universityName"
        val USER_ID = "userId"
        val CONTENT_TITLE = "contentTitle"
        val CONTENT = "content"
        val WRITE_TIME = "writeTime"
        val REPLY_COUNT = "replyCount"
    }

    var mUuid: Int = 0

    val mCloseButton by lazy { findViewById<AppCompatImageView>(R.id.close_btn) }
    val mScrapButton by lazy { findViewById<AppCompatImageView>(R.id.scrap_btn) }
    val mUniversityName by lazy { findViewById<AppCompatTextView>(R.id.university_name) }
    val mUserId by lazy { findViewById<AppCompatTextView>(R.id.user_id) }
    val mImageViewPager by lazy { findViewById<ViewPager>(R.id.image_view_pager) }
    val mContentTitle by lazy { findViewById<AppCompatTextView>(R.id.content_title) }
    val mContent by lazy { findViewById<AppCompatTextView>(R.id.content) }
    val mWriteTime by lazy { findViewById<AppCompatTextView>(R.id.write_time) }
    val mReplyCount by lazy { findViewById<AppCompatTextView>(R.id.reply_count) }
    val mReplyRecyclerview by lazy { findViewById<RecyclerView>(R.id.reply_recyclerview) }
    val mAddReplyImage by lazy { findViewById<AppCompatImageView>(R.id.add_reply_image) }
    val mWriteReplyEdittext by lazy { findViewById<EditText>(R.id.write_reply_edittext) }
    val mSendReply by lazy { findViewById<AppCompatImageView>(R.id.send_reply) }


    override fun getLayoutRes() = R.layout.activity_detail

    override fun getViewModelClass() = DetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout(intent)
        initListener()
    }

    fun initLayout(intent: Intent) {
        mUuid = intent.getIntExtra(CONTENT_ID, 0)
        /*mUniversityName.text = intent.getStringExtra(UNIVERSITY_NAME)
        mUserId.text = intent.getStringExtra(USER_ID)
        mContentTitle.text = intent.getStringExtra(CONTENT_TITLE)
        mContent.text = intent.getStringExtra(CONTENT)
        mWriteTime.text = intent.getStringExtra(WRITE_TIME)
        mReplyCount.text = intent.getStringExtra(REPLY_COUNT)
        mContent.text = intent.getStringExtra(CONTENT)*/


        initData(mUuid)


        mReplyRecyclerview.layoutManager = LinearLayoutManager(this)
        //adapter = DetailAdapter()
    }

    fun initData(contentId: Int) {
        //서버로 contentId를 쏴라!!!
    }

    fun initListener() {
        mCloseButton.setOnClickListener { finish() }
        mScrapButton.setOnClickListener { scripContent() }
    }

    fun scripContent() {
        //서버로 이 코드를
    }
}
