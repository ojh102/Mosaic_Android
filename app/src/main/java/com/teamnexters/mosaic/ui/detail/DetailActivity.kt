package com.teamnexters.mosaic.ui.detail

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.data.local.MosaicSharedPreferenceManager
import com.teamnexters.mosaic.data.remote.model.ReplyResponse
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.databinding.ActivityDetailBinding
import com.teamnexters.mosaic.ui.widget.DetailWriteEditText
import com.teamnexters.mosaic.utils.extension.showKeyboard
import com.teamnexters.mosaic.utils.extension.subscribeOf
import com.teamnexters.mosaic.utils.extension.toPx
import com.teamnexters.mosaic.utils.extension.toast
import javax.inject.Inject


internal class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    companion object {
        const val DETAIL_INTENT_KEY = "detailIntentKey"
        const val REQUEST_TAKE_ALBUM = 0
    }

    @Inject
    lateinit var sharedPreferenceManager: MosaicSharedPreferenceManager

    val closeButton by lazy { binding.closeBtn }
    val scrapButton by lazy { binding.scrapBtn }
    val universityName by lazy { binding.universityName }
    val userId by lazy { binding.userId }
    val replyRecyclerview by lazy { binding.replyRecyclerview }
    val addReplyImage by lazy { binding.addReplyImage }
    val writeReplyEditText : DetailWriteEditText by lazy { binding.writeReplyEdittext }
    val sendReply by lazy { binding.sendReply }
    val writeReplyImageLayout by lazy { binding.writeReplyImageLayout }
    val writeReplyImage by lazy { binding.writeReplyImage }
    val writeReplyImageCancel by lazy { binding.writeReplyImageCancel }
    val writeReplyLayout by lazy { binding.writeReplyLayout }
    val deleteCardLayout by lazy { binding.deleteCardLayout }

    val myUuid by lazy { sharedPreferenceManager.getString(MosaicSharedPreferenceManager.UUID,"") }

    var scriptUuid = ""
    var scriptWriterUuid = ""
    var isScraped = false

    val linearLayoutManager = LinearLayoutManager(this)

    lateinit var recyclerViewAdapter : DetailReplyAdapter

    override fun getLayoutRes() = R.layout.activity_detail

    override fun getViewModelClass() = DetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initIntentData()
        initListener()
        initLayout()
    }

    private fun initIntentData() {
        val cardData : ScriptResponse = intent.getParcelableExtra(DETAIL_INTENT_KEY)

        scriptWriterUuid = cardData.writer.uuid
        scriptUuid = cardData.uuid

        initRecylerView(cardData)

        universityName.text = cardData.writer.university.name
        userId.text = cardData.writer.nick

        setScripContent(cardData.scrap)

        initData(scriptUuid)
    }

    private fun initRecylerView(cardData : ScriptResponse) {
        replyRecyclerview.layoutManager = linearLayoutManager
        recyclerViewAdapter = DetailReplyAdapter(this, myUuid, cardData)
        replyRecyclerview.adapter = recyclerViewAdapter
    }


    fun setScripContent(scraped : Boolean) {
        isScraped = scraped

        if (scraped) {
            scrapButton.setImageResource(R.drawable.ic_scrap_pr)
        } else {
            scrapButton.setImageResource(R.drawable.ic_scrap_nol)
        }
    }

    fun initLayout() {
        deleteCardLayout.visibility = if(myUuid.equals(scriptWriterUuid)) VISIBLE else GONE

        sendReply.isEnabled = false
    }

    fun initListener() {
        closeButton.setOnClickListener { finish() }
        scrapButton.setOnClickListener {
            bind(
                    viewModel.scrap(scriptUuid)
                            .subscribeOn(ioScheduler)
                            .observeOn(mainScheduler)
                            .subscribeOf(
                                    onNext = {
                                        isScraped = it.scrap

                                        setScripContent(isScraped)

                                        globalChannelApi.scrapCard(scriptUuid, isScraped)
                                    }
                            )
            )
        }


        writeReplyEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { sendReply.isEnabled = s.toString().length != 0 }
            override fun afterTextChanged(s: Editable) {}
        })

        addReplyImage.setOnClickListener { getAlbum() }

        sendReply.setOnClickListener { it ->
            if(writeReplyEditText.isRereplyEmpty()){
                this.toast(resources.getString(R.string.empty_reply))
            }else{
                val text = writeReplyEditText.getTextRereply()

                viewModel.addReplies(text.toString(), null ,scriptUuid, writeReplyEditText.rereplyDetailData?.uuid)
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeOf(
                                onNext = {
                                    if(writeReplyEditText.isRereplyMode){
                                        var sameReplyFound = false
                                        var addPosition = 0;
                                        for(position in recyclerViewAdapter.replyList.indices){
                                            if(sameReplyFound){
                                                if(recyclerViewAdapter.replyList.get(position).depth == 0){
                                                    addPosition = position
                                                    break;
                                                }
                                            }

                                            if(recyclerViewAdapter.replyList.get(position).uuid.equals(writeReplyEditText.rereplyDetailData?.uuid)){
                                                sameReplyFound = true
                                            }
                                        }

                                        if(addPosition == 0){
                                            addPosition = recyclerViewAdapter.replyList.size
                                        }

                                        recyclerViewAdapter.addReply(addPosition, it)
                                        replyRecyclerview.smoothScrollToPosition(addPosition+1)

                                    }else{
                                        recyclerViewAdapter.addReply(it)
                                        replyRecyclerview.smoothScrollToPosition(recyclerViewAdapter.replyList.size)
                                    }

                                    writeReplyEditText.setText("")
                                    writeReplyEditText.setRereplyMode(null)
                                },
                                onError = {
                                    it.printStackTrace()
                                    this.toast(resources.getString(R.string.add_reply_response_error))
                                }
                        )
            }
        }

        deleteCardLayout.setOnClickListener { it ->
            viewModel.deleteScript(scriptUuid)
                    .subscribeOn(ioScheduler)
                    .observeOn(mainScheduler)
                    .subscribeOf(
                            onNext = {
                                globalChannelApi.deleteCard(scriptUuid)
                                finish()
                            },
                            onError = {
                                this.toast(resources.getString(R.string.delete_script_response_error))
                            })
        }

        writeReplyImageCancel.setOnClickListener {
            writeReplyImageLayout.visibility = GONE
        }

        /*replyRecyclerview.addOnLayoutChangeListener(object : View.OnLayoutChangeListener{
            override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                replyRecyclerview.postDelayed(Runnable { replyRecyclerview.smoothScrollBy(0,oldBottom - bottom ) }, 100)
            }
        })*/
    }

    fun setEditTextRereply(rereplyDetailData: ReplyResponse?){
        writeReplyEditText.setRereplyMode(rereplyDetailData)
        writeReplyEditText.requestFocus()
        writeReplyEditText.showKeyboard(0)
    }

    private fun getAlbum() {
        val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"

        startActivityForResult(intent, REQUEST_TAKE_ALBUM)
    }

    fun initData(scriptUuid: String) {
        viewModel.getReplies(scriptUuid)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeOf(
                        onNext = {
                            if(it.size != 0){
                                recyclerViewAdapter.addReplyList(it as ArrayList<ReplyResponse>)
                            }
                        },
                        onError = {
                            this.toast(resources.getString(R.string.get_reply_response_error))
                        }
                )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_TAKE_ALBUM ->
                    if (data?.data != null) {
                        val uri = data.data
                    }
            }
        }
    }
}
