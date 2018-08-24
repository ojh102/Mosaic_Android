package com.teamnexters.mosaic.ui.write


import android.arch.lifecycle.Observer
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
import android.util.Log
import android.view.View
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.data.remote.model.CategoryResponse
import com.teamnexters.mosaic.databinding.ActivityWriteBinding
import com.teamnexters.mosaic.ui.common.theme.SpanningGridLayoutManager
import com.teamnexters.mosaic.ui.common.theme.ThemeAdapter
import com.teamnexters.mosaic.utils.extension.hideKeyboard
import com.teamnexters.mosaic.utils.extension.startActivityForResult
import com.theartofdev.edmodo.cropper.CropImage
import com.teamnexters.mosaic.ui.write.Write.*
import com.teamnexters.mosaic.utils.extension.toast
import kotlinx.android.synthetic.main.activity_write.*
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Named

internal class WriteActivity : BaseActivity<ActivityWriteBinding, WriteViewModel>() {

    companion object {
        const val REQUEST_TAKE_ALBUM = 1000
        private const val TYPE_IMAGE_DATA = "image/*"
    }

    @Inject
    @field:Named("write")
    lateinit var themeAdapter: ThemeAdapter

    override fun getLayoutRes() = R.layout.activity_write

    override fun getViewModelClass() = WriteViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize UI
        btn_album.setOnClickListener { viewModel.onClickAddImage() }
        selector_category.setOnClickListener { viewModel.onClickCategorySelect() }
        btn_save.setOnClickListener {
            Log.d("daesoon","select btn save")
            viewModel.onClickSave(input_content.text.toString())
        }


        recycler_category.apply {
            layoutManager = SpanningGridLayoutManager(this@WriteActivity, 2)
            adapter = themeAdapter
        }

        themeAdapter.onClickItem = viewModel::onCategoryItemClick

        viewModel.fetchCategoryData(this::initCategoryList)



        //Initialize Observer
        viewModel.stateView.observe(this, Observer {
            when(it) {
                is ViewState.TakeAlbum -> showImageSelectActivity()
                is ViewState.Crop -> showImageCropActivity(it.imgUri)
                is ViewState.CategorySelect -> showCategorySelectView()
                is ViewState.Write -> showWriteView()
            }
        })

        viewModel.dataImages.observe(this, Observer {
            updateAddImages(it ?: listOf())
        })

        viewModel.dataSelectedCategory.observe(this, Observer {
            bindSelectCategoryOnView(it)
        })

        viewModel.stateSave.observe(this, Observer {
            when(it) {
                is SaveState.Success -> saveSuccess()
                is SaveState.Error -> saveError(it.err)
            }
        })
    }

    private fun showImageSelectActivity() = Intent(Intent.ACTION_PICK, EXTERNAL_CONTENT_URI).apply {
        type = TYPE_IMAGE_DATA
    }.startActivityForResult(this, REQUEST_TAKE_ALBUM)

    private fun showImageCropActivity(imgUri: Uri) = CropImage.activity(imgUri).setAspectRatio(1,1).start(this)

    private fun showCategorySelectView() {
        hideKeyboard()

        recycler_category.postDelayed({
            recycler_category.visibility = View.VISIBLE
        },200)

    }

    private fun showWriteView() {
        recycler_category.visibility = View.GONE
    }

    private fun updateAddImages(item: List<File>) {
        holder_images.removeAllViews()
        item.forEach { holder_images.addImage(Uri.fromFile(it)) }
    }

    private fun bindSelectCategoryOnView(category: CategoryResponse?) {
        text_selected_category.text = if (category == null) getString(R.string.select_category) else category.name + category.emoji
    }

    private fun initCategoryList( item: List<CategoryResponse>) {
        themeAdapter.setItems(item)
    }

    private fun saveSuccess() {
        toast("글을 등록하였습니다.")
        finish()
    }

    private fun saveError(err: Throwable) {
        err.printStackTrace()
        toast(err.message?:"")
    }



}