package com.teamnexters.mosaic.ui.login

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.data.local.MosaicSharedPreferenceManager
import com.teamnexters.mosaic.databinding.ActivityLoginBinding
import com.teamnexters.mosaic.utils.Navigator
import com.teamnexters.mosaic.utils.extension.hideSoftKeyboard
import com.teamnexters.mosaic.utils.extension.toast
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.text.TextUtils
import android.view.View.GONE
import android.view.View.VISIBLE
import com.teamnexters.mosaic.utils.extension.isEmailAddress


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    val mUseAgreementLayout by lazy { binding.useAgreementLayout }
    val mUseAgreementText by lazy { binding.useAgreementText }
    val mUseAgreementAgree by lazy { binding.useAgreementAgree }
    val mEmailEditText by lazy { binding.emailEdittext }
    val mSendEmail by lazy { binding.sendEmail }
    val mEmailCheckLayout by lazy { binding.emailCheckLayout }
    val mUserEmail by lazy { binding.userEmail }
    val mCheckEmail by lazy { binding.checkEmail }
    val mCheckEmailBack by lazy { binding.checkEmailBack }

    var mIsAgreementLayoutVisible = false
    var mIsEmailCheckLayoutVisible = false

    @Inject
    lateinit var mSharedPreferenceManager: MosaicSharedPreferenceManager

    override fun getLayoutRes() = R.layout.activity_login

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        checkEmailSent()
        initListener()
    }

    fun initLayout() {
        //서비스 약관 내려놓음
        mUseAgreementLayout.let {
            it.post {
                it.translationY = it.height.toFloat()
                it.visibility = GONE
            }
        }
    }

    fun checkEmailSent() {
        mSharedPreferenceManager.getString(MosaicSharedPreferenceManager.EMAIL_ADDRESS).let {
            if ("".equals(it)) {
                val screenWidth = applicationContext.getResources().getDisplayMetrics().widthPixels
                mEmailCheckLayout.translationX = screenWidth.toFloat()

                mIsEmailCheckLayoutVisible = false
            } else {
                mUserEmail.text = it

                mIsEmailCheckLayoutVisible = true
            }
        }
    }

    fun initListener() {
        mUseAgreementAgree.setOnClickListener { hideUseAgreement() }
        mUseAgreementText.setOnClickListener { showUseAgreement() }
        mSendEmail.setOnClickListener { sendEmailInfo() }
        mCheckEmail.setOnClickListener {

            Navigator.navigateToDetail(this)
            //Navigator.navigateToInternet(this)
            //Navigator.navigateToMain(this)
            finish()
        }
        mCheckEmailBack.setOnClickListener {
            hideEmailCheckLayout()
        }

        //이메일 edittext 관련 listener
        mEmailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().length == 0) mSendEmail.isEnabled = false else mSendEmail.isEnabled = true
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        mEmailEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendEmailInfo()
                    return true
                } else {
                    return false
                }
            }
        })
    }

    fun saveEmailPref(email : String){
        mSharedPreferenceManager.setString(MosaicSharedPreferenceManager.EMAIL_ADDRESS, email)
    }

    fun sendEmailInfo() {
        if (mEmailEditText.text.toString().length == 0) {
            toast(resources.getString(R.string.email_empty));
        } else {
            if(mEmailEditText.text.toString().isEmailAddress){
                //서버에 이메일을 보내달라고 요청!
                viewModel.sendEmailInfo()

                saveEmailPref(mEmailEditText.text.toString())
                mUserEmail.text = mEmailEditText.text.toString()

                showEmailCheckLayout()
            }else{
                this.toast(resources.getString(R.string.invalid_email_format))
            }
        }
    }

    fun showEmailCheckLayout() {
        //이메일 edittext의 포커스 제거
        mEmailEditText.clearFocus()

        mEmailEditText.hideSoftKeyboard()

        mIsEmailCheckLayoutVisible = true

        mEmailCheckLayout.let {
            it.clearAnimation()

            it.animate()
                    .translationX(0f)
                    .setDuration(300)
                    .setInterpolator(DecelerateInterpolator(1.5f))
                    .start()
        }
    }

    fun hideEmailCheckLayout() {
        //이메일 edittext의 포커스 제거
        mEmailEditText.clearFocus()

        mIsEmailCheckLayoutVisible = false

        mEmailCheckLayout.let {
            it.clearAnimation()

            it.animate()
                    .translationX(it.width.toFloat())
                    .setDuration(300)
                    .setInterpolator(DecelerateInterpolator(1.5f))
                    .start()
        }
    }

    fun showUseAgreement() {
        //이메일 edittext의 포커스 제거
        mEmailEditText.clearFocus()

        mIsAgreementLayoutVisible = true

        mUseAgreementLayout.let {
            it.clearAnimation()

            it.animate()
                    .translationY(0f)
                    .setDuration(300)
                    .setInterpolator(DecelerateInterpolator(1.5f))
                    .setListener(object : Animator.AnimatorListener{
                        override fun onAnimationRepeat(animation: Animator?) {}

                        override fun onAnimationCancel(animation: Animator?) {}

                        override fun onAnimationStart(animation: Animator?) {
                            it.visibility = VISIBLE
                        }

                        override fun onAnimationEnd(animation: Animator){}
                    })
                    .start()
        }
    }

    fun hideUseAgreement() {
        //이메일 edittext의 포커스 제거
        mEmailEditText.clearFocus()

        mIsAgreementLayoutVisible = false

        mUseAgreementLayout.let {
            it.clearAnimation()

            it.animate()
                    .translationY(it.height.toFloat())
                    .setDuration(300)
                    .setInterpolator(DecelerateInterpolator(1.5f))
                    .setListener(object : Animator.AnimatorListener{
                        override fun onAnimationRepeat(animation: Animator?) {}

                        override fun onAnimationCancel(animation: Animator?) {}

                        override fun onAnimationStart(animation: Animator?) {}

                        override fun onAnimationEnd(animation: Animator){
                            it.visibility = GONE
                        }
                    })
                    .start()
        }
    }

    override fun onBackPressed() {
        if (mIsAgreementLayoutVisible)
            hideUseAgreement()
        else if(mIsEmailCheckLayoutVisible)
            hideEmailCheckLayout()
        else super.onBackPressed()
    }
}
