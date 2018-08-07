package com.teamnexters.mosaic.ui.main

data class CardLooknFeel(
        val date: String,
        val theme: String,
        val content: String,
        val imageUrlList: List<String>,
        val nonEmptyComment: Boolean,
        val commentCount: Int,
        val scarped: Boolean
)
