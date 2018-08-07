package com.teamnexters.mosaic.ui.main

import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.CardRepository
import io.reactivex.Observable
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val cardRepository: CardRepository

) : BaseViewModel() {

    fun getCards(): Observable<List<CardLooknFeel>> {
        return cardRepository.getCards()
    }

}