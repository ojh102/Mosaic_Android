package com.teamnexters.mosaic.ui.filter

import android.os.Bundle
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityFilterBinding

internal class FilterActivity : BaseActivity<ActivityFilterBinding, FilterViewModel>() {

    companion object {
        const val REQUEST_FILTER = 100
    }

    override fun getLayoutRes() = R.layout.activity_filter

    override fun getViewModelClass() = FilterViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

    }
}