package com.teamnexters.mosaic.data.local

import com.teamnexters.mosaic.data.local.model.Keyword
import io.reactivex.Observable
import io.realm.Sort
import javax.inject.Inject

internal class LocalRepository @Inject constructor(
        private val realmTransaction: RealmTransaction,
        private val realmObservableFactory: MosaicRealmObservableFactory

) : LocalRepositoryApi {

    override fun getKeywordList(): Observable<List<Keyword>> {
        return realmObservableFactory.fromList {
            it.where(Keyword::class.java).sort("createdAt", Sort.DESCENDING).findAll()
        }
    }

    override fun addKeyword(keyword: Keyword) {
        realmTransaction.execute {
            it.insertOrUpdate(keyword)
        }
    }
}