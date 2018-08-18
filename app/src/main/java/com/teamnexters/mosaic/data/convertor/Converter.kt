package com.teamnexters.mosaic.data.convertor


internal interface Converter<T, R> {
    fun convert(source: T): R
}