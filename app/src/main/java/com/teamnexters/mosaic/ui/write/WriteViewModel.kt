package com.teamnexters.mosaic.ui.write

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import com.teamnexters.mosaic.utils.livedata.ListLiveData
import com.theartofdev.edmodo.cropper.CropImage
import javax.inject.Inject

internal class WriteViewModel @Inject constructor(
        private val remoteRepository: RemoteRepositoryApi
) : BaseViewModel() {

    val stateNetworkResponse = MutableLiveData<WriteResponse>()
    val stateView = MutableLiveData<ViewState>().also {
        it.value = ViewState.Write()
    }

    val dataImages = ListLiveData<Uri>()

    init {
        bind(
            activityResult()
                .subscribe {
                    onActivityResult(it.first, it.second, it.third)
                }

        )
    }

    fun onClickSave() {

    }

    fun onClickAddImage() {
        stateView.value = ViewState.TakeAlbum()
    }

    fun onClickCategorySelect() {
        stateView.value = ViewState.CategorySelect()
    }

    fun onClickExit() {
        stateView.value = ViewState.Finish()
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
            Activity.RESULT_OK -> CropImage.getActivityResult(data)?.apply { dataImages.add(uri) }
        }
    }
}

sealed class WriteResponse {
    class Success : WriteResponse()
    class Failure : WriteResponse()
}

sealed class ViewState {
    class CategorySelect : ViewState()
    class TakeAlbum : ViewState()
    class Crop(val imgUri: Uri) : ViewState()
    class Write : ViewState()
    class Finish : ViewState()

}
