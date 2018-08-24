package com.teamnexters.mosaic.ui.write
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.data.remote.model.CategoryResponse
import com.teamnexters.mosaic.databinding.ViewThemeBinding
import com.teamnexters.mosaic.ui.common.theme.CompatibleThemeAction
import com.teamnexters.mosaic.ui.common.theme.ThemeAdapter
import com.teamnexters.mosaic.ui.common.theme.ThemeViewHolder

internal class WriteThemeAction : CompatibleThemeAction {

    override fun onClickTheme(themeAdapter: ThemeAdapter, viewHolder: ThemeViewHolder, items: List<CategoryResponse>) {

        var preSelectedPosition = -1
        val curSelectedPosition = viewHolder.adapterPosition

        items.forEachIndexed { index, categoryResponse ->
            if (categoryResponse.selected) {
                preSelectedPosition = index
            }
            categoryResponse.selected = false
        }

        val selectedOtherPosition = preSelectedPosition != curSelectedPosition
        if (preSelectedPosition != -1 && selectedOtherPosition) {
            themeAdapter.notifyItemChanged(preSelectedPosition)
        }

        val selectedItem = items[curSelectedPosition]
        selectedItem.selected = selectedOtherPosition

        viewHolder.bind(selectedItem)
    }

    override fun bindSelector(binding: ViewThemeBinding) {
        binding.backgroundSelectorRes = R.drawable.selector_theme_write_bg
        binding.highlightSelectorRes = R.drawable.selector_theme_write_text_highlight
        binding.textSelectorRes = R.drawable.selector_theme_text
    }

}