package com.teamnexters.mosaic.ui.search.result

import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.utils.Navigator
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject


internal class SearchResultViewModel @Inject constructor(

) : BaseViewModel() {

    init {
        bind(
                intent().map {
                    it.getStringExtra(Navigator.KEY_KEYWORD)
                }.subscribeBy(
                     onNext = {
                         Timber.d(it)
                     }
                )
        )
    }

}