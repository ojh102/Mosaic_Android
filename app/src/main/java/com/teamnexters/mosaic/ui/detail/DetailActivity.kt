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


internal class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    val REQUEST_TAKE_ALBUM = 0
    val REQUEST_IMAGE_CROP = 1

    lateinit var photoUri : Uri

    var uuid: Int = 0
    var isScraped = false

    val closeButton by lazy { binding.closeBtn }
    val scrapButton by lazy { binding.scrapBtn }
    val universityName by lazy { binding.universityName }
    val userId by lazy { binding.userId }
    val imageViewPager by lazy { binding.imageViewPager }
    val contentTitle by lazy { binding.contentTitle }
    val content by lazy { binding.content }
    val writeTime by lazy { binding.writeTime }
    val replyCount by lazy { binding.replyCount }
    val replyRecyclerview by lazy { binding.replyRecyclerview }
    val addReplyImage by lazy { binding.addReplyImage }
    val writeReplyEditText by lazy { binding.writeReplyEdittext }
    val sendReply by lazy { binding.sendReply }
    val indicatorLayout by lazy { binding.indicatorLayout }

    override fun getLayoutRes() = R.layout.activity_detail

    override fun getViewModelClass() = DetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecylerView()
        initIntentData(intent)
        initListener()
        initLayout()
    }

    fun initIntentData(intent: Intent) {
        //intent로 받은 정보 view에 할당


        //intent로 전달 받은 uuid로 서버에 쏜다.
        initData(uuid)

        //intent로 받은 이미지 리스타 값을 바탕으로 viewpager 초기화
        val tmpImageList = ArrayList<String>()

        for(i in 0..4){
            tmpImageList.add("https://post-phinf.pstatic.net/20160811_167/1470891802502lQq1P_JPEG/google_co_kr_20160811_140117.jpg?type=w1200")
        }

        initViewPager(tmpImageList)
    }

    fun initLayout() {
        sendReply.isEnabled = false
    }

    private fun initRecylerView() {
        replyRecyclerview.layoutManager = LinearLayoutManager(this)
        replyRecyclerview.adapter = DetailReplyAdapter(this)
        replyRecyclerview.setNestedScrollingEnabled(false)
    }

    private fun initViewPager(imageList : ArrayList<String>) {
        //intent를 통해 이미지 리스트가 넘어 올것이다

        imageViewPager.adapter = DetailImagePagerAdapter(this)
        (imageViewPager.adapter as DetailImagePagerAdapter).imageList = imageList

        initIndicator(5, 6.toPx)

        indicatorLayout.onSelected(0)
    }


    private fun initIndicator(num: Int, spacing: Int) {
        indicatorLayout.removeAllIndicator()
        indicatorLayout.spacing = spacing

        for (i in 1..num) {
            indicatorLayout.addIndicator(createIndicatorView())
        }
    }

    private fun createIndicatorView(): View {
        val view = View(this)
        view.setBackgroundResource(R.drawable.detail_image_dot_indicator)
        val layoutParams = ViewGroup.LayoutParams(5.toPx, 5.toPx)
        view.setLayoutParams(layoutParams)

        return view
    }

    fun initListener() {
        closeButton.setOnClickListener { finish() }
        scrapButton.setOnClickListener { scripContent() }
        imageViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                if (indicatorLayout != null) indicatorLayout.onSelected(position % 5)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        writeReplyEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().length == 0) sendReply.isEnabled = false else sendReply.isEnabled = true
            }

            override fun afterTextChanged(s: Editable) {}
        })

        addReplyImage.setOnClickListener {
            getAlbum()
        }

        sendReply.setOnClickListener {

        }
    }

    private fun getAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE)
        startActivityForResult(intent, REQUEST_TAKE_ALBUM)
    }

    private fun cropImage() {
        if(photoUri != null){
            val cropIntent = Intent("com.android.camera.action.CROP")

            cropIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            cropIntent.setDataAndType(photoUri,"image/*")
            cropIntent.putExtra("aspectX", 1)
            cropIntent.putExtra("aspectY", 1)
            cropIntent.putExtra("scale", true)
            startActivityForResult(cropIntent, REQUEST_IMAGE_CROP)
        }
    }

/*    private fun createImageFile() : File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_$timeStamp.jpg"
        val storageDir = File("${Environment.getExternalStorageDirectory()}/Pictures","mosaic")

        if(!storageDir.exists()){
            storageDir.mkdir()
        }

        val imageFile = File(storageDir,imageFileName)

        return imageFile
    }*/

    fun initData(contentId: Int) {
        //서버로 contentId를 쏴라!!!
        //여기서 댓글 리스트를 받는다!!

        val tmpReplyList = ArrayList<ReplyDetailData>()

        for(i in 0..10){
            tmpReplyList.add(ReplyDetailData(i % 2,
                    0,
                    "https://post-phinf.pstatic.net/20160811_167/1470891802502lQq1P_JPEG/google_co_kr_20160811_140117.jpg?type=w1200",
                    "숭실대학교",
                    "이건준",
                    i % 2 == 0,
                    "댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당 댓글 본문입니당",
                    "3분전",
                    if(i % 2 == 1 ) "@EWHA0001" else "",
                    null))
        }


        (replyRecyclerview.adapter as DetailReplyAdapter).replyList = tmpReplyList
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_TAKE_ALBUM ->
                if (resultCode == Activity.RESULT_OK) {
                    if (data?.data != null) {
                        try {
                            //val albumFile = createImageFile()
                            photoUri = data.data

                            cropImage()
                        }catch (e : Exception){

                        }
                    }
                }
            REQUEST_IMAGE_CROP ->
                if (resultCode == Activity.RESULT_OK) {
                    if (data?.data != null) {

                    }
                }
        }
    }

    fun scripContent() {
        //서버로 스크랩 정보 보내기


        if (isScraped == true) {
            isScraped = !isScraped
            scrapButton.setImageDrawable(scrapButton.resources.getDrawable(R.drawable.ic_scrap_nol, null))
        } else {
            isScraped = !isScraped
            scrapButton.setImageDrawable(scrapButton.resources.getDrawable(R.drawable.ic_scrap_pr, null))
        }
    }
}
