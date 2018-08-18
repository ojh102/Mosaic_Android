package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.data.remote.model.EmailSendResponse
import com.teamnexters.mosaic.ui.filter.FilterData
import com.teamnexters.mosaic.ui.main.CardLooknFeel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

internal class RemoteRepository @Inject constructor(
        private val mosaicApi: MosaicApi
) : RemoteRepositoryApi {

    override fun fetchMainCardList(): Observable<List<CardLooknFeel>> {
        return createDummyList()
    }

    override fun fetchFilterList(): Observable<List<FilterData>> {
        return createDummyFilterList()
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

    private fun createDummyFilterList(): Observable<List<FilterData>> {
        val dummyList = mutableListOf<FilterData>()

        dummyList.add(FilterData("\uD83E\uDD2B", "익명제보"))
        dummyList.add(FilterData("\uD83C\uDFC6", "공모전"))
        dummyList.add(FilterData("\uD83D\uDC83", "대외활동"))
        dummyList.add(FilterData("✍️", "스터디"))
        dummyList.add(FilterData("\uD83C\uDF6F", "대학생활 팁"))
        dummyList.add(FilterData("\uD83D\uDE4B\u200D♀️", "아르바이트"))
        dummyList.add(FilterData("\uD83D\uDC6B", "동아리"))
        dummyList.add(FilterData("\uD83D\uDC7B", "아무말"))

        return Observable.just(dummyList)
    }
}