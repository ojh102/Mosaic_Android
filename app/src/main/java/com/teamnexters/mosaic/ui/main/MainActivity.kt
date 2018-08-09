package com.teamnexters.mosaic.ui.main

import android.os.Bundle
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityMainBinding
import com.teamnexters.mosaic.ui.main.stack.CardStackView
import com.teamnexters.mosaic.ui.main.stack.SwipeDirection
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutRes() = R.layout.activity_main

    override fun getViewModelClass() = MainViewModel::class.java

    @Inject
    lateinit var adapter: MosaicStackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        initializeStack()

        bind(
                viewModel.getCards()
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeBy(
                                onNext = {
                                    adapter.addAll(it)
                                }
                        )
        )
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

            }

        })

        stack_card.setAdapter(adapter)
    }

}
