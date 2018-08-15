package com.teamnexters.mosaic.ui.main

data class CardLooknFeel(
        val id: String,
        val date: String,
        val theme: String,
        val content: String,
        val imageUrlList: List<String>,
        val univImageUrl: String,
        val univName: String,
        val commentCount: Int,
        val scarped: Boolean
)
