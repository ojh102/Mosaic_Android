package com.teamnexters.mosaic.utils.extension

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

private val onNextStub: (Any) -> Unit = {}
private val onSuccessStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = { throw RuntimeException(it) }
private val onCompleteStub: () -> Unit = {}

fun <T : Any> Observable<T>.subscribeOf(
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub

): Disposable = subscribe(onNext, onError, onComplete)

fun <T : Any> Single<T>.subscribeOf(
        onSuccess: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub

): Disposable = subscribe(onSuccess, onError)
