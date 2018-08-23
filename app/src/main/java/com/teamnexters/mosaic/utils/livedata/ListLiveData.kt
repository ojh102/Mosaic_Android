package com.teamnexters.mosaic.utils.livedata

import android.arch.lifecycle.LiveData

/**
 * Created by daesoon.choi on 2018. 8. 21..
 */
class ListLiveData<T>: LiveData<List<T>>() {

    private val data = mutableListOf<T>()

    fun add(item: T) {
        data.add(item)
        postValue(data)
    }

    fun removeAt(index: Int) {
        data.removeAt(index)
        postValue(data)
    }

    fun remove(item: T) {
        data.remove(item)
        postValue(data)
    }


}