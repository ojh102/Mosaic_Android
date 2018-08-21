package com.teamnexters.mosaic.ui.main

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.databinding.ActivityMainBinding
import com.teamnexters.mosaic.databinding.ViewCardBinding
import com.teamnexters.mosaic.ui.main.stack.CardStackView
import com.teamnexters.mosaic.ui.main.stack.SwipeDirection
import com.teamnexters.mosaic.utils.Navigator
import com.teamnexters.mosaic.utils.extension.subscribeOf
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

internal class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutRes() = R.layout.activity_main

    override fun getViewModelClass() = MainViewModel::class.java

    @Inject
    lateinit var adapter: MosaicStackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        initializeStack()

        bind(
                viewModel.bindClickSearch()
                        .subscribeBy(
                                onNext = {
                                    Navigator.navigateToSearch(this, container_search)
                                }
                        ),

                viewModel.bindScrap()
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeBy(
                                onNext = { pair ->
                                    val scriptUuid = pair.first
                                    val scarped = pair.second

                                    adapter.setScrap(
                                            swipeCardStackView = stack_card,
                                            scriptUuid = scriptUuid,
                                            scrap = scarped
                                    )
                                }
                        )
        )

        fetchScriptList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        fetchScriptList()
    }

    private fun initializeStack() {

        stack_card.setCardEventListener(object : CardStackView.CardEventListener {
            override fun onCardDragging(percentX: Float, percentY: Float) {

            }

            override fun onCardSwiped(direction: SwipeDirection?) {

            }

            override fun onCardReversed() {

            }

            override fun onCardMovedToOrigin() {

            }

            override fun onCardClicked(index: Int) {
                Navigator.navigateToDetail(this@MainActivity, adapter.getItem(index))
            }

        })

        adapter.setScrapClickListener(object : MosaicStackAdapter.ScarpClickListener {
            override fun onClick(binding: ViewCardBinding, items: List<ScriptResponse>) {
                val scriptResponse = items[stack_card.topIndex]

                bind(
                        viewModel.scarp(scriptResponse.uuid)
                                .subscribeOn(ioScheduler)
                                .observeOn(mainScheduler)
                                .subscribeOf(
                                        onNext = {
                                            scriptResponse.scrap = it.scrap
                                            binding.card = scriptResponse
                                        }
                                )
                )
            }
        })

        stack_card.setAdapter(adapter)
    }

    private fun fetchScriptList() {
        bind(
                viewModel.fetchScriptList()
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeOf(
                                onNext = {
                                    adapter.setItems(it)
                                }
                        )
        )
    }

}
