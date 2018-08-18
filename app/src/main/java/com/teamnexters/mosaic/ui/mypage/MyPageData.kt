package com.teamnexters.mosaic.ui.mypage

internal data class MyPageData(
        val id: String,
        val univImgUrl: String,
        val univName: String,
        val email: String,
        val myPageRowDataList: List<MyPageRowData>
)