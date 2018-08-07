package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.ui.main.CardLooknFeel
import io.reactivex.Observable
import javax.inject.Inject

class CardRepository @Inject constructor(
        private val mosaicApi: MosaicApi

) : CardRepositoryApi {

    fun getCards(): Observable<List<CardLooknFeel>> {
        val dummyList = mutableListOf<CardLooknFeel>()

        for (i in 1..20) {
            val dummy = CardLooknFeel(
                    date = "오늘",
                    theme = "테마$i",
                    content = "이것이 콘텐츠다$i",
                    univName = "대학$i",
                    imageUrlList = listOf(),
                    commentCount = i,
                    scarped = i % 2 == 0
            )

            dummyList.add(dummy)
        }

        return Observable.just(dummyList)
    }
}