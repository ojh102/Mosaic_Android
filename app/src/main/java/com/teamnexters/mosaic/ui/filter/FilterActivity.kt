package com.teamnexters.mosaic.ui.filter

import android.content.Intent
import android.os.Bundle
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityFilterBinding
import com.teamnexters.mosaic.ui.Screen
import com.teamnexters.mosaic.ui.common.theme.SpanningGridLayoutManager
import com.teamnexters.mosaic.ui.common.theme.ThemeAdapter
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_filter.*
import javax.inject.Inject

internal class FilterActivity : BaseActivity<ActivityFilterBinding, FilterViewModel>() {

    companion object {
        const val REQUEST_FILTER = 100
        const val KEY_FILTER = "filter"
    }

    @Inject
    lateinit var themeAdapterFactory: ThemeAdapter.Factory

    private val themeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        themeAdapterFactory.newInstance(Screen.Filter)
    }

    override fun getLayoutRes() = R.layout.activity_filter

    override fun getViewModelClass() = FilterViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        initializeRecyclerView()

        bind(
                viewModel.bindClickClose()
                        .subscribeBy(
                                onNext = {
                                    finish()
                                }
                        ),

                viewModel.fetchFilterList()
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeBy(
                                onNext = {
                                    themeAdapter.setItems(it)
                                }
                        )
        )
    }

    override fun finish() {
        val intent = Intent().apply {
            putParcelableArrayListExtra(KEY_FILTER, ArrayList(themeAdapter.getSelectedItems()))
        }

        setResult(REQUEST_FILTER, intent)

        super.finish()
    }

    private fun initializeRecyclerView() {
        rv_filter.run {
            layoutManager = SpanningGridLayoutManager(this@FilterActivity, 2)
            adapter = themeAdapter
        }
    }
}