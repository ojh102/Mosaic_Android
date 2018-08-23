package com.teamnexters.mosaic.ui.write

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
import android.view.View
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityWriteBinding
import com.teamnexters.mosaic.ui.common.theme.ThemeAdapter
import com.teamnexters.mosaic.utils.extension.startActivityForResult
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_write.*
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

        btn_album.setOnClickListener { viewModel.onClickAddImage() }

        viewModel.stateView.observe(this, Observer {
            when(it) {
                is ViewState.TakeAlbum -> showImageSelectActivity()
                is ViewState.Crop -> showImageCropActivity(it.imgUri)
                is ViewState.CategorySelect -> showCategorySelectView()
            }
        })

        viewModel.dataImages.observe(this, Observer {
            updateAddImages(it ?: listOf())
        })
    }

    private fun showImageSelectActivity() = Intent(Intent.ACTION_PICK, EXTERNAL_CONTENT_URI).apply {
        type = TYPE_IMAGE_DATA
    }.startActivityForResult(this, REQUEST_TAKE_ALBUM)

    private fun showImageCropActivity(imgUri: Uri) = CropImage.activity(imgUri).setAspectRatio(1,1).start(this)

    private fun showCategorySelectView() {
        container_category.visibility = View.VISIBLE
    }

    private fun updateAddImages(item: List<Uri>) {
        holder_images.removeAllViews()
        item.forEach { holder_images.addImage(it) }
    }



}