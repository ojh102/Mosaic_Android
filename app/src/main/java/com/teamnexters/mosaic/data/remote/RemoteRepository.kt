package com.teamnexters.mosaic.data.remote

import android.content.res.Resources
import com.google.gson.Gson
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.ui.common.theme.ThemeData
import com.teamnexters.mosaic.ui.mypage.MyPageData
import com.teamnexters.mosaic.ui.mypage.MyPageRowData
import com.teamnexters.mosaic.utils.extension.validate
import io.reactivex.Observable
import javax.inject.Inject

internal class RemoteRepository @Inject constructor(
        private val mosaicApi: MosaicApi,
        private val resource: Resources

) : RemoteRepositoryApi {

    override fun fetchMainCardList(): Observable<List<ScriptResponse>> {
        return mosaicApi.fetchCards()
                .map { validate(it) }
                .toObservable()
    }

    override fun fetchFilterList(): Observable<List<ThemeData>> {
        return createDummyFilterList()
    }

    override fun fetchResultListFromSearch(): Observable<List<ScriptResponse>> {
        return createDummyList()
    }

    override fun fetchResultListFromWritten(): Observable<List<ScriptResponse>> {
        return createDummyList()
    }

    override fun fetchResultListFromScrap(): Observable<List<ScriptResponse>> {
        return createDummyList()
    }

    override fun fetchMyPage(): Observable<MyPageData> {
        return createDummyMyPageData()
    }

    private fun createDummyList(): Observable<List<ScriptResponse>> {
        val dummyList = mutableListOf<ScriptResponse>()

        val dummyImageUrl = "https://picsum.photos/200?random"

        for(i in 0..20) {
            val jsonString = """
                 {
            "idx": 1,
            "uuid": "b171b90d-5764-416f-a433-89b3b293faab",
            "content": "씨박asd",
            "writer": {
                "uuid": "524a934b-1a6f-4f55-8f5d-22baf7d77c54",
                "nick": "BcQEGOzleH",
                "university": {
                    "idx": 1,
                    "name": "네이버대",
                    "domain": "naver.com",
                    "imgUrl": "https://s3.ap-northeast-2.amazonaws.com/angointeam-s3/scripts/dumy/dumy.png"
                }
            },
            "imgUrls": [],
            "thumbnailUrls": [],
            "valid": true,
            "category": {
                "name": "익명제보",
                "emoji": "U+1F92B"
            },
            "replies": 0,
            "scrap": false,
            "createdAt": 1534587587000
        }
            """.trimIndent()

            val gson = Gson()
            val data = gson.fromJson<ScriptResponse>(jsonString, ScriptResponse::class.java)

            dummyList.add(data)
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