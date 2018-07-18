package com.teamnexters.mosaic.data.local

import io.reactivex.Completable
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class RealmTransaction @Inject constructor(
        private val realmConfiguration: RealmConfiguration
) {

    fun execute(realmConfiguration: RealmConfiguration, transactionAction: ((realm: Realm) -> Unit)) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction(transactionAction)
    }

    fun executeAsync(realmConfiguration: RealmConfiguration, transactionAction: (realm: Realm) -> Unit): Completable {

        return Completable.create { emitter ->
            val realm = Realm.getInstance(realmConfiguration)

            realm.executeTransactionAsync(
                    { transactionAction.invoke(it) },
                    { emitter.onComplete() },
                    { emitter.onError(it) }
            )
        }
    }

    fun <T> select(realmConfiguration: RealmConfiguration, selectAction: ((realm: Realm) -> T)): T {
        val realm = Realm.getInstance(realmConfiguration)
        val selectedValue = selectAction.invoke(realm)
        realm.close()
        return selectedValue
    }

}