package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.ui.main.CardLooknFeel
import io.reactivex.Observable
import javax.inject.Inject

class RemoteRepository @Inject constructor(
        private val mosaicApi: MosaicApi

) : RemoteRepositoryApi {


    fun getCards(): Observable<List<CardLooknFeel>> {
        val dummyList = mutableListOf<CardLooknFeel>()

        val dummyImageUrl = "https://picsum.photos/200?random"

        for (i in 0..20) {
            val dummyImageUrlList = mutableListOf<String>()

            for (j in 0 until i % 6) {
                dummyImageUrlList.add(dummyImageUrl)
            }

            val dummy = CardLooknFeel(
                    date = "오늘",
                    theme = "테마$i",
                    content = "이것이 콘텐츠다$i",
                    univImageUrl = dummyImageUrl,
                    univName = "대학$i",
                    imageUrlList = dummyImageUrlList,
                    commentCount = i,
                    scarped = i % 2 == 0
            )

            dummyList.add(dummy)
        }

        return Observable.just(dummyList)
    }
}