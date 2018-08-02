package com.teamnexters.mosaic.ui.main.home

import android.os.Bundle
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseFragment
import com.teamnexters.mosaic.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    companion object {
        const val TAG = "home"

        fun newInstance() = HomeFragment()
    }

    override fun getLayoutRes() = R.layout.fragment_home

    override fun getModelClass() = HomeViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bind(
                viewModel.bindTopic()
                        .subscribe(binding::setTopicFilter)
        )

    }

}