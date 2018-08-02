package com.teamnexters.mosaic.ui.main.home

import android.content.res.Resources
import com.jakewharton.rxrelay2.BehaviorRelay
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject


class HomeViewModel @Inject constructor(
        private val resources: Resources

) : BaseViewModel() {

    private val topicFilterNameRelay = BehaviorRelay.createDefault(resources.getString(R.string.topic_filter_competition_and_parttime))

    fun acceptTopic(topic: String) {
        topicFilterNameRelay.accept(topic)
    }

    fun bindTopic(): Observable<String> {
        return topicFilterNameRelay
    }

}