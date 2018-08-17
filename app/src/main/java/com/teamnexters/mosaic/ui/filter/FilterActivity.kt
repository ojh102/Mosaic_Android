package com.teamnexters.mosaic.ui.filter

import android.content.Intent
import android.os.Bundle
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityFilterBinding
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_filter.*
import javax.inject.Inject

internal class FilterActivity : BaseActivity<ActivityFilterBinding, FilterViewModel>() {

    companion object {
        const val REQUEST_FILTER = 100
        const val KEY_FILTER = "filter"
    }

    @Inject lateinit var filterAdapter: FilterAdapter

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
                                    filterAdapter.setItems(it)
                                }
                        )
        )
    }

    override fun finish() {
        val intent = Intent().apply {
            putParcelableArrayListExtra(KEY_FILTER, ArrayList(filterAdapter.getSelectedItems()))
        }

        setResult(REQUEST_FILTER, intent)

        super.finish()
    }

    private fun initializeRecyclerView() {
        rv_filter.run {
            layoutManager = SpanningGridLayoutManager(this@FilterActivity, 2)
            adapter = filterAdapter
        }
    }
}