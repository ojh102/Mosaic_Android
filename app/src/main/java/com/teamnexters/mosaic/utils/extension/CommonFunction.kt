package com.teamnexters.mosaic.utils.extension

import com.teamnexters.mosaic.data.remote.model.ResponseEnvelope

fun <T> validate(responseEnvelope: ResponseEnvelope<T>): T {
    if(responseEnvelope.result != null) {
        return responseEnvelope.result!!
    } else {
        throw RuntimeException("서버 결과가 널이다 ㅠㅠ")
    }
}