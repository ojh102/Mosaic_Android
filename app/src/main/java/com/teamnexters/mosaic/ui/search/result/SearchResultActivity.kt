package com.teamnexters.mosaic.ui.search.result

import android.os.Bundle
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivitySearchResultBinding


internal class SearchResultActivity : BaseActivity<ActivitySearchResultBinding, SearchResultViewModel>() {

    override fun getLayoutRes() = R.layout.activity_search_result

    override fun getViewModelClass() = SearchResultViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
    }

}