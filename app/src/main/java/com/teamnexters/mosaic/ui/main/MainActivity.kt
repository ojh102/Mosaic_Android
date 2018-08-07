package com.teamnexters.mosaic.ui.main

import android.os.Bundle
import android.support.v7.widget.helper.ItemTouchHelper
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityMainBinding
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var cardAdapter: CardAdapter

    override fun getLayoutRes() = R.layout.activity_main

    override fun getViewModelClass() = MainViewModel::class.java

    private var datas = mutableListOf<CardLooknFeel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        initializeRecyclerView()

        bind(
                viewModel.getCards()
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeBy(
                                onNext = {
                                    datas.addAll(it)
                                    cardAdapter.addItems(it)
                                }
                        )
        )
    }

    private fun initializeRecyclerView() {
        rv_card.run {
            layoutManager = OverLayCardLayoutManager()
            adapter = cardAdapter
        }

        CardConfig.initConfig(this)

        val callback = PrimerCallback(rv_card, cardAdapter, datas)
        val itemTouchHelper = ItemTouchHelper(callback)

        itemTouchHelper.attachToRecyclerView(rv_card)
    }

}
