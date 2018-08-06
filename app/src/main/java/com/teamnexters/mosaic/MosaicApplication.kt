package com.teamnexters.mosaic

import android.app.Activity
import android.app.Application
import android.app.Service
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.bundled.BundledEmojiCompatConfig
import android.support.v4.app.Fragment
import com.facebook.stetho.Stetho
import com.teamnexters.mosaic.di.DaggerApplicationComponent
import dagger.android.*
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class MosaicApplication : Application(), HasActivityInjector, HasSupportFragmentInjector, HasFragmentInjector, HasServiceInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<android.app.Fragment>

    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this)

        EmojiCompat.init(BundledEmojiCompatConfig(this))

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

    override fun fragmentInjector(): AndroidInjector<android.app.Fragment> {
        return fragmentInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return serviceInjector
    }

}