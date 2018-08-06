package com.teamnexters.mosaic.ui.main

import android.os.Bundle
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutRes() = R.layout.activity_main

    override fun getViewModelClass() = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        initializeToolbar()
    }

    private fun initializeToolbar() {

    }

}
