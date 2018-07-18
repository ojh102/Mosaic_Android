package com.teamnexters.mosaic.data.local

import io.reactivex.*
import org.reactivestreams.Publisher

class RealmIOTransfer<T> : ObservableTransformer<T, T>, FlowableTransformer<T, T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(RealmSchedulers.realmIO())
                .unsubscribeOn(RealmSchedulers.realmIO())
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.subscribeOn(RealmSchedulers.realmIO())
                .unsubscribeOn(RealmSchedulers.realmIO())
    }

}