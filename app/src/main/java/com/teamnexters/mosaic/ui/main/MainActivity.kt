package com.teamnexters.mosaic.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivityMainBinding
import com.teamnexters.mosaic.ui.main.home.HomeFragment
import com.teamnexters.mosaic.ui.main.mypage.MyPageFragment
import com.teamnexters.mosaic.utils.Navigator
import com.teamnexters.mosaic.utils.extension.addFragment
import com.teamnexters.mosaic.utils.extension.hideFragment
import com.teamnexters.mosaic.utils.extension.showFragment

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun getLayoutRes() = R.layout.activity_main

    override fun getViewModelClass() = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        initializeToolbar()
        initializeBottomNavigation()
        initializeFragments()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.navigation_home -> {
                hideFragment(MyPageFragment.TAG)
                showFragment(HomeFragment.TAG)

                true
            }
            R.id.navigation_upload -> {
                Navigator.navigateToWrite(this)

                false
            }
            R.id.navigation_mypage -> {
                hideFragment(HomeFragment.TAG)
                showFragment(MyPageFragment.TAG)

                true
            }
            else -> {
                false
            }
        }
    }

    private fun initializeToolbar() {

    }

    private fun initializeBottomNavigation() {
        binding.navigation.run {
            enableAnimation(false)
            enableShiftingMode(false)
            enableItemShiftingMode(false)
            setTextVisibility(false)

            elevation = 0f
            itemIconTintList = null
            onNavigationItemSelectedListener = this@MainActivity
        }
    }

    private fun initializeFragments() {
        addFragment(R.id.container_fragment, HomeFragment.newInstance(), HomeFragment.TAG)
        addFragment(R.id.container_fragment, MyPageFragment.newInstance(), MyPageFragment.TAG)

        hideFragment(MyPageFragment.TAG)
    }

}
