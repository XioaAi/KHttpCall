package com.xiaoai.zhao.khttpcall.di.component

import com.xiaoai.zhao.khttpcall.App
import com.xiaoai.zhao.khttpcall.di.module.ApiModule
import com.xiaoai.zhao.khttpcall.di.module.LoginModule
import dagger.Component
import javax.inject.Singleton

@Singleton//当Module中使用了@Singleton  Component中也必须使用@Singleton
@Component(modules = arrayOf(ApiModule::class))
interface ApiComponent {
    fun inject(app: App)

    fun plue(loginModule: LoginModule): LoginComponent
}