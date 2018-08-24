package com.teamnexters.mosaic.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by daesoon.choi on 2018. 8. 24..
 */
class RxSchedulersFacade {

    fun io(): Scheduler {
        return Schedulers.io()
    }

    fun computation(): Scheduler {
        return Schedulers.computation()
    }

    fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}