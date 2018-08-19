package com.teamnexters.mosaic.ui.common.theme

import com.teamnexters.mosaic.data.remote.model.CategoryResponse
import com.teamnexters.mosaic.databinding.ViewThemeBinding

internal interface CompatibleThemeAction {
    fun onClickTheme(themeAdapter: ThemeAdapter, viewHolder: ThemeViewHolder, items: List<CategoryResponse>)
    fun bindSelector(binding: ViewThemeBinding)
}