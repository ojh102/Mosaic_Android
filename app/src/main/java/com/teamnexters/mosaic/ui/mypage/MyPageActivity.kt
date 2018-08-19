package com.teamnexters.mosaic.ui.mypage

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.data.local.model.Keyword
import com.teamnexters.mosaic.databinding.ActivityMyPageBinding
import com.teamnexters.mosaic.ui.Screen
import com.teamnexters.mosaic.utils.Navigator
import com.teamnexters.mosaic.utils.extension.subscribeOf
import com.teamnexters.mosaic.utils.extension.toast
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_my_page.*
import javax.inject.Inject

internal class MyPageActivity : BaseActivity<ActivityMyPageBinding, MyPageViewModel>() {

    @Inject
    lateinit var myPageAdapter: MyPageAdapter

    override fun getLayoutRes() = R.layout.activity_my_page

    override fun getViewModelClass() = MyPageViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        bind(
                viewModel.bindClickBack()
                        .subscribeBy(
                                onNext = {
                                    finish()
                                }
                        ),

                viewModel.fetchMyPage()
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeOf(
                                onNext = {
                                    binding.data = it

                                    myPageAdapter.setItems(listOf(
                                            MyPageRowData.ScarpRow(getString(R.string.my_page_scrap), it.myScrapCnt),
                                            MyPageRowData.WrittenRow(getString(R.string.my_page_written), it.myScriptCnt),
                                            MyPageRowData.Reset(getString(R.string.my_page_reset))
                                    ))
                                }
                        )
        )

        rv_my_page.run {
            layoutManager = LinearLayoutManager(this@MyPageActivity)
            adapter = myPageAdapter
        }

        myPageAdapter.setMyPageRowClickListener(object : MyPageAdapter.MyPageRowClickListener {
            override fun onRowClick(myPageRowData: MyPageRowData) {
                when (myPageRowData) {
                    is MyPageRowData.ScarpRow -> {
                        Navigator.navigationToResult(
                                this@MyPageActivity,
                                Keyword().apply {
                                    keyword = myPageRowData.title
                                },
                                Screen.Scrap
                        )
                    }

                    is MyPageRowData.WrittenRow -> {
                        Navigator.navigationToResult(
                                this@MyPageActivity,
                                Keyword().apply {
                                    keyword = myPageRowData.title
                                },
                                Screen.Written
                        )
                    }

                    is MyPageRowData.Reset -> {
                        toast("리셋하자")
                    }
                }
            }
        })


        myPageAdapter.setItems(listOf(
                MyPageRowData.ScarpRow(getString(R.string.my_page_scrap), 0),
                MyPageRowData.WrittenRow(getString(R.string.my_page_written), 0),
                MyPageRowData.Reset(getString(R.string.my_page_reset))
        ))
    }

}