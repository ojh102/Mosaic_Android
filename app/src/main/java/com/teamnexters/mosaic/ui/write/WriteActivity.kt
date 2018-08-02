package com.teamnexters.mosaic.ui.write

import android.os.Bundle
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityWriteBinding

class WriteActivity : BaseActivity<ActivityWriteBinding, WriteViewModel>() {

    override fun getLayoutRes() = R.layout.activity_write

    override fun getViewModelClass() = WriteViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}