package com.teamnexters.mosaic.ui.detail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityDetailBinding
import com.teamnexters.mosaic.ui.detail.data.ReplyDetailData
import com.teamnexters.mosaic.utils.extension.toPx
import java.lang.Exception
import java.util.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.view.View.GONE
import android.view.View.VISIBLE
import com.teamnexters.mosaic.data.local.MosaicSharedPreferenceManager
import com.teamnexters.mosaic.data.remote.model.ReplyResponse
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.utils.extension.subscribeOf
import com.teamnexters.mosaic.utils.extension.toast
import javax.inject.Inject
import kotlin.collections.ArrayList
import android.provider.MediaStore
import com.teamnexters.mosaic.data.remote.model.UniversityResponse
import com.teamnexters.mosaic.data.remote.model.WriterResponse
import java.io.File


internal class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    companion object {
        const val DETAIL_INTENT_KEY = "detailIntentKey"
    }
    @Inject
    lateinit var sharedPreferenceManager: MosaicSharedPreferenceManager

    val uuid by lazy { sharedPreferenceManager.getString(MosaicSharedPreferenceManager.UUID,"") }

    val REQUEST_TAKE_ALBUM = 0
    val REQUEST_IMAGE_CROP = 1

    var scriptWriterUuid: String = ""
    var isScraped = false

    val closeButton by lazy { binding.closeBtn }
    val scrapButton by lazy { binding.scrapBtn }
    val universityName by lazy { binding.universityName }
    val userId by lazy { binding.userId }
    val imageViewPager by lazy { binding.imageViewPager }
    val contentCategory by lazy { binding.contentCategory }
    val content by lazy { binding.content }
    val writeTime by lazy { binding.writeTime }
    val replyCount by lazy { binding.replyCount }
    val replyRecyclerview by lazy { binding.replyRecyclerview }
    val addReplyImage by lazy { binding.addReplyImage }
    val writeReplyEditText by lazy { binding.writeReplyEdittext }
    val sendReply by lazy { binding.sendReply }
    val indicatorLayout by lazy { binding.indicatorLayout }
    val writeReplyImageLayout by lazy { binding.writeReplyImageLayout }
    val writeReplyImage by lazy { binding.writeReplyImage }
    val writeReplyImageCancel by lazy { binding.writeReplyImageCancel }
    val writeReplyLayout by lazy { binding.writeReplyLayout }
    val deleteCardLayout by lazy { binding.deleteCardLayout }
    val noReplyBottomLine by lazy { binding.noReplyBottomLine }
    val noReply by lazy { binding.noReply }

    var replyCroppedBitmap: Bitmap? = null

    override fun getLayoutRes() = R.layout.activity_detail

    override fun getViewModelClass() = DetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //댓글 리스트 뷰 초기화
        initRecylerView()

        //이미지 뷰 페이저 초기화
        initViewPager()

        //intent로 받은 값 초기화
        initIntentData()

        initListener()
        initLayout()
    }

    private fun initRecylerView() {
        replyRecyclerview.layoutManager = LinearLayoutManager(this)
        replyRecyclerview.adapter = DetailReplyAdapter(this)
        replyRecyclerview.isNestedScrollingEnabled = false
    }

    private fun initViewPager() { imageViewPager.adapter = DetailImagePagerAdapter(this) }

    private fun initIntentData() {
        //intent로 받은 정보 view에 할당
        val cardData : ScriptResponse = intent.getParcelableExtra(DETAIL_INTENT_KEY)

        scriptWriterUuid = cardData.uuid

        if(uuid.equals(scriptWriterUuid)){
            deleteCardLayout.visibility = VISIBLE
        }

        if(cardData.imgUrls.size != 0){
            loadViewPager(cardData.imgUrls)
        }

        universityName.text = cardData.writer.university.name
        userId.text = cardData.writer.username
        contentCategory.text = "${cardData.category.name}${cardData.category.emoji}"
        content.text = cardData.content
        replyCount.text = cardData.replies.toString()
        writeTime.text = cardData.created.toString()
        initScripContent(cardData.scrap)

        //intent로 전달 받은 uuid로 서버에 쏜다.
        initData(scriptWriterUuid)
    }

    fun initScripContent(scraped : Boolean) {
        isScraped = scraped

        if (scraped == true) {
            scrapButton.setImageDrawable(scrapButton.resources.getDrawable(R.drawable.ic_scrap_nol, null))
        } else {
            scrapButton.setImageDrawable(scrapButton.resources.getDrawable(R.drawable.ic_scrap_pr, null))
        }
    }

    fun initLayout() {
        sendReply.isEnabled = false
    }

    private fun loadViewPager(imageList: List<String>){
        (imageViewPager.adapter as DetailImagePagerAdapter).imageList = imageList as ArrayList<String>
        initIndicator(imageList.size, 6.toPx)

        imageViewPager.visibility = View.VISIBLE
        indicatorLayout.visibility = View.VISIBLE
    }


    private fun initIndicator(num: Int, spacing: Int) {
        indicatorLayout.removeAllIndicator()
        indicatorLayout.spacing = spacing

        for (i in 1..num) {
            indicatorLayout.addIndicator(createIndicatorView())
        }

        indicatorLayout.onSelected(0)
    }

    private fun createIndicatorView(): View {
        val view = View(this)
        view.setBackgroundResource(R.drawable.detail_image_dot_indicator)
        val layoutParams = ViewGroup.LayoutParams(5.toPx, 5.toPx)
        view.layoutParams = layoutParams

        return view
    }

    fun initListener() {
        closeButton.setOnClickListener { finish() }
        scrapButton.setOnClickListener {
            if (isScraped == true) {
                isScraped = !isScraped
                scrapButton.setImageDrawable(scrapButton.resources.getDrawable(R.drawable.ic_scrap_nol, null))
            } else {
                isScraped = !isScraped
                scrapButton.setImageDrawable(scrapButton.resources.getDrawable(R.drawable.ic_scrap_pr, null))
            }
        }
        imageViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                indicatorLayout.onSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        writeReplyEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                sendReply.isEnabled = s.toString().length != 0
            }

            override fun afterTextChanged(s: Editable) {}
        })

        addReplyImage.setOnClickListener {
            getAlbum()
        }

        sendReply.setOnClickListener {

        }

        writeReplyImageCancel.setOnClickListener {
            writeReplyImageLayout.visibility = GONE
            replyCroppedBitmap = null
        }
    }

    private fun getAlbum() {
        val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"

        startActivityForResult(intent, REQUEST_TAKE_ALBUM)
    }

    private fun cropImage(photoUri: Uri?) {
        if (photoUri != null) {
            val cropIntent = Intent("com.android.camera.action.CROP")

            cropIntent.setDataAndType(photoUri, "image/*")
            cropIntent.putExtra("aspectX", 1)
            cropIntent.putExtra("aspectY", 1)
            cropIntent.putExtra("crop", true)
            cropIntent.putExtra("return-data", true)
            startActivityForResult(cropIntent, REQUEST_IMAGE_CROP)
        }
    }

    fun initData(scriptUuid: String) {

        val tmpReplyList = ArrayList<ReplyResponse>()

        for (i in 0..10) {
            tmpReplyList.add(ReplyResponse(0,
                    "ㅁ",
                    "ㅁ",
                    0,
                    "댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당",
                    0,
                    "https://post-phinf.pstatic.net/20160811_167/1470891802502lQq1P_JPEG/google_co_kr_20160811_140117.jpg?type=w1200",
                    "숭실대학교",
                    "https://post-phinf.pstatic.net/20160811_167/1470891802502lQq1P_JPEG/google_co_kr_20160811_140117.jpg?type=w1200",
                    0,
                    listOf(),
                    WriterResponse("0",
                            "0",
                            UniversityResponse(0,"건준","도메인","https://post-phinf.pstatic.net/20160811_167/1470891802502lQq1P_JPEG/google_co_kr_20160811_140117.jpg?type=w1200"),
                            "건준",
                            "이메일",
                            0,
                            0)))
        }


        (replyRecyclerview.adapter as DetailReplyAdapter).replyList = tmpReplyList

    /*    viewModel.getReplies(scriptUuid)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeOf(
                        onNext = {
                            if(it.size == 0){
                                replyRecyclerview.visibility = View.GONE
                                noReplyBottomLine.visibility = View.VISIBLE
                                noReply.visibility = View.VISIBLE
                            }else{
                                (replyRecyclerview.adapter as DetailReplyAdapter).replyList = it as ArrayList<ReplyResponse>
                            }
                        },
                        onError = {
                            this.toast(resources.getString(R.string.reply_response_error))
                            replyRecyclerview.visibility = View.GONE
                            noReplyBottomLine.visibility = View.VISIBLE
                            noReply.visibility = View.VISIBLE
                        }
                )*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_TAKE_ALBUM ->
                    if (data?.data != null) {
                        cropImage(data.data)
                    }
                REQUEST_IMAGE_CROP -> {
                    if(data?.extras != null){
                        replyCroppedBitmap = data.extras.getParcelable("data")
                        writeReplyImage.setImageBitmap(replyCroppedBitmap)
                        writeReplyImageLayout.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
