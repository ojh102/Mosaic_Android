package com.teamnexters.mosaic.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.di.qualifier.RxIOScheduler
import com.teamnexters.mosaic.di.qualifier.RxMainScheduler
import com.teamnexters.mosaic.ui.detail.DetailViewModel
import com.teamnexters.mosaic.ui.login.LoginViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject


abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity(),
        HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var frameworkFragmentInjector: DispatchingAndroidInjector<android.app.Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    @field:RxIOScheduler
    lateinit var ioScheduler: Scheduler

    @Inject
    @field:RxMainScheduler
    lateinit var mainScheduler: Scheduler

    lateinit var binding: VB
    lateinit var viewModel: VM

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    protected abstract fun getViewModelClass(): Class<VM>

    private val compositeDisposable by lazy(LazyThreadSafetyMode.NONE) {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())

        viewModel.let {
            if(it is BaseViewModel) {
                it.intent(intent)
            }
        }

        if(viewModel is DetailViewModel == true){
            initializeWhiteWindow()
        }else if(viewModel is LoginViewModel == false){
            initializeWindow()
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        viewModel.let {
            if(it is BaseViewModel) {
                it.activityResult(requestCode, resultCode, data)
            }
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

    override fun fragmentInjector(): AndroidInjector<android.app.Fragment> {
        return frameworkFragmentInjector
    }

    protected fun bind(vararg disposables: Disposable) {
        compositeDisposable.addAll(*disposables)
    }

    private fun initializeWindow() {
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window.statusBarColor = Color.TRANSPARENT
        window.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.main_background))
    }

    private fun initializeWhiteWindow() {
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.WHITE
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

}