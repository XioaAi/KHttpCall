package com.xiaoai.zhao.khttpcall.mvp.presenter

import android.util.Log
import com.xiaoai.zhao.khttpcall.base.base_entities.BaseResultEntity
import com.xiaoai.zhao.khttpcall.base.base_mvp.BasePresenter
import com.xiaoai.zhao.khttpcall.entities.LoginReqEntity
import com.xiaoai.zhao.khttpcall.mvp.contract.LoginContract
import com.xiaoai.zhao.khttpcall.mvp.model.LoginModel
import com.xiaoai.zhao.khttpcall.net.rxjava.ISubscriberListener
import com.xiaoai.zhao.khttpcall.net.rxjava.RxSubscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenter
    @Inject constructor(var loginView: LoginContract.LoginView, var loginModel: LoginModel)
    : BasePresenter<LoginContract.LoginView, LoginModel>(loginView, loginModel),LoginContract.LoginPresenter{

    override fun login(loginReqEntity: LoginReqEntity) {
        addSubscription(loginModel.login(loginReqEntity)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxSubscriber(object:ISubscriberListener<BaseResultEntity>{
                    override fun onError(errorInfo: String) {
                        Log.e("XiaoAi",errorInfo)
                    }

                    override fun onSuccess(t: BaseResultEntity) {
                        loginView.loginSuccess(t)
                    }
                })))
    }

}