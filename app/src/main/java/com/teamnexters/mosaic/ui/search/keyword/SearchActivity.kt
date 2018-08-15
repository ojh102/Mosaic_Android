package com.teamnexters.mosaic.ui.search.keyword

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.transition.Fade
import android.transition.TransitionInflater
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivitySearchBinding
import com.teamnexters.mosaic.utils.Navigator
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.withLatestFrom
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject


internal class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>() {

    @Inject lateinit var searchAdapter: SearchAdapter

    override fun getLayoutRes() = R.layout.activity_search

    override fun getViewModelClass() = SearchViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.main_background))

        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        initializeAnimation()
        initializeRecyclerView()

        bind(
                viewModel.bindSearchKeywordList()
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeBy(
                                onNext = {
                                    searchAdapter.setItems(it)
                                }
                        ),

                viewModel.bindClickSearch()
                        .withLatestFrom(viewModel.bindKeyword())
                        .map { it.second }
                        .filter { it.isNotBlank() }
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeBy(
                                onNext = {
                                    viewModel.addKeyword(it)

                                    Navigator.navigationToSearchResult(this@SearchActivity)
                                }
                        ),

                viewModel.bindClickCancel()
                        .subscribeBy(
                                onNext = {
                                    clearKeyword()
                                    finishAfterTransition()
                                }
                        ),

                viewModel.bindClickKewordCancel()
                        .subscribeBy(
                                onNext = {
                                    clearKeyword()
                                }
                        ),

                viewModel.bindKeyword()
                        .subscribeBy(
                                onNext = {
                                    val keywordCancelVisibility = it.isNotEmpty()

                                    binding.keywordCancelVisibility = keywordCancelVisibility
                                }
                        )
        )
    }

    private fun initializeRecyclerView() {
        rv_search.run {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchAdapter
        }
    }

    private fun initializeAnimation() {
        val transition = TransitionInflater.from(this).inflateTransition(R.transition.change_bound_transform)

        window.sharedElementEnterTransition = transition

        window.enterTransition = Fade().apply {
            duration = 0
        }

        window.returnTransition = Fade().apply {
            duration = 0
        }
    }

    private fun clearKeyword() {
        binding.editSearch.setText("")
    }

}