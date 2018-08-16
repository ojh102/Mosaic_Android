package com.teamnexters.mosaic.ui.mypage

import android.os.Bundle
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityMyPageBinding

internal class MyPageActivity : BaseActivity<ActivityMyPageBinding, MyPageViewModel>() {

    override fun getLayoutRes() = R.layout.activity_my_page

    override fun getViewModelClass() = MyPageViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
    }

}