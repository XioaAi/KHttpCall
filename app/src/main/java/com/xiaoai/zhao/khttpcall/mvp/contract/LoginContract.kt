package com.xiaoai.zhao.khttpcall.mvp.contract

import com.xiaoai.zhao.khttpcall.base.base_entities.BaseResultEntity
import com.xiaoai.zhao.khttpcall.base.base_mvp.IBaseModule
import com.xiaoai.zhao.khttpcall.base.base_mvp.IBaseView
import com.xiaoai.zhao.khttpcall.entities.LoginReqEntity
import com.xiaoai.zhao.khttpcall.entities.LoginResultEntity
import rx.Observable

interface LoginContract{
    interface LoginView : IBaseView{
        fun loginSuccess(loginInfo : BaseResultEntity<LoginResultEntity>)
    }

    interface LoginModel : IBaseModule{
        fun login(loginReqEntity: LoginReqEntity): Observable<BaseResultEntity<LoginResultEntity>>
    }

    interface LoginPresenter{
        fun login(loginReqEntity: LoginReqEntity)
    }
}