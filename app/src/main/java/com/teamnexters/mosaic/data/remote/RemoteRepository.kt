package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.ui.main.CardLooknFeel
import io.reactivex.Observable
import javax.inject.Inject

class RemoteRepository @Inject constructor(
        private val mosaicApi: MosaicApi

) : RemoteRepositoryApi {

    override fun fetchMainCardList(): Observable<List<CardLooknFeel>> {
        return createDummyList()
    }

    override fun fetchResultListFromSearch(): Observable<List<CardLooknFeel>> {
        return createDummyList()
    }

    override fun fetchResultListFromWritten(): Observable<List<CardLooknFeel>> {
        return createDummyList()
    }

    override fun fetchResultListFromScrap(): Observable<List<CardLooknFeel>> {
        return createDummyList()
    }

    private fun createDummyList(): Observable<List<CardLooknFeel>> {
        val dummyList = mutableListOf<CardLooknFeel>()

        val dummyImageUrl = "https://picsum.photos/200?random"

        for (i in 0..20) {
            val dummyImageUrlList = mutableListOf<String>()

            for (j in 0 until i % 6) {
                dummyImageUrlList.add(dummyImageUrl)
            }

            val dummy = CardLooknFeel(
                    id = "Univ$i",
                    date = "오늘",
                    theme = "테마$i",
                    content = "$i 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다 이것이 콘텐츠다이것이 콘텐츠다 이것이 콘텐츠다",
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