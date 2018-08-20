package com.teamnexters.mosaic.ui.result

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityResultBinding
import com.teamnexters.mosaic.ui.Screen
import com.teamnexters.mosaic.utils.Navigator
import com.teamnexters.mosaic.utils.extension.subscribeOf
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.withLatestFrom
import kotlinx.android.synthetic.main.activity_result.*
import javax.inject.Inject


internal class ResultActivity : BaseActivity<ActivityResultBinding, ResultViewModel>() {

    companion object {
        const val KEY_TITLE = "title"
        const val KEY_FROM_SCREEN = "fromScreen"
    }

    @Inject
    lateinit var resultAdapterFactory: ResultAdapter.Factory

    private val resultAdapter by lazy(LazyThreadSafetyMode.NONE) {
        resultAdapterFactory.newInstance(intent.getSerializableExtra(ResultActivity.KEY_FROM_SCREEN) as Screen)
    }

    override fun getLayoutRes() = R.layout.activity_result

    override fun getViewModelClass() = ResultViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        initializeRecyclerView()

        bind(
                viewModel.bindClickBack()
                        .withLatestFrom(viewModel.bindFromScreen())
                        .map { it.second }
                        .subscribeBy(
                                onNext = {
                                    when (it) {
                                        Screen.Search -> {
                                            Navigator.navigateToMain(this, true)
                                        }
                                        else -> {
                                            finish()
                                        }
                                    }
                                }
                        ),

                viewModel.bindClickClose()
                        .subscribeBy(
                                onNext = {
                                    finish()
                                }
                        ),

                viewModel.bindTitle()
                        .subscribeBy(
                                onNext = {
                                    binding.title = it
                                }
                        ),

                viewModel.bindFromScreen()
                        .subscribeBy(
                                onNext = {
                                    binding.screen = it
                                }
                        ),

                viewModel.bindResultList()
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeOf(
                                onNext = {
                                    resultAdapter.setItems(it)
                                }
                        ),

                viewModel.bindScarp()
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeOf(
                                onNext = {
                                    resultAdapter.setScrap(
                                            scarpUuid = it.first,
                                            scraped = it.second
                                    )
                                }
                        )
        )
    }

    private fun initializeRecyclerView() {
        rv_result.run {
            layoutManager = LinearLayoutManager(this@ResultActivity)
            adapter = resultAdapter
        }
    }

}