package com.teamnexters.mosaic.ui.filter

import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.databinding.ViewThemeBinding
import com.teamnexters.mosaic.ui.common.theme.CompatibleThemeAction
import com.teamnexters.mosaic.ui.common.theme.ThemeAdapter
import com.teamnexters.mosaic.ui.common.theme.ThemeData
import com.teamnexters.mosaic.ui.common.theme.ThemeViewHolder

internal class FilterThemeAction : CompatibleThemeAction {

    override fun onClickTheme(themeAdapter: ThemeAdapter, viewHolder: ThemeViewHolder, items: List<ThemeData>) {
        val selectedItem = items[viewHolder.adapterPosition]
        selectedItem.selected = selectedItem.selected.not()

        viewHolder.bind(selectedItem)
    }

    override fun bindSelector(binding: ViewThemeBinding) {
        binding.backgroundSelectorRes = R.drawable.selector_theme_filter_bg
        binding.highlightSelectorRes = R.drawable.selector_theme_filter_text_highlight
        binding.textSelectorRes = R.drawable.selector_theme_text
    }

}