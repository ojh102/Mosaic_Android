package com.teamnexters.mosaic.data.convertor

import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.ui.main.CardLooknFeel
import javax.inject.Inject


internal class ScriptToCardConverter @Inject constructor() : Converter<ScriptResponse, CardLooknFeel> {

    override fun convert(source: ScriptResponse): CardLooknFeel {
        return CardLooknFeel(
                uuId = source.uuid,
                id = source.writer.nick,
                date = "${source.created}",
                theme = source.category.name + source.category.emoji,
                content = source.content,
                imageUrlList = source.thumbnailUrls,
                univImageUrl = source.writer.university.umgUrl,
                univName = source.writer.university.name,
                commentCount = source.replies,
                scarped = source.scrap
        )
    }
}