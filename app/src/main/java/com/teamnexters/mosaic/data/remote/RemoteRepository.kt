package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.data.remote.model.EmailSendResponse
import android.content.res.Resources
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.ui.common.theme.ThemeData
import com.teamnexters.mosaic.ui.main.CardLooknFeel
import com.teamnexters.mosaic.ui.mypage.MyPageData
import com.teamnexters.mosaic.ui.mypage.MyPageRowData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

internal class RemoteRepository @Inject constructor(
        private val mosaicApi: MosaicApi,
        private val resource: Resources

) : RemoteRepositoryApi {

    override fun fetchMainCardList(): Observable<List<CardLooknFeel>> {
        return createDummyList()
    }

    override fun fetchFilterList(): Observable<List<ThemeData>> {
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

    override fun fetchMyPage(): Observable<MyPageData> {
        return createDummyMyPageData()
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

    private fun createDummyFilterList(): Observable<List<ThemeData>> {
        val dummyList = mutableListOf<ThemeData>()

        dummyList.add(ThemeData("\uD83E\uDD2B", "익명제보"))
        dummyList.add(ThemeData("\uD83C\uDFC6", "공모전"))
        dummyList.add(ThemeData("\uD83D\uDC83", "대외활동"))
        dummyList.add(ThemeData("✍️", "스터디"))
        dummyList.add(ThemeData("\uD83C\uDF6F", "대학생활 팁"))
        dummyList.add(ThemeData("\uD83D\uDE4B\u200D♀️", "아르바이트"))
        dummyList.add(ThemeData("\uD83D\uDC6B", "동아리"))
        dummyList.add(ThemeData("\uD83D\uDC7B", "아무말"))

        return Observable.just(dummyList)
    }

    private fun createDummyMyPageData(): Observable<MyPageData> {
        return Observable.just(MyPageData(
                id = "SOGANG2039",
                univName = "이화여자대학교",
                email = "ojh102@gmail.com",
                univImgUrl = "https://picsum.photos/200?random",
                myPageRowDataList = listOf(
                        MyPageRowData.ScarpRow(resource.getString(R.string.my_page_scrap), 24),
                        MyPageRowData.WrittenRow(resource.getString(R.string.my_page_written), 3),
                        MyPageRowData.Reset(resource.getString(R.string.my_page_reset))
                )
        ))
    }
}