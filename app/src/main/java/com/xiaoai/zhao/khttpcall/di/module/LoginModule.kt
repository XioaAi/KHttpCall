package com.xiaoai.zhao.khttpcall.di.module

import com.xiaoai.zhao.khttpcall.mvp.contract.LoginContract
import dagger.Module
import dagger.Provides

@Module
class LoginModule{
    var loginView : LoginContract.LoginView;

    constructor(loginView: LoginContract.LoginView) {
        this.loginView = loginView
    }

    @Provides
    fun providerLoginView() = loginView
}