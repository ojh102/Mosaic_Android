package com.teamnexters.mosaic.ui.main.mypage

import android.os.Bundle
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseFragment
import com.teamnexters.mosaic.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding, MyPageViewModel>() {

    companion object {
        const val TAG = "myPage"

        fun newInstance() = MyPageFragment()
    }

    override fun getLayoutRes() = R.layout.fragment_my_page

    override fun getModelClass() = MyPageViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}