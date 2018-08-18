package com.teamnexters.mosaic.data.remote.model


internal data class CategoryResponse(
        val name: String = "",
        val emoji: String = ""
) {
    fun getTheme(): String {
        return name + emoji
    }
}