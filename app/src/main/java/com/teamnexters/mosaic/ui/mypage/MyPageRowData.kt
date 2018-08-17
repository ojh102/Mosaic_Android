package com.teamnexters.mosaic.ui.mypage

internal sealed class MyPageRowData(val title: String, val badgeCount: Int) {

    class ScarpRow(title: String, badgeCount: Int) : MyPageRowData(title, badgeCount)
    class WrittenRow(title: String, badgeCount: Int) : MyPageRowData(title, badgeCount)
    class Reset(title: String, badgeCount: Int = 0) : MyPageRowData(title, badgeCount)

}