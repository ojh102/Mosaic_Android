package com.teamnexters.mosaic.ui.login

import android.animation.Animator
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.data.local.MosaicSharedPreferenceManager
import com.teamnexters.mosaic.databinding.ActivityLoginBinding
import com.teamnexters.mosaic.utils.Navigator
import com.teamnexters.mosaic.utils.extension.*
import java.util.*
import javax.inject.Inject


internal class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    enum class LoginBackground(val resourceId : Int){
        EWHA(R.drawable.img_back_ewhauniv),
        KYUNGHEE(R.drawable.img_back_kyungheeuniv),
        KOREA(R.drawable.img_back_koreauniv),
        KOREA2(R.drawable.img_back_koreauniv_2),
        YONSEI(R.drawable.img_yonseiuniv),
    }

    val loginBackgroudImage by lazy { binding.loginBackgroudImage}
    val useAgreementLayout by lazy { binding.useAgreementLayout }
    val useAgreementText by lazy { binding.useAgreementText }
    val useAgreementAgree by lazy { binding.useAgreementAgree }
    val emailEditText by lazy { binding.emailEdittext }
    val sendEmail by lazy { binding.sendEmail }
    val emailCheckLayout by lazy { binding.emailCheckLayout }
    val userEmail by lazy { binding.userEmail }
    val checkEmail by lazy { binding.checkEmail }
    val checkEmailBack by lazy { binding.checkEmailBack }

    var isAgreementLayoutVisible = false
    var isEmailCheckLayoutVisible = false

    @Inject
    lateinit var sharedPreferenceManager: MosaicSharedPreferenceManager

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
        useAgreementLayout.let {
            it.post {
                it.translationY = it.height.toFloat()
                it.visibility = GONE
            }
        }

        //백그라운드 이미지 설정
        initBackgroundImage()
    }

    fun initBackgroundImage(){
        val imageResource = when(Random().nextInt(5)) {
            LoginBackground.EWHA.ordinal -> LoginBackground.EWHA.resourceId
            LoginBackground.KYUNGHEE.ordinal -> LoginBackground.KYUNGHEE.resourceId
            LoginBackground.KOREA.ordinal -> LoginBackground.KOREA.resourceId
            LoginBackground.KOREA2.ordinal -> LoginBackground.KOREA2.resourceId
            else -> LoginBackground.YONSEI.resourceId
        }

        loginBackgroudImage.setImageResource(imageResource)

        loginBackgroudImage.animate()
                .translationX(30.toPx.toFloat())
                .setDuration(3000)
                .start()
    }


    fun checkEmailSent() {
        sharedPreferenceManager.getString(MosaicSharedPreferenceManager.EMAIL_ADDRESS).let {
            if ("".equals(it)) {
                emailCheckLayout.translationX = applicationContext.getResources().getDisplayMetrics().widthPixels.toFloat()

                isEmailCheckLayoutVisible = false
            } else {
                userEmail.text = it

                isEmailCheckLayoutVisible = true
            }
        }
    }

    fun initListener() {
        useAgreementAgree.setOnClickListener { hideUseAgreement() }
        useAgreementText.setOnClickListener { showUseAgreement() }
        sendEmail.setOnClickListener { sendEmailInfo() }
        checkEmail.setOnClickListener { getTokenInfo() }
        checkEmailBack.setOnClickListener { hideEmailCheckLayout() }

        //이메일 edittext 관련 listener
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().length == 0) sendEmail.isEnabled = false else sendEmail.isEnabled = true
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        emailEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
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

    fun getTokenInfo(){
        val authKey = sharedPreferenceManager.getString(MosaicSharedPreferenceManager.AUTH_KEY,"")
        val uuid = sharedPreferenceManager.getString(MosaicSharedPreferenceManager.UUID,"")

        if(TextUtils.isEmpty(authKey) || TextUtils.isEmpty(uuid)) {
            toast(resources.getString(R.string.auth_email_error))
            return
        }

        viewModel.getTokenInfo(authKey, uuid).subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeOf(
                        onNext = {
                            sharedPreferenceManager.setString(MosaicSharedPreferenceManager.TOKEN, it.token)
                            Navigator.navigateToMain(this)
                            finish()
                        },
                        onError = {
                            toast(resources.getString(R.string.auth_email_error));
                        }
                )
    }

    fun sendEmailInfo() {
        val email = emailEditText.text.toString()

        if (email.length == 0) {
            toast(resources.getString(R.string.email_empty));
        } else {
            if (email.isEmailAddress) {
                //서버에 이메일을 보내달라고 요청!

                viewModel.sendEmailInfo(email) .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeOf(
                                onNext = {
                                    sharedPreferenceManager.setString(MosaicSharedPreferenceManager.AUTH_KEY, it.authKey)
                                    sharedPreferenceManager.setString(MosaicSharedPreferenceManager.UUID, it.uuid)
                                    sharedPreferenceManager.setString(MosaicSharedPreferenceManager.EMAIL_ADDRESS, email)

                                    userEmail.text = email

                                    showEmailCheckLayout()
                                },
                                onError = {
                                    toast(resources.getString(R.string.email_error));
                                }
                        )
            } else {
                this.toast(resources.getString(R.string.invalid_email_format))
            }
        }
    }

    fun showEmailCheckLayout() {
        //이메일 edittext의 포커스 제거
        emailEditText.clearFocus()

        emailEditText.hideSoftKeyboard()

        isEmailCheckLayoutVisible = true

        emailCheckLayout.let {
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
        emailEditText.clearFocus()

        isEmailCheckLayoutVisible = false

        emailCheckLayout.let {
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
        emailEditText.clearFocus()

        isAgreementLayoutVisible = true

        useAgreementLayout.let {
            it.clearAnimation()

            it.animate()
                    .translationY(0f)
                    .setDuration(300)
                    .setInterpolator(DecelerateInterpolator(1.5f))
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) {}

                        override fun onAnimationCancel(animation: Animator?) {}

                        override fun onAnimationStart(animation: Animator?) {
                            it.visibility = VISIBLE
                        }

                        override fun onAnimationEnd(animation: Animator) {}
                    })
                    .start()
        }
    }

    fun hideUseAgreement() {
        //이메일 edittext의 포커스 제거
        emailEditText.clearFocus()

        isAgreementLayoutVisible = false

        useAgreementLayout.let {
            it.clearAnimation()

            it.animate()
                    .translationY(it.height.toFloat())
                    .setDuration(300)
                    .setInterpolator(DecelerateInterpolator(1.5f))
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) {}

                        override fun onAnimationCancel(animation: Animator?) {}

                        override fun onAnimationStart(animation: Animator?) {}

                        override fun onAnimationEnd(animation: Animator) {
                            it.visibility = GONE
                        }
                    })
                    .start()
        }
    }

    override fun onBackPressed() {
        if (isAgreementLayoutVisible)
            hideUseAgreement()
        else if (isEmailCheckLayoutVisible)
            hideEmailCheckLayout()
        else super.onBackPressed()
    }
}
