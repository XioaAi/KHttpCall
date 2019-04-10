package com.xiaoai.zhao.khttpcall.ui.activity

import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.xiaoai.zhao.khttpcall.R
import com.xiaoai.zhao.khttpcall.base.BaseMVPActivity
import com.xiaoai.zhao.khttpcall.base.base_entities.BaseResultEntity
import com.xiaoai.zhao.khttpcall.di.module.LoginModule
import com.xiaoai.zhao.khttpcall.entities.LoginReqEntity
import com.xiaoai.zhao.khttpcall.entities.LoginResultEntity
import com.xiaoai.zhao.khttpcall.getMainComponent
import com.xiaoai.zhao.khttpcall.mvp.contract.LoginContract
import com.xiaoai.zhao.khttpcall.mvp.model.LoginModel
import com.xiaoai.zhao.khttpcall.mvp.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseMVPActivity<LoginContract.LoginView, LoginModel, LoginPresenter>(),LoginContract.LoginView{

    override fun loginSuccess(loginInfo: BaseResultEntity<LoginResultEntity>) {
        logd(loginInfo.errorMsg)
        showFailMsg(loginInfo.errorMsg+"--"+loginInfo.data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getMainComponent().plue(LoginModule(this)).inject(this)

        login.setOnClickListener {
            var username = account_et.text.toString()

            if(username.isEmpty()){
                showFailMsg("请输入账号")
                return@setOnClickListener
            }

            var password = password_et.text.toString()
            if(password.isEmpty() ){
                showFailMsg("请输入密码")
                return@setOnClickListener
            }

            var loginReqEntity = LoginReqEntity(username, password)

            presenter.login(loginReqEntity)
        }

    }
}
