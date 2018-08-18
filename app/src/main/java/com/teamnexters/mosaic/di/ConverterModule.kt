package com.teamnexters.mosaic.di

import com.teamnexters.mosaic.data.convertor.Converter
import com.teamnexters.mosaic.data.convertor.ScriptToCardConverter
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.di.qualifier.ScriptToCardQualifier
import com.teamnexters.mosaic.ui.main.CardLooknFeel
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
internal interface ConverterModule {
    @Binds
    @Singleton
    @ScriptToCardQualifier
    fun bindScriptToCardConverter(scriptToCardConverter: ScriptToCardConverter): Converter<ScriptResponse, CardLooknFeel>
}