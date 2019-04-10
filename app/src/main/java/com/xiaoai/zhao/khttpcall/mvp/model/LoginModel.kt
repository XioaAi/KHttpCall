package com.xiaoai.zhao.khttpcall.mvp.model

import com.xiaoai.zhao.khttpcall.api.XiaoAiApi
import com.xiaoai.zhao.khttpcall.base.base_entities.BaseResultEntity
import com.xiaoai.zhao.khttpcall.entities.LoginReqEntity
import com.xiaoai.zhao.khttpcall.entities.LoginResultEntity
import com.xiaoai.zhao.khttpcall.mvp.contract.LoginContract
import rx.Observable
import javax.inject.Inject

class LoginModel @Inject constructor(var api:XiaoAiApi) : LoginContract.LoginModel{

    override fun login(loginReqEntity: LoginReqEntity): Observable<BaseResultEntity<LoginResultEntity>> {
        return api.login(loginReqEntity.username,loginReqEntity.password)
    }

    override fun onDestory() {

    }

}