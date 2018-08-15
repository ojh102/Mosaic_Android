package com.teamnexters.mosaic.ui.search

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.transition.Fade
import android.transition.TransitionInflater
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivitySearchBinding
import io.reactivex.rxkotlin.subscribeBy


internal class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>() {

    override fun getLayoutRes() = R.layout.activity_search

    override fun getViewModelClass() = SearchViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.main_background))

        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        initializeAnimation()

        bind(
                viewModel.bindClickCancel()
                        .subscribeBy(
                                onNext = {
                                    binding.editSearch.setText("")
                                    finishAfterTransition()
                                }
                        ),

                viewModel.bindClickKewordCancel()
                        .subscribeBy(
                                onNext = {
                                    binding.editSearch.setText("")
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

}