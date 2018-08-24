package com.teamnexters.mosaic.ui.write

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import com.teamnexters.mosaic.data.remote.model.CategoryResponse
import com.teamnexters.mosaic.utils.RxSchedulersFacade
import com.teamnexters.mosaic.utils.extension.subscribeOf
import com.teamnexters.mosaic.utils.livedata.ListLiveData
import com.theartofdev.edmodo.cropper.CropImage
import com.teamnexters.mosaic.ui.write.Write.*
import com.teamnexters.mosaic.utils.saveBitmaptoFileCache
import java.io.File
import javax.inject.Inject

internal class WriteViewModel @Inject constructor(
    private val context: Context,
    private val schedulers: RxSchedulersFacade,
    private val remoteRepository: RemoteRepositoryApi
) : BaseViewModel() {

    val stateView = MutableLiveData<ViewState>().also {
        it.value = ViewState.Write()
    }

    val stateSave = MutableLiveData<SaveState>()

    val dataSelectedCategory = MutableLiveData<CategoryResponse>()

    val dataImages = ListLiveData<File>()


    init {
        activityResult()
            .subscribe {
                onActivityResult(it.first, it.second, it.third)
            }.also { bind(it) }
    }

    fun fetchCategoryData(onSuccess: (List<CategoryResponse>) -> Unit) {
        remoteRepository.fetchFilterList()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribeOf(onSuccess)
            .also { bind(it) }
    }

    fun onClickSave(content: String) {
        Log.d("daesoon","onClickSave $content")

        when {
            dataSelectedCategory.value == null -> stateSave.value = SaveState.Error("카테고리를 선택해 주세요.")
            content.isEmpty() -> stateSave.value = SaveState.Error("내용을 작성해주세요.")
            else -> saveScript(dataSelectedCategory.value!!.uuid, content, dataImages.value ?: listOf())
        }
    }

    fun onClickAddImage() {
        stateView.value = ViewState.TakeAlbum()
    }

    fun onClickCategorySelect() {
        stateView.value = ViewState.CategorySelect()
    }

    fun onCategoryItemClick(item: List<CategoryResponse>) {
        dataSelectedCategory.value = item.firstOrNull()
        stateView.value = ViewState.Write()
    }

    fun onClickExit() {
        stateView.value = ViewState.Finish()
    }

    fun onClickDeviceBack() {
        stateView.value = when(stateView.value) {
            is ViewState.Write -> ViewState.Finish()
            else -> ViewState.Write()
        }
    }


    private fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        when(requestCode) {
            WriteActivity.REQUEST_TAKE_ALBUM -> onResultFromTakeAlbum(resultCode, data)
            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> onResultFromCropImage(resultCode, data)
        }
    }

    private fun onResultFromTakeAlbum(resultCode: Int, data: Intent?){
        when(resultCode) {
            Activity.RESULT_OK -> {
                data?.let {
                    if (it.data == null) stateView.value = ViewState.Write()
                    else stateView.value = ViewState.Crop(it.data)
                }
            }
        }
    }

    private fun onResultFromCropImage(resultCode: Int, data: Intent?) {
        when(resultCode) {
            Activity.RESULT_OK -> CropImage.getActivityResult(data)?.apply {
                val bm = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                val file = saveBitmaptoFileCache(context,bm)
                dataImages.add(file)
            }
        }
    }


    private fun saveScript(categoryUuid: String, content: String, imgs: List<File>) {
        remoteRepository.saveScript(categoryUuid, content,imgs)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribeOf(
                onNext = { stateSave.value = SaveState.Success() },
                onError = { stateSave.value = SaveState.Error("알수없는 오류가 발생했습니다. 다시 시도해주세요.") }
            )
    }



}
sealed class Write {
    sealed class ViewState {
        class CategorySelect : ViewState()
        class TakeAlbum : ViewState()
        class Crop(val imgUri: Uri) : ViewState()
        class Write : ViewState()
        class Finish : ViewState()

    }

    sealed class SaveState {
        class Error(val err: String): SaveState()
        class Success: SaveState()
    }
}


