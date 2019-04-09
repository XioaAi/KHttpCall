package com.xiaoai.zhao.khttpcall.di.component

import com.xiaoai.zhao.khttpcall.di.PerActivity
import com.xiaoai.zhao.khttpcall.di.module.LoginModule
import com.xiaoai.zhao.khttpcall.ui.activity.LoginActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginComponent{
    fun inject(loginActivity: LoginActivity)
}