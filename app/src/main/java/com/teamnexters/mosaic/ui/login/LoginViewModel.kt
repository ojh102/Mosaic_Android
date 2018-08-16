package com.teamnexters.mosaic.ui.login

import com.teamnexters.mosaic.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel()  {

    //이메일 정보를 서버로 보내는 로직
    fun sendEmailInfo(){}

    //스킴을 통해 받은 코드가 맞는지 확인하는 로직
    fun checkEmailSchemeCode(){}
}