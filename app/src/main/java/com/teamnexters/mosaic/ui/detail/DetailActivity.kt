package com.teamnexters.mosaic.ui.detail

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.data.local.MosaicSharedPreferenceManager
import com.teamnexters.mosaic.databinding.ActivityLoginBinding
import com.teamnexters.mosaic.utils.Navigator
import com.teamnexters.mosaic.utils.extension.hideSoftKeyboard
import com.teamnexters.mosaic.utils.extension.toast
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import com.teamnexters.mosaic.databinding.ActivityDetailBinding
import com.teamnexters.mosaic.utils.extension.isEmailAddress


class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override fun getLayoutRes() = R.layout.activity_detail

    override fun getViewModelClass() = DetailViewModel::class.java

}
